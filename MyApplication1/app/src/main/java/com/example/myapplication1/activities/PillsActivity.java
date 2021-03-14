package com.example.myapplication1.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.myapplication1.R;
import com.example.myapplication1.help.DeletepillRequest;
import com.example.myapplication1.help.ReadpillRequest;
import com.example.myapplication1.help.SavepillRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class PillsActivity extends AppCompatActivity {

    private Menu action;
    private Spinner mGenderSpinner;
    private int mGender = 0;
    public static final int GENDER_UNKNOWN = 0;
    public static final int GENDER_NO = 1;
    public static final int GENDER_YES = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pills);
        EditText editText = findViewById(R.id.namepill);
        EditText editText1 = findViewById(R.id.dozpill);
        mGenderSpinner = findViewById(R.id.gender);

        editText.setFocusable(false);
        editText1.setFocusable(false);
        mGenderSpinner.setEnabled(false);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("Лекарство");

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        Get(id);
        setupSpinner();


    }

    private void setupSpinner(){
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.array_gender_options, android.R.layout.simple_spinner_item);
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mGenderSpinner.setAdapter(genderSpinnerAdapter);

        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_no))) {
                        mGender = GENDER_NO;
                    } else if (selection.equals(getString(R.string.gender_yes))) {
                        mGender = GENDER_YES;
                    } else {
                        mGender = GENDER_UNKNOWN;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender = 0;
            }
        });
    }

    private void Get(int id) {
        Response.Listener<String> responseRequest = response -> {
            EditText editText = findViewById(R.id.namepill);
            EditText editText1 = findViewById(R.id.dozpill);
            int gender = 0;

            try {
                JSONObject jsonResponse = new JSONObject(response);
                boolean success = jsonResponse.getBoolean("success");

                if (success) {
                    String nam = jsonResponse.getString("name");
                    String surnam = jsonResponse.getString("doz");
                    String receipt = jsonResponse.getString("receipt");

                    editText.setText(nam);
                    editText1.setText(surnam);

                    if (receipt.equals("yes")) {
                         gender = 2;
                    }

                    if (receipt.equals("no")) {
                        gender = 1;
                    }

                    switch (gender) {
                        case 1:
                            mGenderSpinner.setSelection(1);
                            break;
                        case 2:
                            mGenderSpinner.setSelection(2);
                            break;
                        default:
                            mGenderSpinner.setSelection(0);
                            break;
                    }

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(PillsActivity.this);
                    builder.setMessage("Произошла ошибка!")
                            .setNegativeButton("Обратитесь к администратору", null)
                            .create()
                            .show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        };

        ReadpillRequest readRequest = new ReadpillRequest(id, responseRequest);
        RequestQueue queue = Volley.newRequestQueue(PillsActivity.this);
        queue.add(readRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_action, menu);
        action = menu;
        action.findItem(R.id.menu_save).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        EditText editText = findViewById(R.id.namepill);
        EditText editText1 = findViewById(R.id.dozpill);
        mGenderSpinner = findViewById(R.id.gender);
        switch (item.getItemId()) {
            case android.R.id.home:
                PillsActivity.this.finish();

                return true;
            case R.id.menu_edit:

                editText.setFocusableInTouchMode(true);
                editText1.setFocusableInTouchMode(true);
                mGenderSpinner.setEnabled(true);

                action.findItem(R.id.menu_edit).setVisible(false);
                action.findItem(R.id.menu_delete).setVisible(false);
                action.findItem(R.id.menu_save).setVisible(true);

                return true;

            case R.id.menu_save:

                final String nam = editText.getText().toString();
                final String surn = editText1.getText().toString();
                int gen = mGender;
                String rec ="";
                if (gen == 1 || gen == 0){
                    rec = "no";
                }
                if (gen == 2){
                    rec = "yes";
                }
                Save(id, nam, surn, rec);

                editText.setFocusable(false);
                editText1.setFocusable(false);
                mGenderSpinner.setEnabled(false);


                action.findItem(R.id.menu_edit).setVisible(true);
                action.findItem(R.id.menu_delete).setVisible(true);
                action.findItem(R.id.menu_save).setVisible(false);

                return true;

            case R.id.menu_delete:

                AlertDialog.Builder dialog = new AlertDialog.Builder(PillsActivity.this);
                dialog.setMessage("Удалить препарат?");
                dialog.setPositiveButton("Да" ,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        deleteData(id);
                    }
                });
                dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void deleteData(int id) {
        Response.Listener<String> responseRequest = response -> {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                boolean success = jsonResponse.getBoolean("success");

                if (success) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(PillsActivity.this);
                    builder.setMessage("Успешно удалено!")
                            .setNegativeButton("Закрыть", null)
                            .create()
                            .show();
                    PillsActivity.this.finish();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(PillsActivity.this);
                    builder.setMessage("Произошла ошибка!")
                            .setNegativeButton("Обратитесь к администратору", null)
                            .create()
                            .show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        };
        DeletepillRequest readRequest = new DeletepillRequest(id,responseRequest);
        RequestQueue queue = Volley.newRequestQueue(PillsActivity.this);
        queue.add(readRequest);
    }

    private void Save(int id, String name, String surname, String rec) {
        Response.Listener<String> responseRequest = response -> {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                boolean success = jsonResponse.getBoolean("success");

                if (success) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(PillsActivity.this);
                    builder.setMessage("Изменения сохранены!")
                            .setNegativeButton("Закрыть", null)
                            .create()
                            .show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(PillsActivity.this);
                    builder.setMessage("Произошла ошибка!")
                            .setNegativeButton("Обратитесь к администратору", null)
                            .create()
                            .show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        };
        SavepillRequest readRequest = new SavepillRequest(id, name, surname, rec,responseRequest);
        RequestQueue queue = Volley.newRequestQueue(PillsActivity.this);
        queue.add(readRequest);
    }

}
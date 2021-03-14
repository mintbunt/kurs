package com.example.myapplication1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.myapplication1.R;
import com.example.myapplication1.help.AddPillRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class AddpillsActivity extends AppCompatActivity {
    private Spinner mGenderSpinner;
    private int mGender = 0;
    public static final int GENDER_UNKNOWN = 0;
    public static final int GENDER_NO = 1;
    public static final int GENDER_YES = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpills);

        final Button addpill = findViewById(R.id.addpill);
        EditText editText = findViewById(R.id.namepill1);
        EditText editText1 = findViewById(R.id.dozpill1);
        mGenderSpinner = findViewById(R.id.gender1);
        setTitle("Выберите:");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        int id1 = intent.getIntExtra("id", 0);
        int id2 = intent.getIntExtra("id_doc", 0);
        int id_pac = intent.getIntExtra("id_pac", 0);
        setupSpinner();

        addpill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String login = editText.getText().toString();
                final String login1 = editText1.getText().toString();
                int gen = mGender;
                String rec ="";
                if (gen == 1 || gen == 0){
                    rec = "no";
                }
                if (gen == 2){
                    rec = "yes";
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(AddpillsActivity.this);
                                builder.setMessage("Успешно!")
                                        .setNegativeButton("ура!!", null)
                                        .create()
                                        .show();
                                AddpillsActivity.this.finish();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(AddpillsActivity.this);
                                builder.setMessage("Ошибка при добавлении")
                                        .setNegativeButton("Попробуйте снова", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                AddPillRequest registorRequest = new AddPillRequest( id_pac, login, id1, login1, rec,id2, responseListener);
                RequestQueue queue = Volley.newRequestQueue(AddpillsActivity.this);
                queue.add(registorRequest);
            }
        });
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.enter, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {

            AddpillsActivity.this.finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
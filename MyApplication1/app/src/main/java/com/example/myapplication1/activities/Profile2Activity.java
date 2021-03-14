package com.example.myapplication1.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.myapplication1.R;
import com.example.myapplication1.help.Read12Request;
import com.example.myapplication1.help.Read1Request;
import com.example.myapplication1.help.ReadRequest;
import com.example.myapplication1.help.Save2Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class Profile2Activity extends AppCompatActivity {
    Calendar myCalendar = Calendar.getInstance();
    private Menu action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);
        EditText editText = findViewById(R.id.nameed1);
        EditText editText1 = findViewById(R.id.surned);
        EditText editText2 = findViewById(R.id.patred1);
        EditText editText3 = findViewById(R.id.birthh);
        editText.setFocusable(false);
        editText1.setFocusable(false);
        editText2.setFocusable(false);
        editText3.setFocusable(false);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        setTitle("Профиль");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Get(id);
        Get2(id);
        Get3(id);

        editText3.setFocusableInTouchMode(false);
        editText3.setFocusable(false);
        editText3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Profile2Activity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    private void Get3(int id) {
        Response.Listener<String> responseRequest = response -> {
            TextView textView = findViewById(R.id.clinic);
            TextView textView4 = findViewById(R.id.adrs);
            try {
                JSONObject jsonResponse = new JSONObject(response);
                boolean success = jsonResponse.getBoolean("success");

                if (success) {
                    String spec = jsonResponse.getString("name");
                    String staj1 = jsonResponse.getString("address");

                    textView.setText(spec);
                    textView4.setText(staj1);


                } else {

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        };

        Read12Request readRequest= new Read12Request(id, responseRequest);
        RequestQueue queue = Volley.newRequestQueue(Profile2Activity.this);
        queue.add(readRequest);
    }

    private void Get2( int id) {
        Response.Listener<String> responseRequest = response -> {
            TextView textView = findViewById(R.id.textView);
            TextView textView4 = findViewById(R.id.textView3);
            try {
                JSONObject jsonResponse = new JSONObject(response);
                boolean success = jsonResponse.getBoolean("success");

                if (success) {
                    String spec = jsonResponse.getString("speciality");
                    int staj1 = jsonResponse.getInt("staj");
                    String st = Integer.toString(staj1);

                    textView.setText(spec);
                    textView4.setText(st);


                } else {

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        };

        Read1Request readRequest= new Read1Request(id, responseRequest);
        RequestQueue queue = Volley.newRequestQueue(Profile2Activity.this);
        queue.add(readRequest);
    }

    private void Get(int id) {
        Response.Listener<String> responseRequest = response -> {
            TextView textView1 = findViewById(R.id.textView35);
            TextView textView3 = findViewById(R.id.textView37);
            TextView textView2 = findViewById(R.id.textView41);
            EditText editText = findViewById(R.id.nameed1);
            EditText editText1 = findViewById(R.id.surned);
            EditText editText2 = findViewById(R.id.patred1);
            EditText editText3 = findViewById(R.id.birthh);
            try {
                JSONObject jsonResponse = new JSONObject(response);
                boolean success = jsonResponse.getBoolean("success");

                if (success) {
                    String log = jsonResponse.getString("login");
                    String phone = jsonResponse.getString("phone");
                    String rol = jsonResponse.getString("email");
                    String nam = jsonResponse.getString("name");
                    String surnam = jsonResponse.getString("surname");
                    String patr = jsonResponse.getString("patronymic");
                    String birth = jsonResponse.getString("date_birth");

                    editText.setText(nam);
                    editText1.setText(surnam);
                    editText2.setText(patr);
                    editText3.setText(birth);
                    textView1.setText(log);
                    textView3.setText(rol);
                    textView2.setText(phone);


                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Profile2Activity.this);
                    builder.setMessage("Произошла ошибка!")
                            .setNegativeButton("Обратитесь к администратору", null)
                            .create()
                            .show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        };

        ReadRequest readRequest= new ReadRequest(id, responseRequest);
        RequestQueue queue = Volley.newRequestQueue(Profile2Activity.this);
        queue.add(readRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.enter, menu);
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
        EditText editText = findViewById(R.id.nameed1);
        EditText editText1 = findViewById(R.id.surned);
        EditText editText2 = findViewById(R.id.patred1);
        EditText editText3 = findViewById(R.id.birthh);

        switch (item.getItemId()) {
            case android.R.id.home:

                Profile2Activity.this.finish();

                return true;

            case R.id.menu_edit:

                editText.setFocusableInTouchMode(true);
                editText1.setFocusableInTouchMode(true);
                editText2.setFocusableInTouchMode(true);

                action.findItem(R.id.menu_edit).setVisible(false);
                action.findItem(R.id.menu_save).setVisible(true);

                return true;

            case R.id.menu_save:

                final String nam = editText.getText().toString();
                final String surn = editText1.getText().toString();
                final String patrr = editText2.getText().toString();
                final String data = editText3.getText().toString();
                Save(id, nam, surn, patrr, data);

                editText.setFocusable(false);
                editText1.setFocusable(false);
                editText2.setFocusable(false);
                editText3.setFocusable(false);

                action.findItem(R.id.menu_edit).setVisible(true);
                action.findItem(R.id.menu_save).setVisible(false);

                return true;

            default: return super.onOptionsItemSelected(item);
        }
    }

    private void Save(int id, String name, String surname, String patronymic, String date) {
        Response.Listener<String> responseRequest = response -> {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                boolean success = jsonResponse.getBoolean("success");

                if (success) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Profile2Activity.this);
                    builder.setMessage("Изменения сохранены.")
                            .setNegativeButton("ок", null)
                            .create()
                            .show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Profile2Activity.this);
                    builder.setMessage("Произошла ошибка!")
                            .setNegativeButton("Обратитесь к администратору", null)
                            .create()
                            .show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        };
        Save2Request readRequest= new Save2Request(id, name, surname, patronymic, date, responseRequest);
        RequestQueue queue = Volley.newRequestQueue(Profile2Activity.this);
        queue.add(readRequest);
    }

    DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {
        // TODO Auto-generated method stub
        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, monthOfYear);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        setBirth();
    };

    private void setBirth() {
        EditText editText3 = findViewById(R.id.birthh);
        String myFormat = "dd MM yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editText3.setText(sdf.format(myCalendar.getTime()));
    }
}

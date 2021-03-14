package com.example.myapplication1.activities;

import android.app.DatePickerDialog;
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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.myapplication1.ErrorDetector;
import com.example.myapplication1.R;
import com.example.myapplication1.help.RegistorRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    Calendar myCalendar = Calendar.getInstance();
    private Spinner mGenderSpinner;
    private int mGender = 0;
    public static final int GENDER_MALE = 1;
    public static final int GENDER_FEMALE = 2;
    private EditText rname;
    private EditText rpassw;
    private EditText rpassw2;
    private EditText email;
    private EditText phone;
    private EditText name;
    private EditText surname;
    private EditText patr;
    private EditText date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setTitle("Регистрация");

        rname  = findViewById(R.id.rname);
        rpassw  = findViewById(R.id.rpassw);
        rpassw2  = findViewById(R.id.rpassw2);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        name = findViewById(R.id.Name);
        surname = findViewById(R.id.surname);
        patr = findViewById(R.id.patr);
        date = findViewById(R.id.Date);
        final Button pac = findViewById(R.id.pac);
        final Button doc = findViewById(R.id.doc);
        mGenderSpinner = findViewById(R.id.gender2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        String sub = "@";

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setupSpinner();
        date.setFocusableInTouchMode(false);
        date.setFocusable(false);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(RegisterActivity.this, date1, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        pac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String passw = rpassw.getText().toString();
                final String passw2 = rpassw2.getText().toString();
                if (check()) {
                if (passw.equals(passw2)) {
                    final String email1 = email.getText().toString();
                    final String login = rname.getText().toString();
                    final String name1 = name.getText().toString();
                    final String surname1 = surname.getText().toString();
                    final String patr1 = patr.getText().toString();
                    final String phone1 = phone.getText().toString();
                    final String date1 = date.getText().toString();
                    final String role = "Пациент";

                    int gen = mGender;
                    String rec = "";
                    if (gen == 1) {
                        rec = "м";
                    }
                    if (gen == 2) {
                        rec = "ж";
                    }


                    Response.Listener<String> responseListener = new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");

                                if (success) {
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    RegisterActivity.this.startActivity(intent);
                                    Toast.makeText(RegisterActivity.this, "Успешно!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Пользователь с таким логином уже существует!", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    };

                    RegistorRequest registorRequest = new RegistorRequest(login, passw, name1, surname1, patr1, role, date1, rec, email1, phone1, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                    queue.add(registorRequest);
                }
            }
                else {
                    Toast.makeText(RegisterActivity.this, "Введенные пароли не совпадают!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String passw = rpassw.getText().toString();
                final String passw2 = rpassw2.getText().toString();
                if (check()) {
                    if (passw.equals(passw2)) {
                        final String login = rname.getText().toString();
                        final String name1 = name.getText().toString();
                        final String surname1 = surname.getText().toString();
                        final String patr1 = patr.getText().toString();
                        final String email1 = email.getText().toString();
                        final String phone1 = phone.getText().toString();
                        final String date1 = date.getText().toString();
                        final String role = "Доктор";
                        int gen = mGender;
                        String rec = "";
                        if (gen == 1) {
                            rec = "м";
                        }
                        if (gen == 2) {
                            rec = "ж";
                        }

                        Response.Listener<String> responseListener = new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = jsonResponse.getBoolean("success");

                                    if (success) {
                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        RegisterActivity.this.startActivity(intent);
                                        Toast.makeText(RegisterActivity.this, "Успешно!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Пользователь с таким логином уже существует!", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        };

                        RegistorRequest registorRequest = new RegistorRequest(login, passw, name1, surname1, patr1, role, date1, rec, email1, phone1, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                        queue.add(registorRequest);
                    }
                }else {
                    Toast.makeText(RegisterActivity.this, "Введенные пароли не совпадают!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    DatePickerDialog.OnDateSetListener date1 = (view, year, monthOfYear, dayOfMonth) -> {
        // TODO Auto-generated method stub
        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, monthOfYear);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        setBirth();
    };

    private void setBirth() {
        EditText editText3 = findViewById(R.id.Date);
        String myFormat = "dd MM yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editText3.setText(sdf.format(myCalendar.getTime()));
    }

    private void setupSpinner(){
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.array_gender_options1, android.R.layout.simple_spinner_item);
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mGenderSpinner.setAdapter(genderSpinnerAdapter);

        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_male))) {
                        mGender = GENDER_MALE;
                    } else if (selection.equals(getString(R.string.gender_female))) {
                        mGender = GENDER_FEMALE;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender = 0;
            }
        });
    }

    private boolean check() {
        ErrorDetector ed = new ErrorDetector();
        return (ed.lengthCheckMax(rname, 20) && ed.lengthCheckMin(rname, 3)
                && ed.lengthCheckMin(rpassw, 6) && ed.lengthCheckMax(rpassw, 15)
                && ed.lengthCheckMax(email, 30) && ed.isNotEmpty(email) && ed.lengthCheckMin(phone, 11) && ed.lengthCheckMax(phone, 12)
                && ed.isNotEmpty(date) && ed.isNotEmpty(name) && ed.isNotEmpty(surname) && ed.isNotEmpty(phone));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.enter, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {

            RegisterActivity.this.finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
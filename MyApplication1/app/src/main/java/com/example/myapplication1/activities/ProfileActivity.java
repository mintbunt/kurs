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
import com.example.myapplication1.help.Read21Request;
import com.example.myapplication1.help.ReadRequest;
import com.example.myapplication1.help.Save21Request;
import com.example.myapplication1.help.Save2Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    Calendar myCalendar = Calendar.getInstance();
      private Menu action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        EditText editText3 = findViewById(R.id.birth1);
        EditText editText6 = findViewById(R.id.polis);
        EditText editText4 = findViewById(R.id.ves);
        EditText editText5 = findViewById(R.id.rost);
        EditText editText = findViewById(R.id.nameed);
        EditText editText1 = findViewById(R.id.surmed);
        EditText editText2 = findViewById(R.id.patred);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("Профиль");

        editText3.setFocusableInTouchMode(false);
        editText3.setFocusable(false);
        editText3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ProfileActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        getSupportActionBar().setTitle("Профиль");

        editText.setFocusable(false);
        editText1.setFocusable(false);
        editText2.setFocusable(false);
        editText3.setFocusable(false);
        editText4.setFocusable(false);
        editText5.setFocusable(false);
        editText6.setFocusable(false);

        getData(id);
        getData2(id);

    }

    private void getData2(int id) {
        Response.Listener<String> responseRequest = response -> {
            EditText editText6 = findViewById(R.id.polis);
            EditText editText4 = findViewById(R.id.ves);
            EditText editText5 = findViewById(R.id.rost);
            try {
                JSONObject jsonResponse = new JSONObject(response);
                boolean success = jsonResponse.getBoolean("success");
                if (success) {
                    int ves = jsonResponse.getInt("weight");
                    int rost = jsonResponse.getInt("height");
                    String polis = jsonResponse.getString("polis");
                    String st = Integer.toString(ves);
                    String st1 = Integer.toString(rost);
                    editText4.setText(st);
                    editText5.setText(st1);
                    editText6.setText(polis);
                    editText4.setFocusable(false);
                    editText5.setFocusable(false);
                    editText6.setFocusable(false);
                } else {
                    Intent intent = new Intent(ProfileActivity.this, Rega2Activity.class);
                    intent.putExtra("id", id);
                    ProfileActivity.this.startActivity(intent);
                    ProfileActivity.this.finish();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        };

        Read21Request read2Request = new Read21Request(id, responseRequest);
        RequestQueue queue = Volley.newRequestQueue(ProfileActivity.this);
        queue.add(read2Request);
    }

    private void getData(int id) {
        Response.Listener<String> responseRequest = response -> {
            TextView textView1 = findViewById(R.id.log1);
            TextView textView3 = findViewById(R.id.pp);
            TextView textView2 = findViewById(R.id.tell);
            EditText editText = findViewById(R.id.nameed);
            EditText editText1 = findViewById(R.id.surmed);
            EditText editText2 = findViewById(R.id.patred);
            EditText editText3 = findViewById(R.id.birth1);
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

                    editText.setFocusable(false);
                    editText1.setFocusable(false);
                    editText2.setFocusable(false);
                    editText3.setFocusable(false);

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                    builder.setMessage("Произошла ошибка!")
                            .setNegativeButton("Обратитесь к администратору", null)
                            .create()
                            .show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        };

        ReadRequest read2Request = new ReadRequest(id, responseRequest);
        RequestQueue queue = Volley.newRequestQueue(ProfileActivity.this);
        queue.add(read2Request);
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
        EditText editText = findViewById(R.id.nameed);
        EditText editText1 = findViewById(R.id.surmed);
        EditText editText2 = findViewById(R.id.patred);
        EditText editText3 = findViewById(R.id.birth1);
        EditText editText6 = findViewById(R.id.polis);
        EditText editText4 = findViewById(R.id.ves);
        EditText editText5 = findViewById(R.id.rost);

        switch (item.getItemId()) {
            case android.R.id.home:

                ProfileActivity.this.finish();
                return true;
            case R.id.menu_edit:

                editText.setFocusableInTouchMode(true);
                editText1.setFocusableInTouchMode(true);
                editText2.setFocusableInTouchMode(true);
                editText4.setFocusableInTouchMode(true);
                editText5.setFocusableInTouchMode(true);
                editText6.setFocusableInTouchMode(true);

                action.findItem(R.id.menu_edit).setVisible(false);
                action.findItem(R.id.menu_save).setVisible(true);

                return true;

            case R.id.menu_save:

                final String nam = editText.getText().toString();
                final String surn = editText1.getText().toString();
                final String patrr = editText2.getText().toString();
                final String data = editText3.getText().toString();
                final String ves = editText4.getText().toString();
                int vess = Integer.parseInt(ves);
                final String rostt = editText5.getText().toString();
               int rost = Integer.parseInt(rostt);
                final String poliss = editText6.getText().toString();

                Save(id, nam, surn, patrr, data);
                Save2(id, poliss, vess, rost);

                editText.setFocusable(false);
                editText1.setFocusable(false);
                editText2.setFocusable(false);
                editText3.setFocusable(false);
                editText4.setFocusable(false);
                editText5.setFocusable(false);
                editText6.setFocusable(false);

                action.findItem(R.id.menu_edit).setVisible(true);
                action.findItem(R.id.menu_save).setVisible(false);

                return true;

            default: return super.onOptionsItemSelected(item);
        }
    }
        private void Save2(int id, String polis, int weight, int height) {
            Response.Listener<String> responseRequest = response -> {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                        builder.setMessage("Изменения сохранены.")
                                .setNegativeButton("ок", null)
                                .create()
                                .show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                        builder.setMessage("Произошла ошибка!")
                                .setNegativeButton("Обратитесь к администратору", null)
                                .create()
                                .show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            };
            Save21Request readRequest= new Save21Request(id, polis, weight, height, responseRequest);
            RequestQueue queue = Volley.newRequestQueue(ProfileActivity.this);
            queue.add(readRequest);
        }

    private void Save(int id, String name, String surname, String patronymic, String date) {
        Response.Listener<String> responseRequest = response -> {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                boolean success = jsonResponse.getBoolean("success");

                if (success) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                    builder.setMessage("Изменения сохранены.")
                            .setNegativeButton("ок", null)
                            .create()
                            .show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
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
        RequestQueue queue = Volley.newRequestQueue(ProfileActivity.this);
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
        EditText editText3 = findViewById(R.id.birth1);
        String myFormat = "dd MM yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editText3.setText(sdf.format(myCalendar.getTime()));
    }
}
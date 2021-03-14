package com.example.myapplication1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class ProfpacActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profpac);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        int id1 = intent.getIntExtra("id", 0);

        setTitle("Профиль");


        getData(id1);
        getData2(id1);

    }
    private void getData2(int id) {
        Response.Listener<String> responseRequest = response -> {
            TextView editText6 = findViewById(R.id.polis2);
            TextView editText4 = findViewById(R.id.ves2);
            TextView editText5 = findViewById(R.id.rost2);
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
                } else {
                    String st = "No info";
                    editText4.setText(st);
                    editText5.setText(st);
                    editText6.setText(st);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        };

        Read21Request read2Request = new Read21Request(id, responseRequest);
        RequestQueue queue = Volley.newRequestQueue(ProfpacActivity.this);
        queue.add(read2Request);
    }

    private void getData(int id) {
        Response.Listener<String> responseRequest = response -> {
            TextView textView1 = findViewById(R.id.log12);
            TextView textView3 = findViewById(R.id.pp1);
            TextView textView2 = findViewById(R.id.tell2);
            TextView editText3 = findViewById(R.id.birth12);
            TextView editText = findViewById(R.id.nameed2);
            TextView editText1 = findViewById(R.id.surmed2);
            TextView editText2 = findViewById(R.id.patred2);
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProfpacActivity.this);
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
        RequestQueue queue = Volley.newRequestQueue(ProfpacActivity.this);
        queue.add(read2Request);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.enter, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {

            ProfpacActivity.this.finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
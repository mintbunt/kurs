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
import com.example.myapplication1.help.Read12Request;
import com.example.myapplication1.help.Read1Request;
import com.example.myapplication1.help.ReadRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class ProfdocActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profdoc);

        Intent intent = getIntent();
        int id1 = intent.getIntExtra("id", 0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Профиль");

        Get(id1);
        Get2(id1);
        Get3(id1);

    }

    private void Get(int id1) {
        Response.Listener<String> responseRequest = response -> {
            TextView textView1 = findViewById(R.id.textView354);
            TextView textView3 = findViewById(R.id.textView374);
            TextView textView2 = findViewById(R.id.textView414);
            TextView editText = findViewById(R.id.nameed14);
            TextView editText1 = findViewById(R.id.surned4);
            TextView editText2 = findViewById(R.id.patred14);
            TextView editText3 = findViewById(R.id.birthh4);
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProfdocActivity.this);
                    builder.setMessage("Произошла ошибка!")
                            .setNegativeButton("Обратитесь к администратору", null)
                            .create()
                            .show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        };

        ReadRequest readRequest= new ReadRequest(id1, responseRequest);
        RequestQueue queue = Volley.newRequestQueue(ProfdocActivity.this);
        queue.add(readRequest);
    }

    private void Get2(int id1) {

        Response.Listener<String> responseRequest = response -> {
            TextView textView = findViewById(R.id.textView444);
            TextView textView4 = findViewById(R.id.textView344);
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProfdocActivity.this);
                    builder.setMessage("Произошла ошибка!")
                            .setNegativeButton("Обратитесь к администратору", null)
                            .create()
                            .show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        };

        Read1Request readRequest= new Read1Request(id1, responseRequest);
        RequestQueue queue = Volley.newRequestQueue(ProfdocActivity.this);
        queue.add(readRequest);
    }

    private void Get3(int id1) {
        Response.Listener<String> responseRequest = response -> {
            TextView textView = findViewById(R.id.clinic1);
            TextView textView4 = findViewById(R.id.adrs1);
            try {
                JSONObject jsonResponse = new JSONObject(response);
                boolean success = jsonResponse.getBoolean("success");

                if (success) {
                    String spec = jsonResponse.getString("name");
                    String staj1 = jsonResponse.getString("address");

                    textView.setText(spec);
                    textView4.setText(staj1);


                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProfdocActivity.this);
                    builder.setMessage("Произошла ошибка!")
                            .setNegativeButton("Обратитесь к администратору", null)
                            .create()
                            .show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        };

        Read12Request readRequest= new Read12Request(id1, responseRequest);
        RequestQueue queue = Volley.newRequestQueue(ProfdocActivity.this);
        queue.add(readRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.enter, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {

            ProfdocActivity.this.finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
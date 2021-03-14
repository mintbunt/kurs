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
import com.example.myapplication1.help.Readpill2Request;
import com.example.myapplication1.help.ReadpillRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class GPPActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_p_p);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("Лекарство");


        Intent intent = getIntent();
        int id1 = intent.getIntExtra("id", 0);
        int id2 = intent.getIntExtra("id_pac", 0);
        Get(id1);
        Get2(id1);
    }

    private void Get2( int id1) {
        Response.Listener<String> responseRequest = response -> {
            TextView editText = findViewById(R.id.fio);
            TextView editText1 = findViewById(R.id.who);

            try {
                JSONObject jsonResponse = new JSONObject(response);
                boolean success = jsonResponse.getBoolean("success");

                if (success) {
                    String nam = jsonResponse.getString("name");
                    String surnam = jsonResponse.getString("surname");
                    String patr = jsonResponse.getString("patronymic");
                    String spec = jsonResponse.getString("speciality");
                    String fio = nam + ' ' + surnam + ' ' + patr;
                    editText.setText(fio);
                    editText1.setText(spec);

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(GPPActivity.this);
                    builder.setMessage("Произошла ошибка!")
                            .setNegativeButton("Обратитесь к администратору", null)
                            .create()
                            .show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        };

        Readpill2Request readRequest = new Readpill2Request( id1, responseRequest);
        RequestQueue queue = Volley.newRequestQueue(GPPActivity.this);
        queue.add(readRequest);
    }

    private void Get(int id) {
        Response.Listener<String> responseRequest = response -> {
            TextView editText = findViewById(R.id.namep);
            TextView editText1 = findViewById(R.id.dozp);
            TextView editText2 = findViewById(R.id.recp);

            try {
                JSONObject jsonResponse = new JSONObject(response);
                boolean success = jsonResponse.getBoolean("success");

                if (success) {
                    String nam = jsonResponse.getString("name");
                    String surnam = jsonResponse.getString("doz");
                    String receipt = jsonResponse.getString("receipt");

                    editText.setText(nam);
                    editText1.setText(surnam);
                    editText2.setText(receipt);

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(GPPActivity.this);
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
        RequestQueue queue = Volley.newRequestQueue(GPPActivity.this);
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

            GPPActivity.this.finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
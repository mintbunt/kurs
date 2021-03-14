package com.example.myapplication1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.myapplication1.R;
import com.example.myapplication1.help.AddZabRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class AddZabolActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_zabol);

        final EditText namez = findViewById(R.id.name_zab);
        final Button addzab = findViewById(R.id.addzab);

        Intent intent = getIntent();
        int id1 = intent.getIntExtra("id", 0);
        setTitle("Добавить диагноз");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        addzab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String login = namez.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(AddZabolActivity.this);
                                builder.setMessage("Успешно!")
                                        .setNegativeButton("ура!!", null)
                                        .create()
                                        .show();
                                AddZabolActivity.this.finish();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(AddZabolActivity.this);
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

                AddZabRequest registorRequest = new AddZabRequest(id1, login, responseListener);
                RequestQueue queue = Volley.newRequestQueue(AddZabolActivity.this);
                queue.add(registorRequest);
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

            AddZabolActivity.this.finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
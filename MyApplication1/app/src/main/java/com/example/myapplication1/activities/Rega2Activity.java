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
import com.example.myapplication1.help.Rega2Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Rega2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rega2);

        final EditText poliss  = findViewById(R.id.pol1);
        final EditText ves  = findViewById(R.id.wei1);
        final EditText rost = findViewById(R.id.hei1);
        final Button reg = findViewById(R.id.reg2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("Дополнительная информация");

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String login = poliss.getText().toString();
                final String passw = ves.getText().toString();
                final String name1 = rost.getText().toString();
                int ve = Integer.parseInt(passw);
                int ro = Integer.parseInt(name1);

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Rega2Activity.this);
                                builder.setMessage("Успешно!")
                                        .setNegativeButton("ура!!", null)
                                        .create()
                                        .show();
                                Rega2Activity.this.finish();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Rega2Activity.this);
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

                Rega2Request registorRequest = new Rega2Request(id, login, ve, ro, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Rega2Activity.this);
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

            Rega2Activity.this.finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
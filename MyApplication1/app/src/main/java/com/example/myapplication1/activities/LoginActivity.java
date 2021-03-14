package com.example.myapplication1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.myapplication1.R;
import com.example.myapplication1.help.LoginRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);

        usernameEditText.setFocusable(true);

        setTitle("Добро пожаловать!");

        final TextView reg = findViewById(R.id.reg);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(regIntent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String login = usernameEditText.getText().toString();
                final String passw = passwordEditText.getText().toString();

                Response.Listener<String> responseRequest = response -> {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");

                        if (success){

                            String rol = jsonResponse.getString("role");
                            String nam = jsonResponse.getString("name");
                            String surnam = jsonResponse.getString("surname");
                            String patr = jsonResponse.getString("patronymic");
                            String phone = jsonResponse.getString("phone");
                            int id = jsonResponse.getInt("id");
                           // String id1 = Integer.toString(id);


                            switch (rol) {
                                case "Доктор":
                                    Intent intent = new Intent(LoginActivity.this, Enter2Activity.class);
                                    intent.putExtra("login", login);
                                    intent.putExtra("role", rol);
                                    intent.putExtra("name", nam);
                                    intent.putExtra("surname", surnam);
                                    intent.putExtra("patronymic", patr);
                                    intent.putExtra("phone", phone);
                                    intent.putExtra("id", id);
                                    LoginActivity.this.startActivity(intent);
                                    LoginActivity.this.finish();
                                     break;
                                case "Пациент":
                                    Intent intent1= new Intent(LoginActivity.this, EnterActivity.class);
                                    intent1.putExtra("login", login);
                                    intent1.putExtra("role", rol);
                                    intent1.putExtra("name", nam);
                                    intent1.putExtra("surname", surnam);
                                    intent1.putExtra("patronymic", patr);
                                    intent1.putExtra("phone", phone);
                                    intent1.putExtra("id", id);
                                    LoginActivity.this.startActivity(intent1);
                                    LoginActivity.this.finish();
                                    break;
                            }

                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setMessage("Неверный логин или пароль!")
                                    .setNegativeButton("Попробуйте снова", null)
                                    .create()
                                    .show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                };

                LoginRequest loginRequest = new LoginRequest(login, passw, responseRequest);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }

}
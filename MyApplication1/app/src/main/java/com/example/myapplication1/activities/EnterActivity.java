package com.example.myapplication1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.myapplication1.R;
import com.example.myapplication1.medicine.MedicineActivity;

public class EnterActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        final Button button = findViewById(R.id.button3);
        final Button button1 = findViewById(R.id.button4);
        final Button button2 = findViewById(R.id.button5);
        final Button button3 = findViewById(R.id.button6);
        Button button4 = findViewById(R.id.button9);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        /*String login = intent.getStringExtra("login");
        String role1 = intent.getStringExtra("role");
        String nam = intent.getStringExtra("name");
        String surnam = intent.getStringExtra("surname");
        String phone = intent.getStringExtra("phone");
        String fio = nam + " " + surnam;*/

        setTitle("Выберите действие:");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(EnterActivity.this, ChatuspacActivity.class);
                intent1.putExtra("id", id);
                startActivity(intent1);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(EnterActivity.this, ProfileActivity.class);
                intent1.putExtra("id", id);
                startActivity(intent1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(EnterActivity.this, TerapyActivity.class);
                intent1.putExtra("id", id);
                startActivity(intent1);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(EnterActivity.this, analizpacActivity.class);
                intent1.putExtra("id", id);
                startActivity(intent1);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(EnterActivity.this, MedicineActivity.class);
                startActivity(intent1);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       switch (item.getItemId()){
           case R.id.menuLogout:
               Intent intent1 = new Intent(EnterActivity.this, LoginActivity.class);
               EnterActivity.this.startActivity(intent1);
               EnterActivity.this.finish();
               break;
       }
       return true;
    }

}
package com.example.myapplication1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication1.R;

public class Enter2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter2);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

       /* String login = intent.getStringExtra("login");
        String role1 = intent.getStringExtra("role");
        String nam = intent.getStringExtra("name");
        String phone = intent.getStringExtra("phone");
        String surnam = intent.getStringExtra("surname");
        String fio = nam + " " + surnam;*/

        setTitle("Выберите:");

        Button button = findViewById(R.id.button2);
         Button button2 = findViewById(R.id.button8);
         Button button4 = findViewById(R.id.button10);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Enter2Activity.this, ListActivity.class);
                intent1.putExtra("id", id);
                startActivity(intent1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Enter2Activity.this, AnalizdocActivity.class);
                intent1.putExtra("id", id);
                startActivity(intent1);
            }
        });


        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Enter2Activity.this, Profile2Activity.class);
                intent1.putExtra("id", id);
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
                Intent intent1 = new Intent(Enter2Activity.this, LoginActivity.class);
                Enter2Activity.this.startActivity(intent1);
                Enter2Activity.this.finish();
                break;
        }
        return true;
    }
}
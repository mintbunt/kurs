package com.example.myapplication1.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication1.R;
import com.example.myapplication1.help.RequestHandler;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class GetimgActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonGetImage;
    private ImageView imageView;

    private RequestHandler requestHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getimg);
        buttonGetImage = (Button) findViewById(R.id.buttonGetImage);
        imageView = (ImageView) findViewById(R.id.imageViewShow);
        setTitle("Анализы");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        requestHandler = new RequestHandler();
        buttonGetImage.setOnClickListener(this);
    }

    private void getImage(String id) {
        class GetImage extends AsyncTask<String,Void, Bitmap> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(GetimgActivity.this, "Загружаем...", null,true,true);
            }

            @Override
            protected void onPostExecute(Bitmap b) {
                super.onPostExecute(b);
                loading.dismiss();
                imageView.setImageBitmap(b);
            }

            @Override
            protected Bitmap doInBackground(String... params) {
                String id = params[0];
                String add = "http://192.168.64.2/g/getimage.php?id="+id;
                URL url = null;
                Bitmap image = null;
                try {
                    url = new URL(add);
                    image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return image;
            }
        }

        GetImage gi = new GetImage();
        gi.execute(id);
    }
    @Override
    public void onClick(View v) {
        Intent intent = getIntent();
        String id1 = intent.getStringExtra("id");
        getImage(id1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.enter, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            GetimgActivity.this.finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
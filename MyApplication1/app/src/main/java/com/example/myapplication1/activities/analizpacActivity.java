package com.example.myapplication1.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication1.R;
import com.example.myapplication1.help.RequestHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class analizpacActivity extends AppCompatActivity  implements View.OnClickListener {
    public static final String UPLOAD_URL = "http://192.168.64.2/g/upload_image.php";
    public static final String UPLOAD_KEY = "image";
    public static final String TAG = "MY MESSAGE";

    private int PICK_IMAGE_REQUEST = 1;

    private Button buttonChoose;
    private Button buttonUpload;
    private Button buttonView;

    private EditText nameofpic;

    private ImageView imageView;

    private Bitmap bitmap;

    private Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analizpac);

                buttonChoose = (Button) findViewById(R.id.buttonChoose);
                buttonUpload = (Button) findViewById(R.id.buttonUpload);
                nameofpic = (EditText) findViewById(R.id.namepic);
                buttonView = (Button) findViewById(R.id.buttonViewImage);

                imageView = (ImageView) findViewById(R.id.imageView);

                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
                setTitle("??????????????");

                buttonUpload.setEnabled(false);

                buttonChoose.setOnClickListener(this);
                buttonUpload.setOnClickListener(this);
                 buttonView.setOnClickListener(this);
            }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.enter, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            analizpacActivity.this.finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

            private void showFileChooser() {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "???????????????? ??????????????????????"), PICK_IMAGE_REQUEST);
            }

            @Override
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);

                if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
                    buttonUpload.setEnabled(true);
                    filePath = data.getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                        imageView.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            public String getStringImage(Bitmap bmp){
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageBytes = baos.toByteArray();
                String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                return encodedImage;
            }

            private void uploadImage(int id, String name){
                class UploadImage extends AsyncTask<Bitmap,Void,String> {

                    ProgressDialog loading;
                    RequestHandler rh = new RequestHandler();

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        loading = ProgressDialog.show(analizpacActivity.this, "?????????????????? ??????????????????????", "??????????????????...",true,true);
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        loading.dismiss();
                        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    protected String doInBackground(Bitmap... params) {
                        Bitmap bitmap = params[0];
                        String uploadImage = getStringImage(bitmap);

                        HashMap<String,String> data = new HashMap<>();
                        data.put(UPLOAD_KEY, uploadImage);
                        data.put("id", id + "");
                        data.put("name", name);

                        String result = rh.sendPostRequest(UPLOAD_URL,data);

                        return result;
                    }
                }

                UploadImage ui = new UploadImage();
                ui.execute(bitmap);
            }

            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
               int id1 = intent.getIntExtra("id", 0);
                nameofpic = (EditText) findViewById(R.id.namepic);
                String nam = nameofpic.getText().toString();
                if (v == buttonChoose) {
                    showFileChooser();
                }
                if(v == buttonUpload){
                    if (!nam.equals("")){
                    uploadImage(id1, nam);
                    }
                    else {
                        Toast.makeText(analizpacActivity.this, "?????????????? ????????????????????????!", Toast.LENGTH_SHORT).show();
                    }
                }
                if(v == buttonView){
                    Intent intent1 = new Intent(analizpacActivity.this, ListimgActivity.class);
                    intent1.putExtra("id", id1);
                    startActivity(intent1);
                }
            }
        }
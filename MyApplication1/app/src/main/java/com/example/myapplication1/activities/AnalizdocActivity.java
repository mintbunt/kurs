package com.example.myapplication1.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication1.R;
import com.example.myapplication1.help.MyAdapter;
import com.example.myapplication1.help.pacients;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AnalizdocActivity extends AppCompatActivity {

    ListView listView;
    MyAdapter adapter;
    public static ArrayList<pacients> employeeArrayList = new ArrayList<>();
    String url = "http://192.168.64.2/g/retrievelist.php";
    private Map<String, String> params;
    pacients pacient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analizdoc);

        Intent intent = getIntent();
        int id1 = intent.getIntExtra("id", 0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("Выберите:");

        listView = findViewById(R.id.myListView);
        adapter = new MyAdapter(this,employeeArrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogItem = {"Анализы пациента", "Терапия пациента", "Профиль пациента"};
                builder.setTitle(employeeArrayList.get(position).getName());
                 pacients list_item = employeeArrayList.get(position);
                builder.setItems(dialogItem, (dialog, i) -> {
                    switch (i) {
                        case 0:
                            Intent intent1 = new Intent(AnalizdocActivity.this, ListimgActivity.class);
                            intent1.putExtra("id", Integer.parseInt(list_item.getId()));
                            startActivity(intent1);
                            break;
                        case 1:
                            Intent intent2 = new Intent(AnalizdocActivity.this, Terapy2Activity.class);
                            intent2.putExtra("id", Integer.parseInt(list_item.getId()));
                            intent2.putExtra("id_doc", id1);
                            startActivity(intent2);
                            break;
                        case 2:
                            Intent intent3 = new Intent(AnalizdocActivity.this, ProfpacActivity.class);
                            intent3.putExtra("id", Integer.parseInt(list_item.getId()));
                            startActivity(intent3);
                            break;
                    }
                });
                builder.create().show();
        });
        retrieveData(id1);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.enter, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            AnalizdocActivity.this.finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void retrieveData(int id1) {
        StringRequest request = new StringRequest(Request.Method.POST, url,
                response -> {
                    employeeArrayList.clear();
                    try{
                        JSONObject jsonObject = new JSONObject(response);
                        String sucess = jsonObject.getString("success");
                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        if(sucess.equals("1")){

                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject object = jsonArray.getJSONObject(i);



                                String id = object.getString("id");
                                String name = object.getString("name");
                                String surn = object.getString("surname");
                                String patr = object.getString("patronymic");

                                pacient = new pacients(id,name,surn,patr);
                                employeeArrayList.add(pacient);
                                adapter.notifyDataSetChanged();

                            }
                        }else {

                        }
                    }
                    catch (JSONException e){
                        e.printStackTrace();
                    }
                },  new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // Toast.makeText(AnalizdocActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }){
            @Override
            public Map<String, String> getParams() {
                params = new HashMap<>();
                params.put("id", id1 + "");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(AnalizdocActivity.this);
        requestQueue.add(request);
    }

}
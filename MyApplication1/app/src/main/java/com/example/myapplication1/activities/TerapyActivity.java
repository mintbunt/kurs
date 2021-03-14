package com.example.myapplication1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication1.R;
import com.example.myapplication1.help.Adapter3;
import com.example.myapplication1.help.zabol;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TerapyActivity extends AppCompatActivity {
    ListView listView;
    Adapter3 adapter;
    public static ArrayList<zabol> employeeArrayList = new ArrayList<>();
    String url = "http://192.168.64.2/g/retrievelistzab.php";
    private Map<String, String> params;
    zabol zabol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terapy);

        Intent intent = getIntent();
        int id1 = intent.getIntExtra("id", 0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("Выберите:");

        listView = findViewById(R.id.Listv2);
        adapter = new Adapter3(this,employeeArrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {

            zabol list_item = employeeArrayList.get(position);
            Intent intent1 = new Intent(TerapyActivity.this, PPActivity.class);
            intent1.putExtra("id", Integer.parseInt(list_item.getId11()));
            intent1.putExtra("id_pac", id1);
            startActivity(intent1);

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

            TerapyActivity.this.finish();

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
                                String name = object.getString("name_zab");

                                zabol = new zabol(name,id);
                                employeeArrayList.add(zabol);
                                adapter.notifyDataSetChanged();

                            }
                        }else {
                            Toast.makeText(TerapyActivity.this, "Пока не добавлено ни одного диагноза", Toast.LENGTH_SHORT).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(TerapyActivity.this);
        requestQueue.add(request);
    }
}
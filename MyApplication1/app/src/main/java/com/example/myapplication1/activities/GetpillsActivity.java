package com.example.myapplication1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import com.example.myapplication1.help.Adapter4;
import com.example.myapplication1.help.pills;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GetpillsActivity extends AppCompatActivity {
    ListView listView;
    Adapter4 adapter;
    public static ArrayList<pills> employeeArrayList = new ArrayList<>();
    String url = "http://192.168.64.2/g/retrievelistpill.php";
    private Map<String, String> params;
    pills pills;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getpills);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("Выберите:");

        Intent intent = getIntent();
        int id1 = intent.getIntExtra("id", 0);
        int id_pac = intent.getIntExtra("id_pac", 0);
        int id2 = intent.getIntExtra("id_doc", 0);

        FloatingActionButton fab = findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(GetpillsActivity.this, AddpillsActivity.class);
                intent1.putExtra("id", id1);
                intent1.putExtra("id_pac", id_pac);
                intent1.putExtra("id_doc", id2);
                GetpillsActivity.this.startActivity(intent1);
                GetpillsActivity.this.finish();
            }
        });

        listView = findViewById(R.id.Listv1);
        adapter = new Adapter4(this,employeeArrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {

            pills list_item = employeeArrayList.get(position);
            Intent intent1 = new Intent(GetpillsActivity.this, PillsActivity.class);
            intent1.putExtra("id", Integer.parseInt(list_item.getIdPill()));
            startActivity(intent1);
            GetpillsActivity.this.finish();

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

            GetpillsActivity.this.finish();

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

                                pills = new pills(name,id);
                                employeeArrayList.add(pills);
                                adapter.notifyDataSetChanged();

                            }
                        }else {
                            Toast.makeText(GetpillsActivity.this, "Пока не добавлено ни одного препарата", Toast.LENGTH_SHORT).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(GetpillsActivity.this);
        requestQueue.add(request);
    }
}
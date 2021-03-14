package com.example.myapplication1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication1.R;
import com.example.myapplication1.help.Adapter2;
import com.example.myapplication1.help.Analys;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ListimgActivity extends AppCompatActivity {
    ListView listView;
    Adapter2 adapter;
    public static ArrayList<Analys> employeeArrayList = new ArrayList<>();
    String url = "http://192.168.64.2/g/retrieveimglist.php";
    private Map<String, String> params;
    Analys analys;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listimg);

        Intent intent = getIntent();
        int id1 = intent.getIntExtra("id",0);
        setTitle("Анализы");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.ListView);
        adapter = new Adapter2(this,employeeArrayList);
        listView.setAdapter(adapter);
          listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

            Analys list_item = employeeArrayList.get(position);
            Intent intent1 = new Intent(ListimgActivity.this, GetimgActivity.class);
            intent1.putExtra("id", list_item.getId1());
            startActivity(intent1);
          }
         });

        retrieveData(id1);
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

                                analys = new Analys(id,name);
                                employeeArrayList.add(analys);
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
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.enter, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            ListimgActivity.this.finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
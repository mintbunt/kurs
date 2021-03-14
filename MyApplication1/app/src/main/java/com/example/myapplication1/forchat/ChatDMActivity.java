package com.example.myapplication1.forchat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication1.forchat.Adaptadores.AdapterMensajes;
import com.example.myapplication1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatDMActivity extends AppCompatActivity {
    Usuario usuario;
    ArrayList<Usuario> list = new ArrayList<>();
    ArrayList<Message> listaMensajes = new ArrayList<Message>();
    Message mes;
    RecyclerView rvMensajes;

    EditText etTexto;
    private Map<String, String> params;
    Button btnEnviar, btnRecargar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_dm);

        Intent intent = getIntent();
        int id1 = intent.getIntExtra("id", 0);
        int idDest = intent.getIntExtra("userDest", 0);

            rvMensajes = findViewById(R.id.rvMensajes);
            rvMensajes.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));

            etTexto = findViewById(R.id.etTexto);

            btnEnviar = findViewById(R.id.btnEnviar);
            btnRecargar = findViewById(R.id.btnRecargar);

            setTitle("Чат");

            obtenerMensajes(id1,idDest);

            btnEnviar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(etTexto.getText().toString().isEmpty()) {
                        Toast.makeText(ChatDMActivity.this, "Вы забыл написать сообщение.", Toast.LENGTH_LONG).show();
                    } else {
                        enviarMensaje(id1,idDest);
                    }
                }
            });

            btnRecargar.setOnClickListener(v -> obtenerMensajes(id1,idDest));
        }

    public void enviarMensaje(int id1, int idDest) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, getResources().getString(R.string.URL_ENVIAR_MENSAJE),
                    new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            // En este apartado se programa lo que deseamos hacer en caso de no haber errores
                            Toast.makeText(ChatDMActivity.this, response, Toast.LENGTH_LONG).show();
                            obtenerMensajes(id1,idDest);
                            etTexto.setText("");
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // En caso de tener algun error en la obtencion de los datos
                    Toast.makeText(ChatDMActivity.this, "Ошибка", Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                public Map<String, String> getParams() {
                    params = new HashMap<>();
                    params.put("id", id1 + "");
                    params.put("userDest", idDest + "");
                    params.put("message", etTexto.getText().toString());
                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }

        public void getUsers( int id1, int idDest) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, getResources().getString(R.string.URL_USUARIOS),
                    response -> {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String sucess = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if(sucess.equals("1")) {

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String id = object.getString("id");
                                    String name = object.getString("name");
                                    String surn = object.getString("surname");
                                    String patr = object.getString("patronymic");

                                    usuario = new Usuario(id, name, surn, patr);
                                    list.add(usuario);
                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }, error -> {
                Toast.makeText(ChatDMActivity.this, "Ошибка", Toast.LENGTH_LONG).show();
            }) {
                @Override
                public Map<String, String> getParams() {
                    params = new HashMap<>();
                    params.put("id", id1 + "");
                    params.put("userDest", idDest + "");
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }

        public void obtenerMensajes(int id1, int idDest) {
            listaMensajes.clear();
            String id2 = Integer.toString(id1);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, getResources().getString(R.string.URL_OBTENER_MENSAJES),
                    response -> {
                        try{
                                            JSONObject jsonObject = new JSONObject(response);
                                            String sucess = jsonObject.getString("success");
                                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                                            if(sucess.equals("1")) {

                                                for (int i = 0; i < jsonArray.length(); i++) {
                                                    JSONObject object = jsonArray.getJSONObject(i);
                                                    String name1 = object.getString("name");
                                                    String patr1 = object.getString("patronymic");
                                                    String id = object.getString("idMes");
                                                    String name = object.getString("message");
                                                    String surn = object.getString("userOrig");
                                                    String patr = object.getString("userDest");

                                                    mes = new Message(id, name, surn, patr, name1, patr1);
                                                    listaMensajes.add(mes);
                                                }
                                            }
                            rvMensajes.smoothScrollToPosition (listaMensajes.size());
                            AdapterMensajes adaptador = new AdapterMensajes(ChatDMActivity.this, list, id2, listaMensajes);
                            rvMensajes.setAdapter(adaptador);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }, error -> {
                        Toast.makeText(ChatDMActivity.this, "Ошибка", Toast.LENGTH_LONG).show();
                    }){
                @Override
                public Map<String, String> getParams() {
                    params = new HashMap<>();
                    params.put("id", id1 + "");
                    params.put("userDest", idDest + "");
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }
package com.example.myapplication1.help;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Readpill2Request extends StringRequest {
    private static final String logrequrl = "http://192.168.64.2/g/readpilldoc.php";
    private Map<String, String> params;

    public Readpill2Request(int id, Response.Listener<String> listener){
        super(Method.POST, logrequrl, listener, null);
        params = new HashMap<>();
       // params.put("id_pac", id_pac + "");
        params.put("id", id + "");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

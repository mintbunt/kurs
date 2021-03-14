package com.example.myapplication1.help;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SavepillRequest extends StringRequest {
    private static final String logrequrl = "http://192.168.64.2/g/safepill.php";
    private Map<String, String> params;

    public SavepillRequest(int id, String name, String doz, String rec, Response.Listener<String> listener){
        super(Request.Method.POST, logrequrl, listener, null);
        params = new HashMap<>();
        params.put("id", id + "");
        params.put("name", name);
        params.put("doz", doz);
        params.put("receipt", rec);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
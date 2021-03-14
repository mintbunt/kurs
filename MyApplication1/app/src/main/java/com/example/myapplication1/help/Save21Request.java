package com.example.myapplication1.help;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Save21Request extends StringRequest {
    private static final String logrequrl = "http://192.168.64.2/g/safe_info21.php";
    private Map<String, String> params;

    public Save21Request(int id, String polis, int weight, int height,  Response.Listener<String> listener){
        super(Request.Method.POST, logrequrl, listener, null);
        params = new HashMap<>();
        params.put("id", id + "");
        params.put("polis", polis);
        params.put("weight", weight + "");
        params.put("height", height + "");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

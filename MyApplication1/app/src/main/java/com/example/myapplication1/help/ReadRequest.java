package com.example.myapplication1.help;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ReadRequest extends StringRequest {
    private static final String logrequrl = "http://192.168.64.2/g/read_info.php";
    private Map<String, String> params;

    public ReadRequest(int id, Response.Listener<String> listener){
        super(Request.Method.POST, logrequrl, listener, null);
        params = new HashMap<>();
        params.put("id", id + "");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

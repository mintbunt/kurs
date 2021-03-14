package com.example.myapplication1.help;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DeletepillRequest extends StringRequest {

    private static final String regrequrl = "http://192.168.64.2/g/deletepill.php";
    private Map<String, String> params;

    public DeletepillRequest(int id, Response.Listener<String> listener){
        super(Request.Method.POST, regrequrl, listener, null);
        params = new HashMap<>();
        params.put("id", id + "");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

package com.example.myapplication1.help;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AddZabRequest  extends StringRequest {

    private static final String regrequrl = "http://192.168.64.2/g/addzab.php";
    private Map<String, String> params;

    public AddZabRequest(int id , String name, Response.Listener<String> listener){
        super(Method.POST, regrequrl, listener, null);
        params = new HashMap<>();
        params.put("id", id + "");
        params.put("name_zab", name);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

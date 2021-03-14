package com.example.myapplication1.help;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Save2Request extends StringRequest {
    private static final String logrequrl = "http://192.168.64.2/g/safe_info2.php";
    private Map<String, String> params;

    public Save2Request(int id, String name, String surname, String patronymic, String date,  Response.Listener<String> listener){
        super(Request.Method.POST, logrequrl, listener, null);
        params = new HashMap<>();
        params.put("id", id + "");
        params.put("name", name);
        params.put("surname", surname);
        params.put("patronymic", patronymic);
        params.put("date_birth", date);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

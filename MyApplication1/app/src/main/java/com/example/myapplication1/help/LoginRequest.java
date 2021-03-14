package com.example.myapplication1.help;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {


    private static final String logrequrl = "http://192.168.64.2/g/Login.php";
    private Map<String, String> params;

    public LoginRequest(String login, String password, Response.Listener<String> listener){
        super(Request.Method.POST, logrequrl, listener, null);
        params = new HashMap<>();
        params.put("login", login);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

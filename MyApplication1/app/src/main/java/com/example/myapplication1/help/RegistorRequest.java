package com.example.myapplication1.help;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegistorRequest extends StringRequest {

    private static final String regrequrl = "http://192.168.64.2/g/register.php";
    private Map<String, String> params;

    public RegistorRequest(String login, String password,  String name, String surname, String patronymic, String role, String date, String sex ,String email, String phone, Response.Listener<String> listener){
        super(Method.POST, regrequrl, listener, null);
        params = new HashMap<>();
        params.put("login", login);
        params.put("password", password);
        params.put("name", name);
        params.put("surname", surname);
        params.put("patronymic", patronymic);
        params.put("role", role);
        params.put("date_birth", date);
        params.put("sex", sex);
        params.put("email", email);
        params.put("phone", phone);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

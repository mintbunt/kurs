package com.example.myapplication1.help;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AddPillRequest extends StringRequest {

    private static final String regrequrl = "http://192.168.64.2/g/addpill.php";
    private Map<String, String> params;

    public AddPillRequest( int id_pac, String name,  int id, String doz, String receipt, int id_doc, Response.Listener<String> listener){
        super(Method.POST, regrequrl, listener, null);
        params = new HashMap<>();
        params.put("id_pac", id_pac + "");
        params.put("name", name);
        params.put("id", id + "");
        params.put("doz", doz);
        params.put("receipt", receipt);
        params.put("id_doc", id_doc + "");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

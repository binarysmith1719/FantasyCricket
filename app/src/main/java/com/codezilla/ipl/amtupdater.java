package com.codezilla.ipl;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class amtupdater {
    final String UID_KEY="main_key";
    Context context;
    int amt;

    public amtupdater(Context context, int amt) {
        this.context = context;
        this.amt = amt;
    }

    void addAmt()
    {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.upbalanceURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, "successfully added amt", Toast.LENGTH_LONG).show();
                        SharedPreferences shpf = context.getSharedPreferences(UID_KEY,MODE_PRIVATE);
                        int balance = shpf.getInt("wallet",2);
                        SharedPreferences.Editor editor = shpf.edit();
                        editor.putInt("wallet",balance+amt);
                        editor.apply();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        progressDialog.dismiss();
                        String e= error.toString();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                SharedPreferences getshpf= context.getSharedPreferences(UID_KEY,MODE_PRIVATE);
                int user_id = getshpf.getInt("uid",2);
                params.put("uid",Integer.toString(user_id));
                params.put("amt",Integer.toString(amt));
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 5000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 5000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        requestQueue.add(stringRequest);
    }
}

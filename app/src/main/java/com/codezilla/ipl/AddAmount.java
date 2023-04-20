package com.codezilla.ipl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddAmount extends AppCompatActivity {
    String UID_KEY="main_key";
    int amt;
    int user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_amount);
        amt=0;
        SharedPreferences getshpf= getSharedPreferences(UID_KEY,MODE_PRIVATE);
        user_id = getshpf.getInt("uid",2);

        EditText edt=  findViewById(R.id.txtaddamt);
        Button btn= findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amt=Integer.parseInt(edt.getText().toString());
                if(amt!=0){
                    amtupdater am=new amtupdater(AddAmount.this,amt);
                    am.addAmt();
                }
            }
        });
    }
//    void addAmt()
//    {
//        StringRequest stringRequest = new StringRequest(
//                Request.Method.POST,
//                Constants.upbalanceURL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
////                        Toast.makeText(profile.this, "successful frag selt", Toast.LENGTH_LONG).show();
//                        SharedPreferences shpf = getSharedPreferences(UID_KEY,MODE_PRIVATE);
//                        int balance = shpf.getInt("wallet",2);
//                        SharedPreferences.Editor editor = shpf.edit();
//                        editor.putInt("wallet",balance+amt);
//                        editor.apply();
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
////                        progressDialog.dismiss();
//                        String e= error.toString();
//                    }
//                }
//        ){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                SharedPreferences getshpf= getSharedPreferences(UID_KEY,MODE_PRIVATE);
//                int user_id = getshpf.getInt("uid",2);
//                params.put("uid",Integer.toString(user_id));
//                params.put("amt",Integer.toString(amt));
//                return params;
//            }
//
//        };
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        stringRequest.setRetryPolicy(new RetryPolicy() {
//            @Override
//            public int getCurrentTimeout() {
//                return 5000;
//            }
//
//            @Override
//            public int getCurrentRetryCount() {
//                return 5000;
//            }
//
//            @Override
//            public void retry(VolleyError error) throws VolleyError {
//
//            }
//        });
//        requestQueue.add(stringRequest);
//    }
}
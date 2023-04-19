package com.codezilla.ipl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class profile extends AppCompatActivity {
    String UID_KEY="main_key";
TextView edtname;
TextView phone;
TextView edtemail;
TextView wallet,coins,points;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Button edt_data= findViewById(R.id.btn_editdata);
        edt_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this, profileEdit.class);
                startActivity(intent);
            }
        });
        edtname=findViewById(R.id.edt_name);
        phone=findViewById(R.id.edt_phone);
        edtemail=findViewById(R.id.edt_eml2);
        wallet=findViewById(R.id.edt_wlt);
        coins=findViewById(R.id.btn_changeEmail);
        points=findViewById(R.id.edt_pass2);
        getProfile();
    }
    void getProfile()
    {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.getProfileURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(profile.this, "successful frag selt", Toast.LENGTH_LONG).show();
                        try {
                            JSONArray jsonarray= new JSONArray(response);
                            for(int i=0;i<jsonarray.length();i++)
                            {
                                JSONObject jsonobject= jsonarray.getJSONObject(i);
                                int  uid= jsonobject.getInt("user_id");
                                int  coin= jsonobject.getInt("coins");
                                int  balance= jsonobject.getInt("wallet_balance");
                                int  point= jsonobject.getInt("points");
                                String name = jsonobject.getString("name");
                                String email = jsonobject.getString("email_id");
                                String passwrd = jsonobject.getString("password");

                                //storing password in shared preference
                                SharedPreferences shpf = getSharedPreferences(UID_KEY,MODE_PRIVATE);
                                SharedPreferences.Editor editor = shpf.edit();
                                editor.putString("passx",passwrd);
                                editor.putString("emailx",email);
                                editor.apply();

                                edtname.setText(name);
                                edtemail.setText(email);
                                wallet.setText(Integer.toString(balance));
                                coins.setText(Integer.toString(coin));
                                points.setText(Integer.toString(point));

                                Toast.makeText(profile.this, "frag selt successfulx"+Integer.toString(uid), Toast.LENGTH_LONG).show();

                            }
                            Toast.makeText(profile.this ,"frag selt 8877****01", Toast.LENGTH_LONG).show();

                            if(jsonarray.length()==0)
                            {
                                Toast.makeText(profile.this, "frag selt NO PROFILE", Toast.LENGTH_LONG).show();
                            }else
                            {

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
                SharedPreferences getshpf= getSharedPreferences(UID_KEY,MODE_PRIVATE);
                int user_id = getshpf.getInt("uid",2);
                params.put("uid",Integer.toString(user_id));
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
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
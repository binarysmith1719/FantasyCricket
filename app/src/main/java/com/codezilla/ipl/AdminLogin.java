package com.codezilla.ipl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class AdminLogin extends AppCompatActivity {
    String email;
    String password;
    String LEAGUE_KEY="lgkey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        EditText eml= findViewById(R.id.edt_eml);
        EditText pass= findViewById(R.id.edt_pass);
        Button btn= findViewById(R.id.btn_login);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email= eml.getText().toString();
                password=pass.getText().toString();
                if(!(email.isEmpty() || password.isEmpty()))
                {
                    chkadmindata();
                }
            }
        });

    }
    void chkadmindata()
    {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.adminLoginURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String emailx="",passx="";
                        Toast.makeText(AdminLogin.this, "successful frag stmt 11", Toast.LENGTH_LONG).show();
                        try {
                            JSONArray jsonarray= new JSONArray(response);
//                            Toast.makeText(AddStmt.this, "successful frag stmt arry size =  "+Integer.toString(jsonarray.length()), Toast.LENGTH_LONG).show();

                            for(int i=0;i<jsonarray.length();i++)
                            {
                                JSONObject jsonobject= jsonarray.getJSONObject(i);
                                emailx = jsonobject.getString("email");
                                passx = jsonobject.getString("password");

//                                data_stmt ds=new data_stmt(title,sid,lid,points,coins);
////                              Toast.makeText(AddStmt.this, "frag stmt successfulx"+Integer.toString(lid), Toast.LENGTH_LONG).show();
//                                stmtlist.add(ds);
                            }
                            Toast.makeText(AdminLogin.this, "LOGIN "+email+" "+emailx , Toast.LENGTH_LONG).show();

                            if(email.equals(emailx) && password.equals(passx))
                            {
                                SharedPreferences shpf = getSharedPreferences(LEAGUE_KEY,MODE_PRIVATE);
                                SharedPreferences.Editor editor = shpf.edit();
                                editor.putBoolean("ADMINlogged",true);
                                editor.apply();
                                Intent in= new Intent(AdminLogin.this,AdminDash.class);
                                startActivity(in);
                            }
                            else {
                            Toast.makeText(AdminLogin.this, "Enter valid Email and Pass", Toast.LENGTH_LONG).show();
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
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(AdminLogin.this);
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
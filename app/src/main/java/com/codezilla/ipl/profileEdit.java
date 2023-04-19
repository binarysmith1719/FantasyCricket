package com.codezilla.ipl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.codezilla.ipl.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class profileEdit extends AppCompatActivity {
    String UID_KEY="main_key";
    TextView edtname;
    TextView phone;
    TextView edtemailOLD,edtemailNEW,edtpassOLD,edtpassNEW;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        edtname=findViewById(R.id.edt_name);
        phone=findViewById(R.id.edt_phone);
        edtemailOLD=findViewById(R.id.edt_eml2);
        edtemailNEW=findViewById(R.id.edt_eml3);
        edtpassOLD=findViewById(R.id.edt_pass2);
        edtpassNEW=findViewById(R.id.edt_pass3);



        Button btn_changemail=findViewById(R.id.btn_changeEmail);
        Button btn_changepass=findViewById(R.id.btn_changePass);

        SharedPreferences getshpf= getSharedPreferences(UID_KEY,MODE_PRIVATE);
        String passwrdx = getshpf.getString("passx","none");
        String emailx=getshpf.getString("emailx","none");
        int uid=getshpf.getInt("uid",1);

        btn_changemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!(edtemailOLD.getText().toString().isEmpty() || edtemailNEW.getText().toString().isEmpty()))
                {
                    if(emailx.equals(edtemailOLD.getText().toString()))
                    {
                        changeemail(edtemailNEW.getText().toString(),uid);
                    }
                }
            }
        });
        btn_changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!(edtpassOLD.getText().toString().isEmpty() || edtpassNEW.getText().toString().isEmpty()))
                {
                    if(passwrdx.equals(edtpassOLD.getText().toString()))
                    {
                        changeemail(edtpassNEW.getText().toString(),uid);
                    }
                }
            }
        });

        Button btn_save=findViewById(R.id.btn_save);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(edtname.getText().toString().isEmpty()))
                {
                    changename(edtname.getText().toString(),uid);
                }
            }
        });


    }
    void changeemail(String email,int uid)
    {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.upemailURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(profileEdit.this, "successful", Toast.LENGTH_LONG).show();
                        try {
                            JSONArray jsonarray= new JSONArray(response);

                                Toast.makeText(profileEdit.this, "Updated Successfully", Toast.LENGTH_LONG).show();

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
                params.put("uid",Integer.toString(uid));
                params.put("eid",email);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(profileEdit.this);
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
    void changename(String name,int uid)
    {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.upnameURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(profileEdit.this, "successful", Toast.LENGTH_LONG).show();
                        try {
                            JSONArray jsonarray= new JSONArray(response);

                            Toast.makeText(profileEdit.this, "Updated Successfully", Toast.LENGTH_LONG).show();

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
                params.put("uid",Integer.toString(uid));
                params.put("eid",name);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(profileEdit.this);
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
    void changepass(String pass,int uid)
    {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.uppassURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(profileEdit.this, "successful", Toast.LENGTH_LONG).show();
                        try {
                            JSONArray jsonarray= new JSONArray(response);

                            Toast.makeText(profileEdit.this, "Updated Successfully", Toast.LENGTH_LONG).show();

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
                params.put("uid",Integer.toString(uid));
                params.put("eid",pass);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(profileEdit.this);
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
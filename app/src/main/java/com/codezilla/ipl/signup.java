package com.codezilla.ipl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompatSideChannelService;

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
import com.codezilla.ipl.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity {
EditText name;
EditText phone;
EditText email;
EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name=findViewById(R.id.edt_name);
        phone=findViewById(R.id.edt_phone);
        email=findViewById(R.id.edt_eml2);
        password=findViewById(R.id.edt_pass2);

        Button  signup= findViewById(R.id.btn_signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nam=name.getText().toString();
                String ph=phone.getText().toString();
                String eml=email.getText().toString();
                String pass=password.getText().toString();
                if(!(nam.isEmpty() || ph.isEmpty() || eml.isEmpty()|| pass.isEmpty())){
                insertSignupDetail();}
            }
        });

    }
    //CALLING THE BACKEND
    void insertSignupDetail()
    {
        String nam=name.getText().toString();
        String ph=phone.getText().toString();
        String eml=email.getText().toString();
        String pass=password.getText().toString();
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.signupURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(signup.this, "successful", Toast.LENGTH_LONG).show();
                        try {
                            JSONArray jsonarray= new JSONArray(response);
                            if(jsonarray.length()==0)
                            {
                                Toast.makeText(signup.this, "ACCOUNT ALREADY EXISTS !!", Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(signup.this, "Account Created Successfully", Toast.LENGTH_LONG).show();
                                finish();
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
                        Toast.makeText(signup.this, "unsuccessful", Toast.LENGTH_LONG).show();
                        Toast.makeText(signup.this,e , Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email",eml);
                params.put("password",pass);
                params.put("name",nam);
                params.put("phone",ph);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(signup.this);
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

package com.codezilla.ipl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
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

public class login extends AppCompatActivity {
    String UID_KEY="main_key";
//    String WALLET_KEY="wallet_key";
//    String POINTS_KEY="points_key";
//    String COINS_KEY="coins_key";
    EditText edt_email ;
    EditText edt_password;
    int result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
       Button btn_login= findViewById(R.id.btn_login);
       edt_email=findViewById(R.id.edt_eml);
       edt_password=findViewById(R.id.edt_pass);
       result=1;
       btn_login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String eml = edt_email.getText().toString();
               String ps=edt_password.getText().toString();
               if(!(eml.isEmpty() || ps.isEmpty())) {
                   getLoginDetail(eml,ps);
               }
           }
       });

       Button signup= findViewById(R.id.btn_signup);
       signup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(login.this, signup.class);
//               onPause();
               startActivity(intent);
           }
       });
    }
    //CALLING THE BACKEND
    void getLoginDetail(String eml,String pass)
    {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.loginURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(login.this, "successful", Toast.LENGTH_LONG).show();
                        try {
                            JSONArray jsonarray= new JSONArray(response);
                            for(int i=0;i<jsonarray.length();i++)
                            {
                                JSONObject jsonobject= jsonarray.getJSONObject(i);
                                int  uid= jsonobject.getInt("user_id");
                                int wallet_balance = Integer.parseInt(jsonobject.getString("wallet_balance"));
                                int points = Integer.parseInt(jsonobject.getString("points"));
                                int coins = Integer.parseInt(jsonobject.getString("coins"));
                                Toast.makeText(login.this, "successfulx"+Integer.toString(uid), Toast.LENGTH_LONG).show();

                                SharedPreferences shpf = getSharedPreferences(UID_KEY,MODE_PRIVATE);
                                SharedPreferences.Editor editor = shpf.edit();
                                editor.putInt("uid",uid);
                                editor.putInt("wallet",wallet_balance);
                                editor.putInt("points",points);
                                editor.putInt("coins",coins);
                                editor.putBoolean("logged",true);
                                editor.apply();

                                Intent intent = new Intent(login.this, MainActivity.class);
                                startActivity(intent);
                            }
                            if(jsonarray.length()==0)
                            {
                                edt_email.setText("");
                                edt_password.setText("");
                                Toast.makeText(login.this, "ENTER VALID EMAIL OR PASSWORD", Toast.LENGTH_LONG).show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
//                        adapterm.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        progressDialog.dismiss();
                        String e= error.toString();
                        Toast.makeText(login.this, "unsuccessful", Toast.LENGTH_LONG).show();
                        Toast.makeText(login.this,e , Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email",eml);
                params.put("password",pass);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(login.this);
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
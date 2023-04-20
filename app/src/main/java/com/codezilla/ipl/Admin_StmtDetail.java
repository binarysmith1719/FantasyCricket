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

import java.util.HashMap;
import java.util.Map;

public class Admin_StmtDetail extends AppCompatActivity {
    String stmt;
    int points,coins;
    String LEAGUE_KEY="lgkey";
    static stmtListener ref;
    public static void initSTMTlistener(stmtListener refx)
    {
        ref=refx;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_stmt_detail);
        EditText stmte=findViewById(R.id.txtstmt);
        EditText pointse=findViewById(R.id.txtpoints);
        EditText coinse=findViewById(R.id.txtcoins);

        Button btn= findViewById(R.id.setlgstmt);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stmt=stmte.getText().toString();
                points=Integer.parseInt(pointse.getText().toString());
                coins=Integer.parseInt(coinse.getText().toString());
                if(!(stmt.isEmpty() || points==0 || coins==0))
                {
                    Toast.makeText(Admin_StmtDetail.this, "successful in ", Toast.LENGTH_LONG).show();
                    insertStmtDetail();
                }
            }
        });

    }
    //    CALLING THE BACKEND
    void insertStmtDetail()
    {
        SharedPreferences getshpf2= getSharedPreferences(LEAGUE_KEY,MODE_PRIVATE);
        int league_id = getshpf2.getInt("leagueADMIN",1);

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.addnewstmtURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Admin_StmtDetail.this, "successful", Toast.LENGTH_LONG).show();
                        try {
                            JSONArray jsonarray= new JSONArray(response);
                            Toast.makeText(Admin_StmtDetail.this, "Added Successfully", Toast.LENGTH_LONG).show();
                            ref.onstmtaddition();
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
                        Toast.makeText(Admin_StmtDetail.this, "unsuccessful", Toast.LENGTH_LONG).show();
                        Toast.makeText(Admin_StmtDetail.this,e , Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("stmt",stmt);
                params.put("lid", Integer.toString(league_id));
                params.put("points",Integer.toString(points));
                params.put("coins",Integer.toString(coins));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Admin_StmtDetail.this);
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

    public interface stmtListener
    {
        public void onstmtaddition();
    }
}
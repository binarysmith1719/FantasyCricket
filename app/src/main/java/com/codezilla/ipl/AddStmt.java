package com.codezilla.ipl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddStmt extends AppCompatActivity implements Admin_StmtDetail.stmtListener{
    ArrayList<data_stmt> stmtlist=new ArrayList<>();
    int league_id;
    String LEAGUE_KEY="lgkey";
    statementAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stmt);
        Admin_StmtDetail.initSTMTlistener(this);
        Button btnADDstmtDetail = findViewById(R.id.btnaddstmt);

        btnADDstmtDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  Intent in= new Intent(AddStmt.this,Admin_StmtDetail.class);
                  startActivity(in);
            }
        });

        SharedPreferences getshpf= getSharedPreferences(LEAGUE_KEY,MODE_PRIVATE);
        int value = getshpf.getInt("leagueADMIN",1);
        league_id=value;

        RecyclerView rv= findViewById(R.id.rvst);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);

        sa= new statementAdapter(this,stmtlist);

        rv.setAdapter(sa);
        getLeague();
    }
    void getLeague()
    {
        stmtlist.clear();
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.stmtUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(AddStmt.this, "successful frag stmt 11", Toast.LENGTH_LONG).show();
                        try {
                            JSONArray jsonarray= new JSONArray(response);
//                            Toast.makeText(AddStmt.this, "successful frag stmt arry size =  "+Integer.toString(jsonarray.length()), Toast.LENGTH_LONG).show();

                            for(int i=0;i<jsonarray.length();i++)
                            {
                                JSONObject jsonobject= jsonarray.getJSONObject(i);
                                int  lid= jsonobject.getInt("league_id");
                                int  sid= jsonobject.getInt("stmt_id");
                                int  points= jsonobject.getInt("points");
                                int  coins=jsonobject.getInt("coins");
                                String title = jsonobject.getString("statement");

                                data_stmt ds=new data_stmt(title,sid,lid,points,coins);

//                                Toast.makeText(AddStmt.this, "frag stmt successfulx"+Integer.toString(lid), Toast.LENGTH_LONG).show();

                                stmtlist.add(ds);
                            }
                            Toast.makeText(AddStmt.this, "frag stmt 8877****01", Toast.LENGTH_LONG).show();

                            if(jsonarray.length()==0)
                            {
                                Toast.makeText(AddStmt.this, " frag stmt NO LEAGUE", Toast.LENGTH_LONG).show();
                            }else
                            {

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        sa.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        progressDialog.dismiss();
                        String e= error.toString();
                        Toast.makeText(AddStmt.this, "unsuccessful", Toast.LENGTH_LONG).show();
                        Toast.makeText(AddStmt.this,e , Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id",Integer.toString(league_id));
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(AddStmt.this);
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

    @Override
    public void onstmtaddition() {
        getLeague();
    }
}
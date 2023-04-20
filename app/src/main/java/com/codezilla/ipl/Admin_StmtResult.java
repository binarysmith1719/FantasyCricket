package com.codezilla.ipl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class Admin_StmtResult extends AppCompatActivity {
    static onLeagueCompleteListener reference;

    ArrayList<data_stmt> stmtlist=new ArrayList<>();
    int league_id;
    String LEAGUE_KEY="lgkey";
    Admin_ResStmtAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_stmt_result);

        SharedPreferences getshpf= getSharedPreferences(LEAGUE_KEY,MODE_PRIVATE);
        int value = getshpf.getInt("leagueADMIN",1);
        league_id=value;

        RecyclerView rv= findViewById(R.id.ryStmtResult);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
        sa= new Admin_ResStmtAdapter(this,stmtlist);

        rv.setAdapter(sa);
        getLeague();

        Button btn= findViewById(R.id.processRes);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uppoints();
            }
        });
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
                            Toast.makeText(Admin_StmtResult.this, "frag stmt 8877****01", Toast.LENGTH_LONG).show();

                            if(jsonarray.length()==0)
                            {
                                Toast.makeText(Admin_StmtResult.this,  " frag stmt NO LEAGUE", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(Admin_StmtResult.this,  "unsuccessful", Toast.LENGTH_LONG).show();
                        Toast.makeText(Admin_StmtResult.this, e , Toast.LENGTH_LONG).show();
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

        RequestQueue requestQueue = Volley.newRequestQueue(Admin_StmtResult.this);
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
    void uppoints()
    {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.uppointsURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Admin_StmtResult.this, "successful updated points", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        progressDialog.dismiss();
                        String e= error.toString();
                        Toast.makeText(Admin_StmtResult.this,  "unsuccessful", Toast.LENGTH_LONG).show();
                        Toast.makeText(Admin_StmtResult.this, e , Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("lid",Integer.toString(league_id));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Admin_StmtResult.this);
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

    static void makeref(onLeagueCompleteListener reference)
    {
        Admin_StmtResult.reference=reference;
    }

    public interface onLeagueCompleteListener{
        public void oncompletion();
    }
}
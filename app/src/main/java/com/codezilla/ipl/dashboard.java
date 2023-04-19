package com.codezilla.ipl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class dashboard extends AppCompatActivity {
//    String LGE_KEY="league_key";
    ArrayList<leagueList> arrayList= new ArrayList<>();
    dashAdapter da;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolBar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView rv= findViewById(R.id.dashRV);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);

        da = new dashAdapter(arrayList,this);
        rv.setAdapter(da);

        getLeague();
    }
    void getLeague()
    {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.leagueUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(dashboard.this, "successful", Toast.LENGTH_LONG).show();
                        try {
                            JSONArray jsonarray= new JSONArray(response);
                            for(int i=0;i<jsonarray.length();i++)
                            {
                                JSONObject jsonobject= jsonarray.getJSONObject(i);
                                int  lid= jsonobject.getInt("league_id");
                                String title = jsonobject.getString("title");

                                Toast.makeText(dashboard.this, "successfulx"+Integer.toString(lid), Toast.LENGTH_LONG).show();
                                leagueList ll= new leagueList(title,lid);
                                arrayList.add(ll);
                            }
                            Toast.makeText(dashboard.this, "Checking LEAGUE *88******77", Toast.LENGTH_LONG).show();

                            if(jsonarray.length()==0)
                            {
                                Toast.makeText(dashboard.this, "NO LEAGUE", Toast.LENGTH_LONG).show();
                            }else
                            {

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        da.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        progressDialog.dismiss();
                        String e= error.toString();
                        Toast.makeText(dashboard.this, "unsuccessful", Toast.LENGTH_LONG).show();
                        Toast.makeText(dashboard.this,e , Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(dashboard.this);
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
package com.codezilla.ipl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class Admin_declareResult extends AppCompatActivity {

    ArrayList<leagueList> arrayList=new ArrayList<>();
    Admin_addLeagueAdapter aa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_declare_result);

        RecyclerView rvlg= findViewById(R.id.ryResult);
        aa= new Admin_addLeagueAdapter(arrayList,this);
        Admin_addLeagueAdapter.choice=2;
        rvlg.setLayoutManager(new LinearLayoutManager(this));
        rvlg.setHasFixedSize(true);
        rvlg.setAdapter(aa);
        getLeague();
    }
    void getLeague()
    {
        arrayList.clear();
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.leagueUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Admin_declareResult.this, "Admin successfullglglglg", Toast.LENGTH_LONG).show();
                        try {
                            JSONArray jsonarray= new JSONArray(response);
                            for(int i=0;i<jsonarray.length();i++)
                            {
                                JSONObject jsonobject= jsonarray.getJSONObject(i);
                                int  lid= jsonobject.getInt("league_id");
                                String title = jsonobject.getString("title");
                                String date = jsonobject.getString("date");
                                String time = jsonobject.getString("time");
                                int resdec=jsonobject.getInt("resdec");
                                Toast.makeText(Admin_declareResult.this, "Admin successfulx"+Integer.toString(lid), Toast.LENGTH_LONG).show();
                                leagueList ll= new leagueList(title,lid,date,time);
                                if(resdec==0) {
                                    arrayList.add(ll);
                                }
                            }
                            Toast.makeText(Admin_declareResult.this, "Admin Checking LEAGUE *88******77", Toast.LENGTH_LONG).show();

                            if(jsonarray.length()==0)
                            {
                                Toast.makeText(Admin_declareResult.this, "Admin NO LEAGUE", Toast.LENGTH_LONG).show();
                            }else
                            {

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        aa.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        progressDialog.dismiss();
                        String e= error.toString();
                        Toast.makeText(Admin_declareResult.this, "unsuccessful", Toast.LENGTH_LONG).show();
                        Toast.makeText(Admin_declareResult.this,e , Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(Admin_declareResult.this);
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
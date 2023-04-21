package com.codezilla.ipl;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class statementFragment extends Fragment {
    ArrayList<data_stmt> stmtlist=new ArrayList<>();
    int league_id;
    String LEAGUE_KEY="lgkey";
    statementAdapter sa;
    public statementFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_statement, container, false);
//        data_stmt ds=new data_stmt("CSK will win",101, 1);
//        data_stmt ds1=new data_stmt("KKR will win",102, 1);
//        data_stmt ds2=new data_stmt("CSK win the TOSS",103, 1);
//        data_stmt ds3=new data_stmt("Kohli makes 50 run",104, 2);

        SharedPreferences getshpf= getContext().getSharedPreferences(LEAGUE_KEY,MODE_PRIVATE);
        int value = getshpf.getInt("leaguek",1);
        league_id=value;
//        Toast.makeText(getContext(), "successful stmt frag lid"+Integer.toString(value), Toast.LENGTH_LONG).show();

//        stmtlist.add(ds);
//        stmtlist.add(ds1);
//        stmtlist.add(ds2);
//        stmtlist.add(ds3);

        RecyclerView rv= view.findViewById(R.id.stmtRV);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setHasFixedSize(true);

        sa= new statementAdapter(getContext(),stmtlist);

        rv.setAdapter(sa);
        getLeague();

        return view;
    }
    void getLeague()
    {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.stmtUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getContext(), "successful frag stmt 11", Toast.LENGTH_LONG).show();
                        try {
                            JSONArray jsonarray= new JSONArray(response);
//                            Toast.makeText(getContext(), "successful frag stmt arry size =  "+Integer.toString(jsonarray.length()), Toast.LENGTH_LONG).show();

                            for(int i=0;i<jsonarray.length();i++)
                            {
                                JSONObject jsonobject= jsonarray.getJSONObject(i);
                                int  lid= jsonobject.getInt("league_id");
                                int  sid= jsonobject.getInt("stmt_id");
                                int  points= jsonobject.getInt("points");
                                int  coins=jsonobject.getInt("coins");
                                String title = jsonobject.getString("statement");

                                data_stmt ds=new data_stmt(title,sid,lid,points,coins);

//                                Toast.makeText(getContext(), "frag stmt successfulx"+Integer.toString(lid), Toast.LENGTH_LONG).show();

                                stmtlist.add(ds);
                            }
//                            Toast.makeText(getContext(), "frag stmt 8877****01", Toast.LENGTH_LONG).show();

                            if(jsonarray.length()==0)
                            {
                                Toast.makeText(getContext(), " frag stmt NO LEAGUE", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(getContext(), "unsuccessful", Toast.LENGTH_LONG).show();
                        Toast.makeText(getContext(),e , Toast.LENGTH_LONG).show();
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

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
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
package com.codezilla.ipl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class showLeagueWinners extends AppCompatActivity {
    ArrayList<winner> wlist=new ArrayList<>();
    showWinnerAdapter sa;
    int league_id;
    String LEAGUE_KEY="lgkey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_league_winners);
        SharedPreferences getshpf= getSharedPreferences(LEAGUE_KEY,MODE_PRIVATE);
        int value = getshpf.getInt("leagueADMIN",1);
        league_id=value;

        RecyclerView rv= findViewById(R.id.winnerrv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);

        sa= new showWinnerAdapter(this,wlist);
        rv.setAdapter(sa);

        getWinners();
    }
    void getWinners()
    {
        wlist.clear();
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.winnerURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(showLeagueWinners.this, "successful winner", Toast.LENGTH_LONG).show();
                        try {
                            JSONArray jsonarray= new JSONArray(response);
//                            Toast.makeText(getContext(), "successful frag stmt arry size =  "+Integer.toString(jsonarray.length()), Toast.LENGTH_LONG).show();

                            for(int i=0;i<jsonarray.length();i++)
                            {
                                JSONObject jsonobject= jsonarray.getJSONObject(i);
                                int  uid= jsonobject.getInt("user_id");
                                int  points= jsonobject.getInt("umaid");
                                String name = jsonobject.getString("name");

                                winner w= new winner(name,points,uid);
                                Toast.makeText(showLeagueWinners.this, "frag stmt successfulx", Toast.LENGTH_LONG).show();
                                wlist.add(w);
                            }
//                            Toast.makeText(getContext(), "frag stmt 8877****01", Toast.LENGTH_LONG).show();

                            if(jsonarray.length()==0)
                            {
                                Toast.makeText(showLeagueWinners.this, " frag stmt NO LEAGUE", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(showLeagueWinners.this, "unsuccessful", Toast.LENGTH_LONG).show();
                        Toast.makeText(showLeagueWinners.this,e , Toast.LENGTH_LONG).show();
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

        RequestQueue requestQueue = Volley.newRequestQueue(showLeagueWinners.this);
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
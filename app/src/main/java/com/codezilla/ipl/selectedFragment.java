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

public class selectedFragment extends Fragment implements OnAdditionListener{
    ArrayList<data_stmt> selectedlist=new ArrayList<>();
    selectedAdapter sa;

    int league_id,user_id;
    String LEAGUE_KEY="lgkey";
    String UID_KEY="main_key";
    public selectedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_selected, container, false);

        contactBtnStmtANDSelectedFrag.setOnAdditionListener(this);

        SharedPreferences getshpf= getContext().getSharedPreferences(UID_KEY,MODE_PRIVATE);
        user_id = getshpf.getInt("uid",2);

        SharedPreferences getshpf2= getContext().getSharedPreferences(LEAGUE_KEY,MODE_PRIVATE);
        league_id = getshpf2.getInt("leaguek",1);

        RecyclerView rv= view.findViewById(R.id.selectRV);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setHasFixedSize(true);

        sa= new selectedAdapter(getContext(),selectedlist);
        rv.setAdapter(sa);
        getLeague();

        return view;
    }
    void getLeague()
    {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.selectUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getContext(), "successful frag selt", Toast.LENGTH_LONG).show();
                        selectedlist.clear();
                        try {
                            JSONArray jsonarray= new JSONArray(response);
                            for(int i=0;i<jsonarray.length();i++)
                            {
                                JSONObject jsonobject= jsonarray.getJSONObject(i);
                                int  lid= jsonobject.getInt("league_id");
                                int  sid= jsonobject.getInt("stmt_id");
                                int  points= jsonobject.getInt("points");
                                int  uid= jsonobject.getInt("user_id");
                                String title = jsonobject.getString("stmt");

                                data_stmt ds=new data_stmt(title,sid,lid,points);

                                Toast.makeText(getContext(), "frag selt successfulx"+Integer.toString(lid), Toast.LENGTH_LONG).show();

                                selectedlist.add(ds);
                            }
                            Toast.makeText(getContext(), "frag selt 8877****01", Toast.LENGTH_LONG).show();

                            if(jsonarray.length()==0)
                            {
                                Toast.makeText(getContext(), "frag selt NO LEAGUE", Toast.LENGTH_LONG).show();
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
                params.put("lid",Integer.toString(league_id));
                params.put("uid",Integer.toString(user_id));
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

    @Override
    public void onaddition() {
        getLeague();
    }
}
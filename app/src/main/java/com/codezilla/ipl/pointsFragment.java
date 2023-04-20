package com.codezilla.ipl;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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

public class pointsFragment extends Fragment {

    public pointsFragment() {
        // Required empty public constructor
    }
    TextView txtpnts;
    String url;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_points, container, false);
        txtpnts=view.findViewById(R.id.txtcount);

        SharedPreferences getshpf= getContext().getSharedPreferences(UID_KEY,MODE_PRIVATE);
        int user_id = getshpf.getInt("uid",2);
        url=Constants.pointsURL+Integer.toString(user_id);
         getLoginDetail();
        return view;
    }
    String UID_KEY="main_key";
    //CALLING THE BACKEND
    void getLoginDetail()
    {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getContext(), "successful", Toast.LENGTH_LONG).show();
                        try {
                            JSONArray jsonarray= new JSONArray(response);
                            for(int i=0;i<jsonarray.length();i++)
                            {
                                JSONObject jsonobject= jsonarray.getJSONObject(i);
                                int points = Integer.parseInt(jsonobject.getString("points"));
                                int  coins= jsonobject.getInt("coins");
                                int  balance= jsonobject.getInt("wallet_balance");
                                Toast.makeText(getContext(), "successfulx  points", Toast.LENGTH_LONG).show();

                                txtpnts.setText(Integer.toString(points));
                                SharedPreferences shpf = getContext().getSharedPreferences(UID_KEY,MODE_PRIVATE);
                                SharedPreferences.Editor editor = shpf.edit();
                                editor.putInt("wallet",balance);
                                editor.putInt("points",points);
                                editor.putInt("coins",coins);
                                editor.apply();

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
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
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
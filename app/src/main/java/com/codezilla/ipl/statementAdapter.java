package com.codezilla.ipl;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class statementAdapter extends RecyclerView.Adapter<statementAdapter.ViewHolder> {
    ArrayList<data_stmt> stmtList;
    Context context;
    int league_id,user_id;
    String LEAGUE_KEY="lgkey";
    String UID_KEY="main_key";
    public statementAdapter(Context context, ArrayList<data_stmt> stmtList) {
        this.stmtList=stmtList;
        this.context=context;
    }

    @NonNull
    @Override
    public statementAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_stmt,parent,false);
        return new statementAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull statementAdapter.ViewHolder holder, int position) {
        data_stmt ds= stmtList.get(position);
        holder.tv.setText(ds.stmt);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stmt= ds.getStmt();
                int stmt_id=ds.getStmt_id();
                int points=ds.points;
                int coins=ds.coins;
                insertStmtDetail(stmt,stmt_id,points,coins);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stmtList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv= itemView.findViewById(R.id.stmtid);
            img=itemView.findViewById(R.id.addstmt);
        }
    }
//    CALLING THE BACKEND
    void insertStmtDetail(String stmt,int stmt_id,int points,int coins)
    {
        SharedPreferences getshpf= context.getSharedPreferences(UID_KEY,MODE_PRIVATE);
        user_id = getshpf.getInt("uid",2);

        SharedPreferences getshpf2= context.getSharedPreferences(LEAGUE_KEY,MODE_PRIVATE);
        league_id = getshpf2.getInt("leaguek",1);

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.stmtaddURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, "successful", Toast.LENGTH_LONG).show();
                        try {
                            JSONArray jsonarray= new JSONArray(response);
                            if(jsonarray.length()==0)
                            {
                                Toast.makeText(context, "Already Added", Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(context, "Added Successfully", Toast.LENGTH_LONG).show();
                                contactBtnStmtANDSelectedFrag.ref.onaddition();
                            }

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
                        Toast.makeText(context, "unsuccessful", Toast.LENGTH_LONG).show();
                        Toast.makeText(context,e , Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("stmt",stmt);
                params.put("lid", Integer.toString(league_id));
                params.put("uid",Integer.toString(user_id));
                params.put("sid",Integer.toString(stmt_id));
                params.put("points",Integer.toString(points));
                params.put("coins",Integer.toString(coins));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
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

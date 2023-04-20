package com.codezilla.ipl;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

public class Admin_ResStmtAdapter extends RecyclerView.Adapter<Admin_ResStmtAdapter.ViewHolder> {
    ArrayList<data_stmt> stmtList;
    Context context;
    int league_id;
    String LEAGUE_KEY="lgkey";
    public Admin_ResStmtAdapter(Context context, ArrayList<data_stmt> stmtList) {
        this.stmtList=stmtList;
        this.context=context;
        SharedPreferences getshpf= context.getSharedPreferences(LEAGUE_KEY,MODE_PRIVATE);
        int value = getshpf.getInt("leagueADMIN",1);
        league_id=value;
    }

    @NonNull
    @Override
    public Admin_ResStmtAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardresult,parent,false);
        return new Admin_ResStmtAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Admin_ResStmtAdapter.ViewHolder holder, int position) {
        data_stmt ds= stmtList.get(position);
        holder.tv.setText(ds.stmt);
        int id=ds.getStmt_id();
        holder.cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              holder.correct.setVisibility(View.INVISIBLE);
              holder.cross.setVisibility(View.INVISIBLE);
            }
        });

        holder.correct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.correct.setVisibility(View.INVISIBLE);
                holder.cross.setVisibility(View.INVISIBLE);
                insertStmtDetail(id);
            }
        });

    }

    @Override
    public int getItemCount() {
        return stmtList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        ImageView cross,correct;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv= itemView.findViewById(R.id.stmtid);
            cross=itemView.findViewById(R.id.wrong);
            correct=itemView.findViewById(R.id.right);
        }
    }
    void insertStmtDetail(int stmt_id)
    {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.rightURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, "successfully updated result", Toast.LENGTH_LONG).show();

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
                params.put("lid", Integer.toString(league_id));
                params.put("sid",Integer.toString(stmt_id));
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

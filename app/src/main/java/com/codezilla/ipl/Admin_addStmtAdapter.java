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

public class Admin_addStmtAdapter extends RecyclerView.Adapter<Admin_addStmtAdapter.ViewHolder> {
    ArrayList<data_stmt> stmtList;
    Context context;
    int league_id,user_id;
    String LEAGUE_KEY="lgkey";
    String UID_KEY="main_key";
    public Admin_addStmtAdapter(Context context, ArrayList<data_stmt> stmtList) {
        this.stmtList=stmtList;
        this.context=context;
    }

    @NonNull
    @Override
    public Admin_addStmtAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_stmt,parent,false);
        return new Admin_addStmtAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Admin_addStmtAdapter.ViewHolder holder, int position) {
        data_stmt ds= stmtList.get(position);
        holder.tv.setText(ds.stmt);
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
}

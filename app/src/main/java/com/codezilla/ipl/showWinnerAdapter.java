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

public class showWinnerAdapter extends RecyclerView.Adapter<showWinnerAdapter.ViewHolder> {
    ArrayList<winner> stmtList;
    Context context;
    public showWinnerAdapter(Context context, ArrayList<winner> stmtList) {
        this.stmtList=stmtList;
        this.context=context;
    }

    @NonNull
    @Override
    public showWinnerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardwinner,parent,false);
        return new showWinnerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull showWinnerAdapter.ViewHolder holder, int position) {
        winner wn= stmtList.get(position);
        holder.name.setText(wn.name);
        holder.points.setText(Integer.toString(wn.getPoints()));
    }

    @Override
    public int getItemCount() {
        return stmtList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView points,name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            points= itemView.findViewById(R.id.winpoints);
            name=itemView.findViewById(R.id.winid);
        }
    }
}

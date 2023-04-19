package com.codezilla.ipl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class selectedAdapter  extends RecyclerView.Adapter<selectedAdapter.ViewHolder> {
    ArrayList<data_stmt> stmtList;
    Context context;
    public selectedAdapter(Context context, ArrayList<data_stmt> stmtList) {
        this.stmtList=stmtList;
        this.context=context;
    }

    @NonNull
    @Override
    public selectedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_stmt,parent,false);
        return new selectedAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull selectedAdapter.ViewHolder holder, int position) {
        data_stmt ds= stmtList.get(position);
        holder.tv.setText(ds.stmt);
        holder.img.setImageResource(R.drawable.ic_baseline_check_box_24);
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

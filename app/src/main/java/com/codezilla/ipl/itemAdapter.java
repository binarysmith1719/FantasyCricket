package com.codezilla.ipl;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class itemAdapter extends RecyclerView.Adapter<itemAdapter.ViewHolder> {
    //    private String Name;
    private Context context;
    private ArrayList<String> itemList ;
    public itemAdapter(Context context, ArrayList<String> itemList)
    {
        Log.d("bug","got_response21");
        this.context=context;
        this.itemList=itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("bug2","got_response2");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardreview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("bug2","got_response4");
        String itm= itemList.get(position);
        holder.itemname.setText(itm);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        private TextView itemname;
         public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemname= itemView.findViewById(R.id.revid);

        }
    }
}
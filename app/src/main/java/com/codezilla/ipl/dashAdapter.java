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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class dashAdapter extends RecyclerView.Adapter<dashAdapter.ViewHolder> {
    ArrayList<leagueList> all;
    String LEAGUE_KEY="lgkey";
    Context context;
    public dashAdapter(ArrayList<leagueList> all, Context context) {
        this.all=all;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashcard,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        leagueList ll = all.get(position);
        String s=ll.getStr();
        int id=ll.getLeague_id();

        holder.revtext.setText(s);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences shpf = context.getSharedPreferences(LEAGUE_KEY,MODE_PRIVATE);
                SharedPreferences.Editor editor = shpf.edit();
                editor.putInt("leaguek",id);
                editor.apply();
                Intent intent  = new Intent(context,language.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return all.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgLeague;
        TextView revtext;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLeague = itemView.findViewById(R.id.imgLeague);
            revtext   = itemView.findViewById(R.id.revid);
        }
    }
}

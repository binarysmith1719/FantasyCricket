package com.codezilla.ipl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.RankViewHolder> {
    Context context;
    ArrayList<String> alist;
    public RankAdapter(Context contetx,ArrayList<String> alist)
    {
        this.context=context;
        this.alist=alist;
    }
    @NonNull
    @Override
    public RankAdapter.RankViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardreview,parent,false);
        return new RankAdapter.RankViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RankAdapter.RankViewHolder holder, int position) {
        String itm= alist.get(position);
        holder.itemname.setText(itm);
        holder.rnk.setText(Integer.toString(position+1));
    }

    @Override
    public int getItemCount() {
        return alist.size();
    }
    class RankViewHolder extends RecyclerView.ViewHolder {
        private TextView itemname;
        TextView rnk;
        public RankViewHolder(@NonNull View itemView) {
            super(itemView);
            itemname= itemView.findViewById(R.id.revid);
            rnk=itemView.findViewById(R.id.txtrnk);
        }
    }
}

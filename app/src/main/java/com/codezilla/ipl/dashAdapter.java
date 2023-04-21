package com.codezilla.ipl;

import static android.content.Context.MODE_PRIVATE;

import android.content.ContentUris;
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

import java.util.ArrayList;
import java.util.Calendar;

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
        String date = ll.date;
        String time=ll.time;
        holder.revtext.setText(s);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar c= Calendar.getInstance();
                String y= String.valueOf(c.get(Calendar.YEAR));
                String m=String.valueOf(c.get(Calendar.MONTH)+1);
                String d=String.valueOf(c.get(Calendar.DATE));

                String hr=String.valueOf(c.get(Calendar.HOUR_OF_DAY));
                String min=String.valueOf(c.get(Calendar.MINUTE));

                if(m.length()==1)
                    m="0"+m;
                if(d.length()==1)
                    d="0"+d;
                if(hr.length()==1)
                    hr="0"+hr;
                if(min.length()==1)
                    min="0"+min;
                String current_date=y+"-"+m+"-"+d;
//                Toast.makeText(context,     current_date+"    "+date, Toast.LENGTH_LONG).show();
                String tm1=String.valueOf(time.charAt(0))+String.valueOf(time.charAt(1));
                String m21=String.valueOf(time.charAt(3))+String.valueOf(time.charAt(4));
//                Toast.makeText(context, tm1+"<-- given hr  given min --> "+m21, Toast.LENGTH_SHORT).show();
//                Toast.makeText(context, hr+"<-- curr hr  curr min --> "+min, Toast.LENGTH_SHORT).show();
                if(current_date.equals(date))
                {
//                    Toast.makeText(context, "EQUAL EQUAL EQUAL ", Toast.LENGTH_SHORT).show();
                    String tm=String.valueOf(time.charAt(0))+String.valueOf(time.charAt(1));

                    int tmint=Integer.parseInt(tm);
                    int hrint=Integer.parseInt(hr);
                    if(tmint==hrint)
                    {

                        String m2=String.valueOf(time.charAt(3))+String.valueOf(time.charAt(4));
                            int m3= Integer.parseInt(m2); // event starting minute
                            int min1=Integer.parseInt(min); //current min
//                        Toast.makeText(context, m2+"<-- given min  curr min --> "+min, Toast.LENGTH_SHORT).show();
                            if(!(min1<m3 && m3-min1>=5))
                            {
                                Toast.makeText(context, "TRY 5 min before the event starts", Toast.LENGTH_SHORT).show();
                                return ;
                            }
                    }
                    else if(hrint>tmint) {
                        Toast.makeText(context, "TRY 5 min before the event starts", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

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

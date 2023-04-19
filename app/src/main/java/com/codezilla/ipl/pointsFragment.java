package com.codezilla.ipl;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class pointsFragment extends Fragment {

    public pointsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_points, container, false);
//        RecyclerView rv= view.findViewById(R.id.userrRV);
//        ArrayList<String> as= new ArrayList<>();
//        as.add("SUBHAM KUMAR");
//        as.add("SOURABJIT KUMAR");
//        as.add("ABHIJEET KUMAR");
//        as.add("VIJAY MISRA");
//        as.add("ABHINAV KESRI");
//
//        RankAdapter ra= new RankAdapter(getContext(),as);
//        rv.setLayoutManager(new LinearLayoutManager(getContext()));
//        rv.setHasFixedSize(true);
//        rv.setAdapter(ra);

        return view;
    }
}
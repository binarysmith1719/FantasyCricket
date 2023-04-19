package com.codezilla.ipl;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class userjoinedFragment extends Fragment {

    public userjoinedFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_userjoined, container, false);
        RecyclerView userRV = view.findViewById(R.id.userRV);
        ArrayList<String> as= new ArrayList<>();
        as.add("SUBHAM KUMAR");
        as.add("SOURABJIT KUMAR");
        as.add("ABHIJEET KUMAR");
        as.add("VIJAY MISRA");
        as.add("ABHINAV KESRI");

        itemAdapter itmadap = new itemAdapter(getContext(),as);
        userRV.setLayoutManager(new LinearLayoutManager(getContext()));
        userRV.setHasFixedSize(true);
        userRV.setAdapter(itmadap);

        return view;
    }
}
package com.codezilla.ipl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MyContests extends AppCompatActivity {
    TabLayout tl;
    ViewPager vp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_contests);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tl = findViewById(R.id.tab);
        vp = findViewById(R.id.viewpager);

        PagerFragmentAdapter pfa= new PagerFragmentAdapter(getSupportFragmentManager());
        vp.setAdapter(pfa);
        tl.setupWithViewPager(vp);
    }
}
package com.codezilla.ipl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;

public class language extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("STATEMENTS");

        TabLayout tabl= findViewById(R.id.tabl);
        ViewPager vp= findViewById(R.id.viewpagerl);

        vp.setAdapter(new languageVPandFRAGadapter(getSupportFragmentManager()));
        tabl.setupWithViewPager(vp);
    }

}
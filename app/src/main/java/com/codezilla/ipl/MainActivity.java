package com.codezilla.ipl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.intellij.lang.annotations.Language;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    private String MAIN_KEY="main_key";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences getshpf= getSharedPreferences(MAIN_KEY,MODE_PRIVATE);
        Boolean value = getshpf.getBoolean("logged",false);
        if(value==false)
        {
            Intent intent = new Intent(MainActivity.this,login.class);
            finish();
            startActivity(intent);
        }
//        SharedPreferences.Editor editor = getshpf.edit();
//        editor.putBoolean("logged",false);
//        editor.apply();

        Toolbar toolBar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolBar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this,drawer,toolBar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Button btn = findViewById(R.id.btn);
        Button btn3= findViewById(R.id.btn3);
        Intent intent= new Intent(this,MyContests.class);
        Intent intent1= new Intent(this,dashboard.class);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

        Button mycontest = findViewById(R.id.btn2);
        mycontest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent1);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, language.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.nav_coins:
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("COINS");
                dialog.setMessage("Total coins are 9090");
                dialog.setIcon(R.drawable.ic_baseline_check_box_24);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog.show();
                break;
            case R.id.nav_points:
                AlertDialog.Builder dialog1 = new AlertDialog.Builder(MainActivity.this);
                dialog1.setTitle("POINTS");
                dialog1.setMessage("Total points are 9090");
                dialog1.setIcon(R.drawable.ic_baseline_check_box_24);
                dialog1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog1.show();
                break;
            case R.id.nav_wallet:
                AlertDialog.Builder dialog2 = new AlertDialog.Builder(MainActivity.this);
                dialog2.setTitle("WALLET BALANCE");
                dialog2.setMessage("Your Wallet Balance is 9090");
                dialog2.setIcon(R.drawable.ic_baseline_check_box_24);
                dialog2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog2.show();
                break;
            case R.id.nav_help:
                Toast.makeText(MainActivity.this, "Help", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:
                Intent intent= new Intent(this, login.class);
                startActivity(intent);
                break;
            case R.id.nav_profile:
                Intent intent2= new Intent(this, profile.class);
                startActivity(intent2);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
}
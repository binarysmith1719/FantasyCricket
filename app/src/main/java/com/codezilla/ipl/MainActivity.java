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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.intellij.lang.annotations.Language;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    private String MAIN_KEY="main_key";
    private String LEAGUE_KEY="lgkey";
    String UID_KEY="main_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences shpf = getSharedPreferences(LEAGUE_KEY,MODE_PRIVATE);
        Boolean logadmin=shpf.getBoolean("ADMINlogged",false);

        SharedPreferences getshpf= getSharedPreferences(MAIN_KEY,MODE_PRIVATE);
        Boolean value = getshpf.getBoolean("logged",false);

        if(value==false && logadmin==false )
        {
            Intent intent= new Intent(MainActivity.this,SelectLoginType.class);
            finish();
            startActivity(intent);
        }
        else if(logadmin==true)
        {
            Intent in= new Intent(MainActivity.this,AdminDash.class);
            finish();
            startActivity(in);
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
                Intent in= new Intent(MainActivity.this,CheckWinners.class);
                startActivity(in);
            }
        });

        SharedPreferences shpfx = getSharedPreferences(UID_KEY,MODE_PRIVATE);
        String usernamex=shpfx.getString("username","John doe");
//        TextView txt= navigationView.findViewById(R.id.textusername);
          View  vw=navigationView.getHeaderView(0);
          TextView txt= vw.findViewById(R.id.textusername);
          txt.setText(usernamex);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        SharedPreferences getshpf= getSharedPreferences(UID_KEY,MODE_PRIVATE);
        switch(item.getItemId())
        {
            case R.id.nav_coins:
                int coins = getshpf.getInt("coins",2);
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("COINS");
                dialog.setMessage("Total coins are "+Integer.toString(coins));
                dialog.setIcon(R.drawable.ic_baseline_check_box_24);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog.show();
                break;
            case R.id.nav_points:
                int pnts = getshpf.getInt("points",2);
                AlertDialog.Builder dialog1 = new AlertDialog.Builder(MainActivity.this);
                dialog1.setTitle("POINTS");
                dialog1.setMessage("Total points are "+Integer.toString(pnts));
                dialog1.setIcon(R.drawable.ic_baseline_check_box_24);
                dialog1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog1.show();
                break;
            case R.id.nav_wallet:
                int balance = getshpf.getInt("wallet",2);
                AlertDialog.Builder dialog2 = new AlertDialog.Builder(MainActivity.this);
                dialog2.setTitle("WALLET BALANCE");
                dialog2.setMessage("Your Wallet Balance is "+Integer.toString(balance));
                dialog2.setIcon(R.drawable.ic_baseline_check_box_24);
                dialog2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog2.setNegativeButton("+ADD Balance",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent= new Intent(MainActivity.this,AddAmount.class);
                        startActivity(intent);
                    }
                });
                dialog2.show();
                break;
            case R.id.nav_help:
                Toast.makeText(MainActivity.this, "Help", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:

                SharedPreferences shpf = getSharedPreferences(UID_KEY,MODE_PRIVATE);
                SharedPreferences.Editor editor = shpf.edit();
                editor.putBoolean("logged",false);
                editor.apply();

                SharedPreferences Xshpf= getSharedPreferences(LEAGUE_KEY,MODE_PRIVATE);
                SharedPreferences.Editor editorx = Xshpf.edit();
                editorx.putBoolean("ADMINlogged",false);
                editorx.apply();

                Intent intent= new Intent(MainActivity.this,SelectLoginType.class);
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
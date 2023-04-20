package com.codezilla.ipl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminDash extends AppCompatActivity {
    private String MAIN_KEY="main_key";
    private String LEAGUE_KEY="lgkey";
    String UID_KEY="main_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dash);

        Button btn1=findViewById(R.id.btnaddlg1);
        Button btn2=findViewById(R.id.btnresult);
        Button winr=findViewById(R.id.winner);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(AdminDash.this,AddLeague.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(AdminDash.this,Admin_declareResult.class);
                startActivity(in);
            }
        });

        winr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in2 = new Intent(AdminDash.this,CheckWinners.class);
                startActivity(in2);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.walt:
                Toast.makeText(this, "Wallet Balance is XXXX", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Lgout:
                SharedPreferences shpf = getSharedPreferences(UID_KEY,MODE_PRIVATE);
                SharedPreferences.Editor editor = shpf.edit();
                editor.putBoolean("logged",false);
                editor.apply();

                SharedPreferences Xshpf= getSharedPreferences(LEAGUE_KEY,MODE_PRIVATE);
                SharedPreferences.Editor editorx = Xshpf.edit();
                editorx.putBoolean("ADMINlogged",false);
                editorx.apply();

                Intent intent= new Intent(AdminDash.this,SelectLoginType.class);
                startActivity(intent);
                return true;
            default:return super.onOptionsItemSelected(item);
        }

    }
}
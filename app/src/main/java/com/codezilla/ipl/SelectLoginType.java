package com.codezilla.ipl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectLoginType extends AppCompatActivity {
    private String MAIN_KEY="main_key";
    private String LEAGUE_KEY="lgkey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_login_type);
        Button user=findViewById(R.id.button);
        Button admin=findViewById(R.id.admin);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SharedPreferences getshpf= getSharedPreferences(MAIN_KEY,MODE_PRIVATE);
//                SharedPreferences.Editor editor = getshpf.edit();
//                editor.putBoolean("logged",true);
//                editor.apply();
//
//                SharedPreferences shpf= getSharedPreferences(LEAGUE_KEY,MODE_PRIVATE);
//                SharedPreferences.Editor editorx = shpf.edit();
//                editorx.putBoolean("ADMINlogged",false);
//                editorx.apply();

                Intent intent = new Intent(SelectLoginType.this,login.class);
                startActivity(intent);
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SharedPreferences getshpf= getSharedPreferences(MAIN_KEY,MODE_PRIVATE);
//                SharedPreferences.Editor editor = getshpf.edit();
//                editor.putBoolean("logged",false);
//                editor.apply();
//
//                SharedPreferences shpf= getSharedPreferences(LEAGUE_KEY,MODE_PRIVATE);
//                SharedPreferences.Editor editorx = shpf.edit();
//                editorx.putBoolean("ADMINlogged",true);
//                editorx.apply();

                Intent in = new Intent(SelectLoginType.this,AdminLogin.class);
                startActivity(in);
            }
        });
    }
}
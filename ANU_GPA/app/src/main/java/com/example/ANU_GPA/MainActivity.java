package com.example.ANU_GPA;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

/*Authorship Prateek Arora (u6742441) and Kalai (u6555407)*/

public class MainActivity extends AppCompatActivity {

    /*Authorship of password sharedpreference, jared graf*/
    SharedPreferences pass;
    private static int Time_Out = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView anuTextView=findViewById(R.id.anuTextView);
        final TextView  gpaTextView=findViewById(R.id.gpaTextView);
        new ScaleEffect(new TextView[]{anuTextView,gpaTextView}).setDuration(1000);
        final SharedPreferences pass = getSharedPreferences("com.example.ANU_GPA.Passwords", MODE_PRIVATE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(pass.contains("password")){
                    if(pass.getString("password", null)==null){
                        Intent intent = new Intent(MainActivity.this, Home.class);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(MainActivity.this, PasswordEntry.class);
                        startActivity(intent);
                    }
                }else{
                    SharedPreferences.Editor edit = pass.edit();
                    edit.putString("password", null);
                    edit.commit();
                    Intent intent = new Intent(MainActivity.this, Home.class);
                    startActivity(intent);
                }
                finish();
            }
        }, Time_Out);

    }
}
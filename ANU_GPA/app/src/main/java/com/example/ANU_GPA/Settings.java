package com.example.ANU_GPA;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

/*Authorship: Prateek Arora (u6742441)*/

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        LinearLayout linearLayout1 = findViewById(R.id.linearLayout1);
        LinearLayout linearLayout2 = findViewById(R.id.linearLayout2);
        LinearLayout linearLayout3 = findViewById(R.id.linearLayout3);
        LinearLayout linearLayout4 = findViewById(R.id.linearLayout4);
        LinearLayout[] layouts=new LinearLayout[]{linearLayout1,linearLayout2,linearLayout3,linearLayout4};
        new ScaleEffect(layouts);

        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //depending on if you have a password, either sends you to set or change password
                final SharedPreferences pass = getSharedPreferences("com.example.ANU_GPA.Passwords", MODE_PRIVATE);
                if(pass.getString("password", "")==""){
                    Intent intent = new Intent(Settings.this,PasswordSet.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(Settings.this,Password.class);
                    startActivity(intent);
                }

            }
        });

        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Settings.this, GiveFeedback.class);
                startActivity(intent);

            }
        });

        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //blocks access to update if it will be useless(no data to update)
                final SharedPreferences data = getSharedPreferences("com.example.ANU_GPA.Data", Context.MODE_PRIVATE);
                if(data.getBoolean("HasValues", false)){
                    Intent intent = new Intent(Settings.this, Update.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(Settings.this, "There is no data to update", Toast.LENGTH_LONG).show();
                }
            }
        });

        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this,About.class);
                startActivity(intent);
            }
        });




    }
}

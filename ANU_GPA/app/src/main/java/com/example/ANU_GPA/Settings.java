package com.example.ANU_GPA;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

/*Authorship: Prateek Arora (u6742441)*/

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        LinearLayout linearLayout1 = findViewById(R.id.PasswordLinearLayout);
        LinearLayout linearLayout2 = findViewById(R.id.FeedbackLinearLayout);
        LinearLayout linearLayout3 = findViewById(R.id.UpdateLinearLayout);
        LinearLayout linearLayout4 = findViewById(R.id.AboutLinearLayout);
        LinearLayout[] layouts=new LinearLayout[]{linearLayout1,linearLayout2,linearLayout3,linearLayout4};
        new ScaleEffect(layouts);

        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                Intent intent = new Intent(Settings.this, FontSize.class);
                startActivity(intent);

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

package com.example.ANU_GPA;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

/*Authorship:Prateek Arora (u6742441)*/

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final LinearLayout gpaCalcLinearLayout = findViewById(R.id.linearLayout1);
        final LinearLayout permutationLinearLayout=findViewById(R.id.linearLayout2);
        final LinearLayout settingsLinearLayout=findViewById(R.id.linearLayout3);
        //For extensibility
        final LinearLayout[] arr = new LinearLayout[]{gpaCalcLinearLayout,permutationLinearLayout,settingsLinearLayout};
        new ScaleEffect(arr);

       /* Thread thread=new Thread(){
            @Override
            public void run() {
                new ScaleEffect(arr);
            }
        };
        thread.start();*/

        gpaCalcLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, GPACalc.class);
                startActivity(intent);

            }
        });

        permutationLinearLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, PermutationCalc.class);
                startActivity(intent);

            }
        });

        settingsLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,Settings.class);
                startActivity(intent);
            }
        });
    }
}

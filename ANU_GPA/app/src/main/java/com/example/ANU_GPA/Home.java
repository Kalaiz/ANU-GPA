package com.example.ANU_GPA;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

/*Authorship:Prateek Arora (u6742441)*/

public class Home extends AppCompatActivity {
    SharedPreferences dataSharedPreferences;
    ScaleEffect scaleEffect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final LinearLayout gpaCalcLinearLayout = findViewById(R.id.gpaCalcLinearLayout);
        final LinearLayout permutationLinearLayout = findViewById(R.id.permutationLinearLayout);
        final LinearLayout settingsLinearLayout = findViewById(R.id.settingsLinearLayout);
        dataSharedPreferences = getSharedPreferences("com.example.ANU_GPA.Data", Context.MODE_PRIVATE);
        //For extensibility
        final LinearLayout[] arr = new LinearLayout[]{gpaCalcLinearLayout, permutationLinearLayout, settingsLinearLayout};

        scaleEffect= new ScaleEffect(arr);



        gpaCalcLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, GPACalc.class);
                startActivity(intent);
            }
        });

        permutationLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, PermutationCalc.class);
                startActivity(intent);
            }
        });

        settingsLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Settings.class);
                startActivity(intent);
            }
        });

    }


    /*Once An Inner activity is closed & given that this is the parent activity of the inner activity
Update the Animation*/
    public void onResume() {
        super.onResume();
        dataSharedPreferences = getSharedPreferences("com.example.ANU_GPA.Data", Context.MODE_PRIVATE);
        if(dataSharedPreferences.getBoolean("animation",true)) {
            if(scaleEffect.animationEnd) {
                scaleEffect.setAnimationEnd(false);
                scaleEffect.startAnimation();
            }
        }
        else{
            scaleEffect.setAnimationEnd(true);
        }
    }

}

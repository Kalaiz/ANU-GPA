package com.example.ANU_GPA;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final LinearLayout gpaCalc = (LinearLayout) findViewById(R.id.linearlayout1);
        final LinearLayout permutation=(LinearLayout) findViewById(R.id.linearlayout2);
        final LinearLayout settings=(LinearLayout) findViewById(R.id.linearlayout3);

        gpaCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, GPACalc.class);
                startActivity(intent);

            }
        });


        permutation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, PermutationCalc.class);
                startActivity(intent);

            }
        });


        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,Settings.class);
                startActivity(intent);
            }
    });
    }
}

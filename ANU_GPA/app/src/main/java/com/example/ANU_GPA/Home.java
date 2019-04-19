package com.example.ANU_GPA;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Handler;
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
        final LinearLayout gpaCalcLinearLayout = (LinearLayout) findViewById(R.id.linearLayout1);
        final LinearLayout permutationLinearLayout=(LinearLayout) findViewById(R.id.linearLayout2);
        final LinearLayout settingsLinearLayout=(LinearLayout) findViewById(R.id.linearLayout3);
        //For extensibility


        Thread t = new Thread(){

        };


        effect(gpaCalcLinearLayout);

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




    public void effect(View view){
        final AnimatorSet scaleIn =new AnimatorSet();
        final AnimatorSet scaleOut=new AnimatorSet();
        final AnimatorSet effect=new AnimatorSet();
        final  ObjectAnimator scaleX= ObjectAnimator.ofFloat(view, "scaleX", 0.95f);
        final  ObjectAnimator scaleY= ObjectAnimator.ofFloat(view, "scaleY", 0.95f);
        final  ObjectAnimator originalScaleX= ObjectAnimator.ofFloat(view, "scaleX", 1);
        final  ObjectAnimator originalScaleY= ObjectAnimator.ofFloat(view, "scaleY", 1);

        scaleIn.setDuration(2000);
        scaleOut.setDuration(2000);
        scaleIn.playTogether(scaleX,scaleY);
        scaleOut.playTogether(originalScaleX,originalScaleY);
        effect.playSequentially(scaleIn,scaleOut);
        effect.start();

        effect.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                effect.start();
            }
        });
    }
}

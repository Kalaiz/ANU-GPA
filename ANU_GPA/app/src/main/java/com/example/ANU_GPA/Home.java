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
        final AnimatorSet scaleIn =new AnimatorSet();
        final AnimatorSet scaleOut=new AnimatorSet();
        final AnimatorSet effect=new AnimatorSet();


       final  ObjectAnimator anim= ObjectAnimator.ofFloat(gpaCalcLinearLayout, "rotationY", 360f)
                ;

        final  ObjectAnimator anim2= ObjectAnimator.ofFloat(gpaCalcLinearLayout, "rotationY", -360f)
                ;


        final  ObjectAnimator scaleX= ObjectAnimator.ofFloat(gpaCalcLinearLayout, "scaleX", 0.9f);

        final  ObjectAnimator scaleY= ObjectAnimator.ofFloat(gpaCalcLinearLayout, "scaleY", 0.9f);

        final  ObjectAnimator originalScaleX= ObjectAnimator.ofFloat(gpaCalcLinearLayout, "scaleX", 1);

        final  ObjectAnimator originalScaleY= ObjectAnimator.ofFloat(gpaCalcLinearLayout, "scaleY", 1);


        scaleIn.setDuration(1500);
        scaleOut.setDuration(1500);
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



//        ObjectAnimator.ofFloat(gpaCalcLinearLayout, "scaleY", .75f)
//        .setDuration(2000)
//                .start();
//        new Handler().postDelayed(new Runnable() {
//                                      @Override
//                                      public void run() {
//                                          ObjectAnimator.ofFloat(gpaCalcLinearLayout, "scaleX", 1f)
//                                                  .setDuration(2000)
//                                                  .start();
//                                          ObjectAnimator.ofFloat(gpaCalcLinearLayout, "scaleY", 1f)
//                                                  .setDuration(2000)
//                                                  .start();
//                                      }
//                                  },2000);

    /*    Animator.AnimatorListener animatorListener
                = new Animator.AnimatorListener() {

            public void onAnimationStart(Animator animation) {

            }

            public void onAnimationRepeat(Animator animation) {

            }

            public void onAnimationEnd(Animator animation) {
                scaleOut.start();
            }

            public void onAnimationCancel(Animator animation) {

            }
        };

        Animator.AnimatorListener animatorListener2
                = new Animator.AnimatorListener() {

            public void onAnimationStart(Animator animation) {

            }

            public void onAnimationRepeat(Animator animation) {

            }

            public void onAnimationEnd(Animator animation) {
                scaleIn.start();
            }

            public void onAnimationCancel(Animator animation) {

            }
        };

        scaleIn.start();
        scaleOut.start();
        scaleIn.addListener(animatorListener);
        scaleOut.addListener(animatorListener2);
*/



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

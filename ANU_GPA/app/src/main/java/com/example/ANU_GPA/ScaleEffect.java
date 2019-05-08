package com.example.ANU_GPA;



import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Handler;
import android.view.View;

import java.util.HashMap;


/*Authorship: Kalai(u6555407)*/

public  class ScaleEffect  <T extends View>{

    private int duration=2000;
    T[] viewObjects;
    boolean animationEnd=false;


    ScaleEffect(T [] viewObjects){
        this.viewObjects =viewObjects;
        for(int i=0;i<viewObjects.length;i++ ){
            final T val =viewObjects[i];
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    runEffect(val);
                }
            }, (i)*1000);
        }
    }

    public void setAnimationEnd(boolean animationEnd) {
        this.animationEnd = animationEnd;
    }

    void runEffect(T val){
        final AnimatorSet effect =scaleEffect(val);
        effect.start();
        AnimatorListenerAdapter adapter = new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if(!animationEnd) {
                    effect.start();
                }
            }
        };
        effect.addListener(adapter);
    }


    ScaleEffect(T element){
        AnimatorSet effect=scaleEffect(element);
        runEffect(element);
    }



    public AnimatorSet scaleEffect(T viewElement){
        AnimatorSet scaleIn =new AnimatorSet();
        AnimatorSet scaleOut=new AnimatorSet();
        final AnimatorSet effect=new AnimatorSet();
        ObjectAnimator scaleX= ObjectAnimator.ofFloat(viewElement, "scaleX", 0.95f);
        ObjectAnimator scaleY= ObjectAnimator.ofFloat(viewElement, "scaleY", 0.95f);
        ObjectAnimator originalScaleX= ObjectAnimator.ofFloat(viewElement, "scaleX", 1);
        ObjectAnimator originalScaleY= ObjectAnimator.ofFloat(viewElement, "scaleY", 1);

        scaleIn.setDuration(duration);
        scaleOut.setDuration(duration);
        scaleIn.playTogether(scaleX,scaleY);
        scaleOut.playTogether(originalScaleX,originalScaleY);
        effect.playSequentially(scaleIn,scaleOut);
     return effect;
    }

    void setDuration(int duration){
        this.duration=duration;
    }


}

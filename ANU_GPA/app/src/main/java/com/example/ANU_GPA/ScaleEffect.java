package com.example.ANU_GPA;



import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Handler;
import android.view.View;


/*Authorship: Kalai(u6555407)*/

public  class ScaleEffect  <T extends View>{

    ScaleEffect(T [] array){
        for(int i=0;i<array.length;i++ ){
            final T val =array[i];
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    effect(val);
                }
            }, (i)*1000);
        }
    }

    ScaleEffect(T element){
        effect(element);
    }


    public void effect(T viewElement){
        final AnimatorSet scaleIn =new AnimatorSet();
        final AnimatorSet scaleOut=new AnimatorSet();
        final AnimatorSet effect=new AnimatorSet();
        final ObjectAnimator scaleX= ObjectAnimator.ofFloat(viewElement, "scaleX", 0.95f);
        final  ObjectAnimator scaleY= ObjectAnimator.ofFloat(viewElement, "scaleY", 0.95f);
        final  ObjectAnimator originalScaleX= ObjectAnimator.ofFloat(viewElement, "scaleX", 1);
        final  ObjectAnimator originalScaleY= ObjectAnimator.ofFloat(viewElement, "scaleY", 1);

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

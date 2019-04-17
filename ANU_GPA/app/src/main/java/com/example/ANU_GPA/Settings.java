package com.example.ANU_GPA;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity {

     private ViewPager viewPager;
     private SlideAdapter slideAdapter;
     private Button feedback_button;
     private int mcuurentpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        feedback_button = (Button) findViewById(R.id.feedback_button);
        viewPager = (ViewPager)findViewById(R.id.viewpager);

        slideAdapter = new SlideAdapter(this);

        viewPager.setAdapter(slideAdapter);

        ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                mcuurentpage = i;

                if(i == 0){
                    feedback_button.setVisibility(View.VISIBLE);
                    feedback_button.setText("Feedback");

                }

                else if(i == 1){
                    feedback_button.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        };
    }


}

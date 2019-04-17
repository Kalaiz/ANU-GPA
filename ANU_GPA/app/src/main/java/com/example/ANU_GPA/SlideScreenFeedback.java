package com.example.ANU_GPA;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class
SlideScreenFeedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_screen__feedback);
        TextView textView = (TextView) findViewById(R.id.slide_textview);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SlideScreenFeedback.this,Give_Feedback.class);
                startActivity(intent);
            }
        });
    }
}

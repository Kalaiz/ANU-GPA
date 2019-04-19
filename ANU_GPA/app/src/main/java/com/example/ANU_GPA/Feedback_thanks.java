package com.example.ANU_GPA;

import android.content.Intent;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;

// Authorship Prateek Arora , u6742441

public class Feedback_thanks extends AppCompatActivity {
    private static int Time_Out = 3300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_thanks);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Feedback_thanks.this, Settings.class);
                startActivity(intent);
                finish();
            }
        }, Time_Out);

    }
}

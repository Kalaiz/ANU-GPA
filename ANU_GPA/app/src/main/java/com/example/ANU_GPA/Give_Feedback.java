package com.example.ANU_GPA;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

// Authorship Prateek Arora , u6742441

public class Give_Feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give__feedback);


        RadioButton radioButton1 = (RadioButton)findViewById(R.id.radio_button1);
        RadioButton radioButton2 = (RadioButton)findViewById(R.id.radio_button2);
        RadioButton radioButton3 = (RadioButton)findViewById(R.id.radio_button3);

        Button button = (Button) findViewById(R.id.submit_feedback);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Give_Feedback.this,Feedback_thanks.class);
                startActivity(intent);
            }
        });


    }

}

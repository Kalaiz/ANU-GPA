package com.example.ANU_GPA;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

/*Authorship Prateek Arora (u6742441) and Kalai (u6555407)*/

public class MainActivity extends AppCompatActivity {
    private static int Time_Out = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView anuTextView=findViewById(R.id.anuTextView);
        final TextView  gpaTextView=findViewById(R.id.gpaTextView);
        final SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("cgpa",0);
        editor.putInt("numOfTCourses",0);
        editor.apply();
        new ScaleEffect(new TextView[]{anuTextView,gpaTextView});
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Home.class);
                startActivity(intent);
                finish();
            }
        }, Time_Out);

    }
}
package com.example.ANU_GPA;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button gpaCalc = (Button) findViewById(R.id.gpaCalcButton);
        Button permutation=(Button) findViewById(R.id.permutationButton);
        Button settings=(Button) findViewById(R.id.settingsButton);

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


        settings.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {
        }

        });
    }
}

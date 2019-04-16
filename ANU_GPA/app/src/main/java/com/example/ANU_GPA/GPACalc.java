package com.example.ANU_GPA;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class GPACalc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpacalc);
        final TextView result = (TextView) findViewById(R.id.resultTextView);
        Button done = (Button) findViewById(R.id.doneButton);
        final EditText hd = (EditText) findViewById(R.id.hdEditText);
        final EditText d = (EditText) findViewById(R.id.dEditText);
        final EditText c = (EditText) findViewById(R.id.cEditText);
        final EditText p = (EditText) findViewById(R.id.pEditText);
        final EditText f = (EditText) findViewById(R.id.fEditText);
        final ScrollView scrollView =(ScrollView) findViewById(R.id.scrollView);
        final SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String[] gradeVal = {"nhd", "nd", "ncr", "np", "nf"};
                EditText[] numbersOfGrades = new EditText[]{hd, d, c, p, f};
                int[] nGrades = new int[5];
                boolean error=false;
                for (int i = 0; i < 5; i++) {
                    try {
                        nGrades[i] = Integer.parseInt(numbersOfGrades[i].getText().toString());
                        editor.putInt(gradeVal[i], nGrades[i]);
                    } catch (NumberFormatException n) {
                        error=true;
                    }
                }
                if(error){Toast.makeText(GPACalc.this, "Note:Your are neglecting some attributes;" +
                        "It's values will be considered as 0.", Toast.LENGTH_LONG).show();}
                GPA gpa = new GPA(nGrades);
                findViewById(R.id.yourGPAisTextView).setVisibility(View.VISIBLE);
                result.setText(gpa.cgpa + " ");
                editor.putFloat("CGPA", gpa.cgpa);
                editor.putInt("numOfTCourses", gpa.numOfTCourses);
                editor.apply();
                result.setVisibility(View.VISIBLE);
                Toast.makeText(GPACalc.this, "Extracted marks", Toast.LENGTH_LONG).show();
                scrollView.fullScroll(View.FOCUS_UP);

            }
        });

    }
}
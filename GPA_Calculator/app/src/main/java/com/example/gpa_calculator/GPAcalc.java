package com.example.gpa_calculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GPAcalc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dont_know);
        final TextView result =(TextView) findViewById(R.id.resultTextView);
        Button done = (Button) findViewById(R.id.done);
        final EditText hd = (EditText) findViewById(R.id.hd_edit);
        final EditText d = (EditText) findViewById(R.id.d_edit) ;
        final EditText c = (EditText) findViewById(R.id.c_edit);
        final EditText pass = (EditText) findViewById(R.id.pass_edit) ;
        final EditText fail = (EditText) findViewById(R.id.fail_edit);
        final SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String[] gradeVal={"nhd","nd","ncr","np","nf"};
                EditText[] numbersOfGrades= new EditText[]{hd,d,c,pass,fail};
                int[] ngrades =new int[5];
                try {
                    for (int i = 0; i < 5; i++) {
                        ngrades[i] = Integer.parseInt(numbersOfGrades[i].getText().toString());
                        editor.putInt(gradeVal[i], ngrades[i]);
                    }
                }
                catch (NumberFormatException n){
                    Toast.makeText(GPAcalc.this,"Wrong input,Try Again",Toast.LENGTH_LONG).show();
                }
                Toast.makeText(GPAcalc.this,"Extract marks",Toast.LENGTH_LONG).show();
                GPA gpa =new GPA(ngrades);
                findViewById(R.id.yourGPAisTextView).setVisibility(View.VISIBLE);
                result.setText(gpa.cgpa+" ");
                editor.putFloat("CGPA",gpa.cgpa);
                editor.putInt("numOfTCourses",gpa.numOfTCourses);
                editor.apply();
               // v.animate().alpha(-10);//making it invisible
                result.setVisibility(View.VISIBLE);

            }
        });

    }
}

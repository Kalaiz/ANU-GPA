package com.example.ANU_GPA;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
/**this class allows you to update the stored data of the GPA calculator.
 * rather than supplying all your grades, the user may supply only the new grades since they last calculated their gpa
 * @author jared */
public class Update extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        final SharedPreferences data = getSharedPreferences("com.example.ANU_GPA.Data", MODE_PRIVATE);
        /**below code is borrowed from GPACalc, but reworked to work with my code*/
        Button doneButton = findViewById(R.id.doneButton);
        final EditText hd = findViewById(R.id.hdEditText);
        final EditText d = findViewById(R.id.dEditText);
        final EditText c = findViewById(R.id.cEditText);
        final EditText p = findViewById(R.id.pEditText);
        final EditText f = findViewById(R.id.fEditText);
        final TextView result = findViewById(R.id.resultTextView);
        final int[] GradeValues = {7, 6, 5, 4, 0};
        final EditText[] numbersOfGrades = new EditText[]{hd, d, c, p, f};
        result.setText(data.getFloat("cgpa", 0) +"");
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean error = false;
                int totalPoints = data.getInt("currentPoints", 0);
                int totalClasses = data.getInt("numOfTCourses", 0);
                for (int i = 0; i < 5; i++) {
                    try {
                        totalPoints = GradeValues[i] * Integer.parseInt(numbersOfGrades[i].getText().toString()) + totalPoints;
                        totalClasses = totalClasses + Integer.parseInt(numbersOfGrades[i].getText().toString());
                    } catch (NumberFormatException n) {
                        error = true;
                    }
                }
                if (error) {
                    Toast.makeText(Update.this, "Note:Your are neglecting some attributes;" +
                            "It's values will be considered as 0.", Toast.LENGTH_SHORT).show();
                }
                /**the following is completely my own code*/
                SharedPreferences.Editor edit = data.edit();
                edit.putInt("numOfTCourses", totalClasses);
                edit.putInt("currentPoints", totalPoints);
                if(totalClasses == 0){
                    edit.putFloat("cgpa", (float)0.0);
                }else{
                    edit.putFloat("cgpa", ((float)totalPoints / totalClasses));
                }
                edit.commit();
                result.setText(data.getFloat("cgpa", 0) +"");
            }
        });
    }
}

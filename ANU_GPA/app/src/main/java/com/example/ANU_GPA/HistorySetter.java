package com.example.ANU_GPA;


import android.content.SharedPreferences;
import java.util.ArrayList;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class HistorySetter extends AppCompatActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final SharedPreferences data = getSharedPreferences("com.example.ANU_GPA.Data", MODE_PRIVATE);
        setContentView(R.layout.activity_history_setter);
        final EditText name = findViewById(R.id.name);
        final Button addButton = findViewById(R.id.addButton);
        final HistoryDbHelper help = new HistoryDbHelper(HistorySetter.this);
        ArrayList<notes> noteList = help.getAllNotes();
        //need to make a function to add all notes to the activity
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString() == "") {
                    Toast.makeText(HistorySetter.this, "There is no name", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(HistorySetter.this, "Data saved", Toast.LENGTH_LONG).show();
                    float cgpa = data.getFloat("cgpa", 0);
                    int currentPoints = data.getInt("currentPoints", 0);
                    int numOfTCourses = data.getInt("numOfTCourses", 0);
                    String grades = data.getString("grades", "");
                    String title = name.getText().toString();
                    notes note = new notes();
                    note.setTitle(title);
                    note.setCurrent_GPA_value(""+cgpa);
                    note.setCurrentPoints(currentPoints+"");
                    note.setGrades(grades);
                    note.setNumber_of_courses_done_value(numOfTCourses+"");
                    help.insertUser(note);
                    //update the activity. maybe refresh/close and reopen activity, to update the arraylist
                }
            }
        });
    }
  /**  public class layoutMaker{
        LinearLayout layout;
        layoutMaker(String grades, float cgpa, int points, int classes, String name){
            TextView cgpaView = new TextView(HistorySetter.this);
            cgpaView.setTextSize(10);
            cgpaView.setText("cgpa: " + cgpa);

            TextView pointsView = new TextView(HistorySetter.this);
            cgpaView.setTextSize(10);
            cgpaView.setText("points: " + points);

            TextView courseView = new TextView(HistorySetter.this);
            cgpaView.setTextSize(10);
            cgpaView.setText("taken courses: " + classes);

            TextView gradeView = new TextView(HistorySetter.this);
            cgpaView.setTextSize(10);
            cgpaView.setText("grades(hd ->f): " + grades);

            TextView nameView = new TextView(HistorySetter.this);
            cgpaView.setTextSize(20);
            cgpaView.setText(name);

            layout = new LinearLayout(HistorySetter.this);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            layout.addView(nameView);
            layout.addView(cgpaView);
            layout.addView(gradeView);
            layout.addView(pointsView);
            layout.addView(courseView);
        }
    }*/
}
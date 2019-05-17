package com.example.ANU_GPA;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TextView;

/*Authorship Prateek Arora (u6742441)*/


public class History extends AppCompatActivity {

    HistoryDbHelper historyDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);



        TextView textView1 = (TextView) findViewById(R.id.numberOfCoursesDoneDisplayTextView);
        TextView textView2 = (TextView) findViewById(R.id.history_number_of_courses_done_value);
        TextView textView3 = (TextView) findViewById(R.id.currentGPADisplayTextView);
        TextView textView4 = (TextView) findViewById(R.id.currentGPATextView);
        TextView textView5 = (TextView) findViewById(R.id.gpaWantedDisplayTextView);
        TextView textView6 = (TextView) findViewById(R.id.gpaWantedTextView);
        TextView textView7 = (TextView) findViewById(R.id.history_courses_left);
        TextView textView8 = (TextView) findViewById(R.id.history_courses_left_value);
        historyDbHelper = new HistoryDbHelper(this);
        //TableLayout tableLayout =  (TableLayout) findViewById(R.id.history_table);
    }

}

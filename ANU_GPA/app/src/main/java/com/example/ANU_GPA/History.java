package com.example.ANU_GPA;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

/*Authorship Prateek Arora (u6742441)*/


public class History extends AppCompatActivity {

    HistoryDbHelper historyDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        TextView textView1 = (TextView) findViewById(R.id.history_number_of_courses_done);
        TextView textView2 = (TextView) findViewById(R.id.history_number_of_courses_done_value);
        TextView textView3 = (TextView) findViewById(R.id.history_current_GPA);
        TextView textView4 = (TextView) findViewById(R.id.history_current_GPA_value);
        TextView textView5 = (TextView) findViewById(R.id.history_GPA_wanted_to_achieve);
        TextView textView6 = (TextView) findViewById(R.id.history_GPA_wanted_to_achieve_value);
        TextView textView7 = (TextView) findViewById(R.id.history_courses_left);
        TextView textView8 = (TextView) findViewById(R.id.history_courses_left_value);
        historyDbHelper = new HistoryDbHelper(this);
        TableLayout tableLayout =  (TableLayout) findViewById(R.id.history_table);
    }

}

package com.example.ANU_GPA;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/*Authorship Prateek Arora (u6742441)*/


public class Storage extends AppCompatActivity {
    StorageDatabase storageDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        /*Flow of the function
         * 0)Display the sharedPreference values.
         * 1)Get the data from the database and display on the ListView
         * 2)If there is no data show that there is no data.
         * 3)On click on the ListView content let user to delete it ( without using another activity;most prolly swipe)
         * 4)If delete is activated, respective data from db is also deleted.
         * 5)Let users add data based on the current sharedPreference values.
         * */
        /*TODO
        1) Get the activity layout done
        2) Retrieve data from TextView
        3) Validate primary id (name of the set of info)
        4)
        * */



        /*TextView objects*/
        TextView numberOfCoursesDoneDisplayTextView =  findViewById(R.id.numberOfCoursesDoneDisplayTextView);
        TextView numberOfCoursesDoneTextView = findViewById(R.id.numberOfCoursesDoneTextView);
        TextView currentGPADisplayTextView =  findViewById(R.id.currentGPADisplayTextView);
        TextView currentGPATextView =  findViewById(R.id.currentGPATextView);
        TextView gpaWantedDisplayTextView=  findViewById(R.id.gpaWantedDisplayTextView);
        TextView gpaWantedTextView = findViewById(R.id.gpaWantedTextView);
        TextView numberOfCourseLeftDisplayTextView =  findViewById(R.id.numberOfCourseLeftDisplayTextView);
        TextView numberOfCourseLeftTextView=  findViewById(R.id.numberOfCourseLeftTextView);
        storageDatabase = new StorageDatabase(this);

    }

}

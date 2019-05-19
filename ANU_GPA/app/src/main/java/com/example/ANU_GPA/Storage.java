package com.example.ANU_GPA;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*Authorship Prateek Arora (u6742441)*/


public class Storage extends AppCompatActivity {
    StorageDatabase storageDatabase;



    public boolean validInput(float[] data){
        boolean valid=true;
        for(int i=0;i<data.length;i++){
            valid=valid && (i%2==0)?(data[i]<=80):data[i]<=7;
        }
return valid;
    }

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

        /*View objects*/
        EditText numberOfCoursesDoneEditText=findViewById(R.id.sNumOfCoursesDoneEditText);
        EditText currentGPAEditText=findViewById(R.id.sCurrentGPAEditText);
        EditText gpaWantedEditText=findViewById(R.id.sNumberOfCoursesEditText);
        EditText coursesLeftEditText=findViewById(R.id.sGPAWantedEditText);
        final EditText[] data={numberOfCoursesDoneEditText,currentGPAEditText,gpaWantedEditText,coursesLeftEditText};
        Button addButton=findViewById(R.id.addButton);



        final int[] resources={R.id.sNumOfCoursesDoneEditText,R.id.sCurrentGPAEditText,R.id.sNumberOfCoursesEditText,R.id.sGPAWantedEditText};
        SharedPreferences sharedPreferences= getSharedPreferences("com.example.ANU_GPA.Data",MODE_PRIVATE);
        boolean hasValues=sharedPreferences.getBoolean("hasValues",false);
        float cgpa=sharedPreferences.getFloat("cgpa",0);
        int numOfTCourses=sharedPreferences.getInt("numOfTCourses",0);
        if(hasValues){
            currentGPAEditText.setText(Float.toString(cgpa));
            numberOfCoursesDoneEditText.setText(Integer.toString(numOfTCourses));
        }

      addButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              boolean errorFree=true;
              float[] values=new float[resources.length];
              for(int i=0;i<resources.length;i++){
                  String value;
                if((value=data[i].getText().toString()).equals("")){
                    Toast.makeText(Storage.this,"There's no input.",Toast.LENGTH_SHORT).show();
                    errorFree=false;
                    break;
                }
                else{
                    values[i]=Float.parseFloat(value);
                    errorFree=true;
                }
              }
              if(errorFree && validInput(values)){

              }
              else{
                  Toast.makeText(Storage.this,"Invalid input.",Toast.LENGTH_LONG).show();
              }

          }
      });





        storageDatabase = new StorageDatabase(this);

    }

}

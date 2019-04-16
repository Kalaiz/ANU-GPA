package com.example.ANU_GPA;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Map;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        final SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);

        //for retrieving all values from sharedPreference
        Map<String,?> data=sharedPreferences.getAll();
        ArrayList<View> views=new ArrayList<>();
        for(String key:data.keySet()){
            //TODO
       ///Going to use instanceof or getClass to know the type of the wildcard ?.
        }
    }
}

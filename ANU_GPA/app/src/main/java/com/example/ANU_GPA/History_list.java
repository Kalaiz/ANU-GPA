package com.example.ANU_GPA;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/*Authorship Prateek Arora (u6742441)*/

public class History_list extends AppCompatActivity {

    RecyclerView recyclerView;
    Activity mActivity;
    HistoryDbHelper historyDbHelper;
    String userId;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);
        mActivity = History_list.this;
        historyDbHelper = new HistoryDbHelper(this);
        assignId();
    }

    private void assignId() {
        recyclerView = findViewById(R.id.recycle_view);
        linearLayoutManager = new LinearLayoutManager(mActivity,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        ArrayList<notes> allNotes = historyDbHelper.getAllNotes();
        HistoryAdapter notesAdapter = new HistoryAdapter(mActivity,allNotes);
        recyclerView.setAdapter(notesAdapter);
    }
}

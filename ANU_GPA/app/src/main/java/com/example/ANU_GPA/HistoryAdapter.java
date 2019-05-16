package com.example.ANU_GPA;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/*Authorship Prateek Arora (u6742441)*/

public class HistoryAdapter extends RecyclerView.Adapter {

    private Activity mActivity;
    private List<notes> notesList;
    private LayoutInflater layoutInflater;

    public HistoryAdapter(Activity mActivity, List<notes> notesList) {
        this.mActivity = mActivity;
        this.notesList = notesList;
        layoutInflater = mActivity.getLayoutInflater();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = layoutInflater.inflate(R.layout.row_length, viewGroup, false);
        return new FindViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        if (viewHolder instanceof FindViewHolder) {
            FindViewHolder findViewHolder = (FindViewHolder) viewHolder;
            notes notes = notesList.get(i);
            findViewHolder.textView.setText(notes.getNumber_of_courses_done_value());
            findViewHolder.textView.setText(notes.getCurrent_GPA_value());
            findViewHolder.textView.setText(notes.getCurrentPoints());
            findViewHolder.textView.setText(notes.getGrades());
            findViewHolder.textView.setText(notes.getTitle());

        }

    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class FindViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public FindViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.titleTv);
        }
    }
}

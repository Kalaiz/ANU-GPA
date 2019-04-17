package com.example.ANU_GPA;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SlideAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SlideAdapter(Context context){
        this.context = context;
    }

    public int[] slideimages = {

            R.drawable.ic_feedback_black_24dp,
            R.drawable.ic_error_black_24dp
    };

    public String[] headings = {

            "Please give the feedback about the app",
            "Prateek u6742441" + "\n" + "Kalai u6555407" + "\n"+ "Jared u5953599",
    };


    public String[] slide_text = {
            "Click here to give feedback",
            "The above people in the list created this app"

    };

    public int[] backgroundcolorarray = {
            Color.rgb(166,118,124),
            Color.rgb(166,118,124)
    };


    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view == o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.activity_slide_screen__feedback,container,false);
        RelativeLayout relativeLayoutLayout = (RelativeLayout) view.findViewById(R.id.relativelayout);
        ImageView slide_images = (ImageView)view.findViewById(R.id.feedback_pic);
        TextView slide_headings = (TextView) view.findViewById(R.id.feedback_text);
        final TextView slide_textview = (TextView) view.findViewById(R.id.slide_textview);
        final TextView slide_textview0 = (TextView) view.findViewById(R.id.slide_textview);
        relativeLayoutLayout.setBackgroundColor(backgroundcolorarray[position]);

        slide_images.setImageResource(slideimages[position]);
        slide_headings.setText(headings[position]);
        slide_textview.setText(slide_text[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((RelativeLayout)object);
    }
}

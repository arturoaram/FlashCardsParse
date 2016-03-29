package com.example.arturo.flashcardsparse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Arturo on 3/29/2016.
 */
public class FlashCardSetAdapter extends ArrayAdapter<FlashCardsSet> {
    public FlashCardSetAdapter(Context context, ArrayList<FlashCardsSet> fcSet) {
        super(context,0, fcSet);
    }
    public View getView(int position,View ConvertView, ViewGroup parent){
        FlashCardsSet flashCardsSet = getItem(position);
        if(ConvertView ==null){
            ConvertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.listview_item, parent, false);
        }
        if(flashCardsSet !=null){
            TextView tvTitle = (TextView) ConvertView.findViewById(R.id.title);
            TextView tvClassname = (TextView) ConvertView.findViewById(R.id.classname);
            TextView tvSubject = (TextView) ConvertView.findViewById(R.id.subject);
            TextView tvSchool = (TextView) ConvertView.findViewById(R.id.school);

            tvTitle.setText(flashCardsSet.title);
            tvClassname.setText(flashCardsSet.className);
            tvSubject.setText(flashCardsSet.subject);
            tvSchool.setText(flashCardsSet.school);
        }
        return ConvertView;
    }
}

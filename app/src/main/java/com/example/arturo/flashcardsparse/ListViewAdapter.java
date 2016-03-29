package com.example.arturo.flashcardsparse;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Owner on 3/28/2016.
 */
public class ListViewAdapter extends BaseAdapter {


    Context mContext;
    LayoutInflater inflater;
    private List<SetsFC> setsList = null;
    private ArrayList<SetsFC> arraylist;

    public ListViewAdapter(Context context,
                           List<SetsFC> setsList) {
        mContext = context;
        this.setsList = setsList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<SetsFC>();
        this.arraylist.addAll(setsList);
    }

    public class ViewHolder {
        TextView title;
        TextView classname;
        TextView subject;
        TextView school;
    }

    @Override
    public int getCount() {
        return setsList.size();
    }

    @Override
    public SetsFC getItem(int position) {
        return setsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item, null);
            // Locate the TextViews in listview_item.xml
            holder.title = (TextView) view.findViewById(R.id.title);
            holder.classname = (TextView) view.findViewById(R.id.classname);
            holder.subject = (TextView) view.findViewById(R.id.subject);
            holder.school=(TextView)view.findViewById(R.id.school);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.title.setText(setsList.get(position).getTitle());
        holder.classname.setText(setsList.get(position).getClassname());
        holder.subject.setText(setsList.get(position)
                .getSubject());
        holder.school.setText(setsList.get(position).getSchool());

        // Listen for ListView Item Click
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(mContext, SingleSetView.class);
                // Pass all data rank
                intent.putExtra("title",
                        (setsList.get(position).getTitle()));
                // Pass all data country
                intent.putExtra("classname",
                        (setsList.get(position).getClassname()));
                // Pass all data population
                intent.putExtra("subject",
                        (setsList.get(position).getSubject()));
                // Start SingleItemView Class

                intent.putExtra("school",(setsList.get(position).getSchool()));
                mContext.startActivity(intent);
            }
        });

        return view;
    }



}

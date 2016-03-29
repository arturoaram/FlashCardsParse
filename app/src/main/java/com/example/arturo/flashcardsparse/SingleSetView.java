package com.example.arturo.flashcardsparse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Owner on 3/28/2016.
 */
public class SingleSetView extends Activity
{



    TextView txtname;
    String title;

    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_studying_fc);

        Intent i=getIntent();
        title=i.getStringExtra("Title");
        txtname=(TextView)findViewById(R.id.title);
        txtname.setText(title);




    }
}

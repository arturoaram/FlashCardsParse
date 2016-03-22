package com.example.arturo.flashcardsparse;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Owner on 3/22/2016.
 */
public class display extends Activity {

    TextView textView;
    TextView textView2;
    ImageView imageViewdisplay;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);

        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);

        imageViewdisplay = (ImageView) findViewById(R.id.imageView2);


    }
}
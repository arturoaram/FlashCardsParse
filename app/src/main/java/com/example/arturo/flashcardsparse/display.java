package com.example.arturo.flashcardsparse;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

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

        ParseQuery<ParseObject> query = ParseQuery.getQuery("FlashCard");
        query.getInBackground("u5hI1cQjum", new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                Log.e("Query", "DID YOU FIND THIS??");
                Log.e("Object ", object.getObjectId());
                if (e == null) {
                    final String t, d;
                    ImageView i;
                    t = object.get("term").toString();
                    d = object.get("description").toString();

                    ParseFile picture = (ParseFile) object.get("picture");
                    picture.getDataInBackground(new GetDataCallback() {
                        @Override
                        public void done(byte[] data, ParseException e) {
                            if (e == null) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                                imageViewdisplay.setImageBitmap(bmp);
                                textView.setText(t);
                                textView2.setText(d);
                                Log.e("Picture", " Accesing it!");
                            } else {
                                Log.e("Picture", " Not accesing it");
                            }
                        }
                    });
                    Log.e("Object", " Accesing it!");
                } else {
                    Log.e("Object", " Not Accesing it!");
                }
            }
        });
    }
}
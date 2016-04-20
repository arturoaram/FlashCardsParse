package com.example.arturo.flashcardsparse;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class imageDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flashcard_picture_display);
        Bundle bundle = getIntent().getExtras();

        String key = bundle.getString("KEY");

        Log.e("THis is the REAL key: ", key);

        final ImageView innerIV = (ImageView) findViewById(R.id.imagePictureDisplay);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("FlashCard");
        query.whereEqualTo("objectId", key);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {



                if(parseObject.get("picture") != null){
                ParseFile image = (ParseFile) parseObject.get("picture");
                byte[] bitmapdata = new byte[0];
                try {
                    bitmapdata = image.getData();
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
                //loadBytes(image,imageView);
                Bitmap bmp = BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.length);

                innerIV.setImageBitmap(bmp);
                }
                else
                    innerIV.setImageBitmap(null);
            }
        });

    }
}
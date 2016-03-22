package com.example.arturo.flashcardsparse;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Owner on 3/7/2016.
 */
public class CreateFlashcards extends AppCompatActivity {
private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    EditText term;
    EditText definition;
    Button startCamera2;
    ImageView imageView;
TextView CreateFlashcards;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_flashcards);

//
//mRecyclerView=(RecyclerView) findViewById(R.id.flashcardClist);
//        //will create two types of vertically staggered grids
//        mStaggeredLayoutManager=new StaggeredGridLayoutManager(1,
//                StaggeredGridLayoutManager.VERTICAL);
//        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);

        term=(EditText)findViewById(R.id.term);
        definition=(EditText)findViewById(R.id.definition);
        imageView=(ImageView) findViewById(R.id.imageView);
        startCamera2=(Button) findViewById(R.id.startCamera);
final int CAMERA_PIC_REQUEST=3;

        startCamera2.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {

                        Intent intent=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        //intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                        intent.putExtra("return-data", true);
                        startActivityForResult(intent,CAMERA_PIC_REQUEST);
                    }
                });



    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK)
        {
            Bitmap bitmap=(Bitmap)data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }
    }
}
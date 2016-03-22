package com.example.arturo.flashcardsparse;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
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
    final int REQUEST_CAMERA=3;
    final int SELECT_FILE=2;
    ImageView imageView;
    TextView CreateFlashcards;
    FlashCard flashCard;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_flashcards);

//
//mRecyclerView=(RecyclerView) findViewById(R.id.flashcardClist);
//        //will create two types of vertically staggered grids
//        mStaggeredLayoutManager=new StaggeredGridLayoutManager(1,
//                StaggeredGridLayoutManager.VERTICAL);
//        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);



        term = (EditText) findViewById(R.id.editText);
        definition = (EditText) findViewById(R.id.editText2);
        imageView = (ImageView) findViewById(R.id.imageView);
        startCamera2 = (Button) findViewById(R.id.startCamera);
        final int CAMERA_PIC_REQUEST = 3;

        startCamera2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        //intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                        intent.putExtra("return-data", true);
                        startActivityForResult(intent, CAMERA_PIC_REQUEST);
                    }
                });


    }


    public void addToDB(View view){
        if(imageView !=null) {
            flashCard = new FlashCard(term.getText().toString(),
                    definition.getText().toString(),
                    imageView);
            flashCard.add();
        }
    }

//   public void selectImage() {
//       final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
//       AlertDialog.Builder builder = new AlertDialog.Builder(CreateFlashcards.this);
//       builder.setTitle("Add Photo!");
//       builder.setItems(items, new DialogInterface.OnClickListener() {
//           @Override
//           public void onClick(DialogInterface dialog, int item) {
//               if (items[item].equals("Take Photo")) {
//                   Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                   startActivityForResult(intent, REQUEST_CAMERA);
//               } else if (items[item].equals("Choose from Library")) {
//                   Intent intent = new Intent(
//                           Intent.ACTION_PICK,
//                           android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                   intent.setType("image/*");
//                   startActivityForResult(
//                           Intent.createChooser(intent, "Select File"),
//                           SELECT_FILE);
//               } else if (items[item].equals("Cancel")) {
//                   dialog.dismiss();
//               }
//           }
//       });
//       builder.show();

   //}

   protected void onActivityResult(int requestCode, int resultCode, Intent data)
  {

        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK)
     {
          Bitmap bitmap=(Bitmap)data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
 } }
}

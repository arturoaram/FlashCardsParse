package com.example.arturo.flashcardsparse;

import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Owner on 3/7/2016.
 */
public class CreateFlashcards extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    EditText term;
    EditText definition;
    ImageButton startCamera2;
    final int REQUEST_CAMERA = 3;
    final int SELECT_FILE = 2;
    ImageView imageView;
    TextView CreateFlashcards;
    FlashCard FC;
    String id;
    ParseObject flashCard;
    public static float screen_width;
    Bitmap bitmap;
    ParseObject parseObject;
    byte[] data;
    ParseFile file;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_flashcards);
        setTitle("Create FlashCards");

        id = getIntent().getStringExtra("ID");

        if (id != null)
            Log.d("THIS IS YOUR ID: ", id);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screen_width = metrics.widthPixels;

        term = (EditText) findViewById(R.id.editText);
        definition = (EditText) findViewById(R.id.editText2);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageDrawable(null);
        startCamera2 = (ImageButton) findViewById(R.id.startCamera);
        // final int REQUEST_CA = 3;

        startCamera2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
                        AlertDialog.Builder builder = new AlertDialog.Builder(CreateFlashcards.this);
                        builder.setTitle("Add Photo!");
                        builder.setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int item) {
                                if (items[item].equals("Take Photo")) {
                                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    startActivityForResult(intent, REQUEST_CAMERA);
                                } else if (items[item].equals("Choose from Library")) {
                                    Intent intent = new Intent(
                                            Intent.ACTION_PICK,
                                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                    intent.setType("image/*");
                                    startActivityForResult(
                                            Intent.createChooser(intent, "Select File"),
                                            SELECT_FILE);
                                } else if (items[item].equals("Cancel")) {
                                    dialog.dismiss();
                                }
                            }
                        });
                        builder.show();
                        // Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        //intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                        //intent.putExtra("return-data", true);
                        //startActivityForResult(intent, CAMERA_PIC_REQUEST);
                    }
                });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Toast toast = Toast.makeText(getApplicationContext(),
                "Every Time you press add, It will clear the fields and add the FlashCard to your set, Keep pressing Add for more FlashCards",
                Toast.LENGTH_LONG);
        toast.show();


    }

//    public void addToDB(View view) {
//
//
//        if (!isEmpty(term) && !isEmpty(definition)) {
//            ParseQuery<ParseObject> query = ParseQuery.getQuery("SetFC");
//            query.getInBackground(id, new GetCallback<ParseObject>() {
//                public void done(ParseObject object, ParseException e) {
//                    if (e == null) {
//                        parseObject = object.getParseObject("id");
//                        bitmap = imageView.getDrawingCache();
//                        if (bitmap != null) {
//                            flashCard = new FlashCard(term.getText().toString(),
//                                    definition.getText().toString(),
//                                    bitmap);
//                            //flashCard.add(parseObject);
//                            //clears the term and definition for next
//                            term.getText().clear();
//                            definition.getText().clear();
//                            imageView.setImageBitmap(null);
//
//                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                            byte[] data = stream.toByteArray();
//
//                            ParseFile file = new ParseFile("hola.png", data);
//                            file.saveInBackground();
//
//                            ParseObject flashCard = new ParseObject("FlashCard");
//                            flashCard.put("term", term.getText().toString());
//                            flashCard.put("description", definition.getText().toString());
//                            flashCard.put("picture", file);
//                            flashCard.put("Parent", object);
//                            flashCard.saveInBackground();
//                            Log.e("AQUI HAY FOTO? ", "SI HAY FOTO");
//
//                            Toast toastInner = Toast.makeText(getApplicationContext(),
//                                    "FlashCard added",
//                                    Toast.LENGTH_SHORT);
//                            toastInner.show();
//                        } else {
//                            flashCard = new FlashCard(term.getText().toString(),
//                                    definition.getText().toString());
//                            //flashCard.add(parseObject);
//                            //clears the term and definition for next
//                            term.getText().clear();
//                            definition.getText().clear();
//                            imageView.setImageBitmap(null);
//                            //bitmap.recycle();
//
//                            ParseObject flashCard = new ParseObject("FlashCard");
//                            flashCard.put("term" , term.getText().toString());
//                            flashCard.put("description", definition.getText().toString());
//                            flashCard.put("Parent", object);
//                            flashCard.saveInBackground();
//                            Log.e("AQUI HAY FOTO? ", "NO HAY FOTO");
//
//                            Toast toastInner = Toast.makeText(getApplicationContext(),
//                                    "FlashCard added",
//                                    Toast.LENGTH_SHORT);
//                            toastInner.show();
//                        }
//                        //flashCard.addBitmap(object);
////
//                    } else {
//                        Log.d("Parse Object: ", "It has no object inside");
//                    }
//                }
//
//            });
//
//        } else {
//            Toast toast = Toast.makeText(getApplicationContext(),
//                    "Either the Term or Definition field is empty", Toast.LENGTH_SHORT);
//            toast.show();
//        }
//    }

    public void newAddToDB(View view){
        String s = term.getText().toString();
        Log.e("This is your STRING: ",s);
        int a = term.getText().toString().length();
        Log.e("Size os STRING: "," "+a);


        if (imageView.getDrawable()!=null && !isEmpty(term) && !isEmpty(definition)) {
            imageView.setDrawingCacheEnabled(true);
            imageView.buildDrawingCache();
            bitmap = imageView.getDrawingCache();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            data = stream.toByteArray();

            FC = new FlashCard(term.getText().toString(),
                    definition.getText().toString(), bitmap);
            file = new ParseFile("hola.png", data);
            //file.saveInBackground();

            flashCard = new ParseObject("FlashCard");
            flashCard.put("term", FC.getTerm());
            flashCard.put("description", FC.getDefinition());
            Log.e("What is this?: ", definition.getText().toString());
            flashCard.put("picture", file);

            ParseQuery<ParseObject> query = ParseQuery.getQuery("SetFC");
            query.getInBackground(id, new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject parseObject, ParseException e) {
                    flashCard.put("Parent", parseObject);
                    flashCard.saveInBackground();
                    file.cancel();
                    //imageView.setImageResource(android.R.color.transparent);
                    Log.e("AQUI HAY FOTO? ", "SI HAY FOTO");
                    Toast toastInner = Toast.makeText(getApplicationContext(),
                            "FlashCard added",
                            Toast.LENGTH_SHORT);
                    toastInner.show();

                    term.getText().clear();
                    definition.getText().clear();

                    imageView.setImageBitmap(null);
                    bitmap = null;
                    data = null;
                    imageView.setDrawingCacheEnabled(false);
                    imageView.destroyDrawingCache();
                    imageView.setImageDrawable(null);

                }
            });
            query.clearCachedResult();

        } else if(!isEmpty(term) && !isEmpty(definition)){

            ParseQuery<ParseObject> query = ParseQuery.getQuery("SetFC");
            query.getInBackground(id, new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject parseObject, ParseException e) {

                    FlashCard FC = new FlashCard(term.getText().toString(),
                            definition.getText().toString());

                    ParseObject flashCard = new ParseObject("FlashCard");
                    String s = term.getText().toString();
                    flashCard.put("term", FC.getTerm());
                    Log.d("What is this?: ", s);
                    flashCard.put("description", FC.getDefinition());
                    Log.d("What is this?: ", definition.getText().toString());
                    flashCard.put("Parent", parseObject);
                    flashCard.saveInBackground();
                    Log.e("AQUI HAY FOTO? ", "NO HAY FOTO");

                    Toast toastInner = Toast.makeText(getApplicationContext(),
                            "FlashCard added",
                            Toast.LENGTH_SHORT);
                    toastInner.show();

                    term.getText().clear();
                    definition.getText().clear();
                }
            });
            query.clearCachedResult();
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(),
                "Either the Term or Definition field is empty", Toast.LENGTH_SHORT);
            toast.show();
        }


    }

    public void doneBtn(View view) {

        Intent i = new Intent(this, UserMenu.class);
        startActivity(i);
        //finish();

    }

    public boolean isEmpty(EditText string) {
        if (string.getText().toString().length() == 0) return true;

        else return false;
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                File destination = new File(Environment.getExternalStorageDirectory(),
                        System.currentTimeMillis() + ".jpg");
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageView.setImageBitmap(thumbnail);
            } else if (requestCode == SELECT_FILE) {
                Uri selectedImageUri = data.getData();
                String[] projection = {MediaStore.MediaColumns.DATA};
                CursorLoader cursorLoader = new CursorLoader(this, selectedImageUri, projection, null, null,
                        null);
                Cursor cursor = cursorLoader.loadInBackground();
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                cursor.moveToFirst();
                String selectedImagePath = cursor.getString(column_index);
                Bitmap bm;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(selectedImagePath, options);
                final int REQUIRED_SIZE = 200;
                int scale = 1;
                while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                        && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                    scale *= 2;
                options.inSampleSize = scale;
                options.inJustDecodeBounds = false;
                bm = BitmapFactory.decodeFile(selectedImagePath, options);
                imageView.setImageBitmap(bm);
            }
        }

    }
}
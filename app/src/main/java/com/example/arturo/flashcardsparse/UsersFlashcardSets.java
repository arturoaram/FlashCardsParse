package com.example.arturo.flashcardsparse;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Owner on 3/28/2016.
 */
public class UsersFlashcardSets extends AppCompatActivity {
    ListView listview;
    ArrayList<FlashCardsSet> arFlashCardsSet;
    ProgressDialog mProgressDialog;
    FlashCardSetAdapter adapter;
    private List<SetsFC> setsList = null;
    ParseUser user;
    List<ParseObject> fcSets;
    ImageView imageView;
    String h = "hola";
    List<FlashCard> result;
    //Context context= getApplicationContext();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users_fcsets);
        setTitle(user.getCurrentUser().getString("Name") + "'s Flashcard Sets");

        listview = (ListView) findViewById(R.id.listView);

        Log.d("QUERY OBJECT ID CHEKCK", user.getCurrentUser().getObjectId());
        new RemoteDataTask().execute();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("SetFC");
        query.whereEqualTo("Parent", user.getCurrentUser());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    fcSets = scoreList;
                    Log.d("score", "Retrieved " + scoreList.size() + " scores");
                    //arFlashCardsSet.addAll(scoreList);
                    arFlashCardsSet = new ArrayList<FlashCardsSet>();
                    arFlashCardsSet.addAll(parseObjectToFlashCardSetConverter(scoreList));
                    adapter = new FlashCardSetAdapter(getApplicationContext(), arFlashCardsSet);
                    listview.setAdapter(adapter);

                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
        mProgressDialog.dismiss();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Intent intent = new Intent(getApplicationContext(),StartStudyingFC.class);
                //startActivity(intent);

                final ParseObject fcSetParse = fcSets.get(i);
                //  FlashCardsSet fcSet = arFlashCardsSet.get(i);

                ParseQuery<ParseObject> query = ParseQuery.getQuery("FlashCard");
                query.whereEqualTo("Parent", fcSetParse);
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> FlashCardsList, ParseException e) {
                        if (e == null) {
                            Log.d("MY FLASHCARDSSSSSSSS", "Retrieved " + FlashCardsList.size() + " scores");
                            setContentView(R.layout.display);
                            setTitle(fcSetParse.getString("Title"));

                            RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
                            LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
                            llm.setOrientation(LinearLayoutManager.VERTICAL);
                            recList.setLayoutManager(llm);

                            ListViewAdapter cs = null;
                            try {
                                cs = new ListViewAdapter(createList(FlashCardsList.size(), FlashCardsList));
                            } catch (ParseException e1) {
                                e1.printStackTrace();
                            }
                            recList.setAdapter(cs);


                        } else {
                            Log.d("score", "Error: " + e.getMessage());
                        }
                    }
                });


            }
        });


    }  // RemoteDataTask AsyncTask

    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(UsersFlashcardSets.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Parse.com Simple ListView Tutorial");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
//            // Locate the class table named "SetFC" in Parse.com
//            setsList = new ArrayList<SetsFC>();
//            try {
//                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
//                        "SetFC");
//                query.orderByAscending("_created_at");
//                query.whereEqualTo("Parent", user.getCurrentUser());
//                ob = query.find();
//                for (ParseObject SetFC : ob) {
//                    SetsFC map = new SetsFC();
//                    map.setTitle((String) SetFC.get("Title"));
//                    // map.setClassname((String)SetFC.get("Classname"));
//                    // map.setSubject((String)SetFC.get("Subject"));
//                    //  map.setSchool((String)SetFC.get("School"));
//                    setsList.add(map);
//                }
//
//                //  ob = query.find();
//            } catch (ParseException e) {
//                Log.e("Error", e.getMessage());
//                e.printStackTrace();
//            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Locate the listview in listview_main.xml
//            listview = (ListView) findViewById(R.id.listView);
            // Pass the results into an ArrayAdapter
//            adapter = new ListViewAdapter(UsersFlashcardSets.this,
//                    setsList);
            // Retrieve object "name" from Parse.com database

            //    adapter.add((String) SetFC.get("Title"));
            //  adapter.add((String) SetFC.get("Classname"));
            // adapter.add((String) SetFC.get("School"));
            //adapter.add((String) SetFC.get("Subject"));
            //  }
            // Binds the Adapter to the ListView
//            listview.setAdapter(adapter);
            // Close the progressdialog
            // mProgressDialog.dismiss();

            // Capture button clicks on ListView items
//          listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//               @Override
//              public void onItemClick(AdapterView<?> parent, View view,
//                                       int position, long id) {
//                   // Send single item click data to SingleItemView Class
//                    Intent i = new Intent(UsersFlashcardSets.this,
//                          SingleSetView.class);
//                  // Pass data "name" followed by the position
//                   i.putExtra("Title", ob.get(position).getString("Title"));                    i.putExtra("Classname", ob.get(position).getString("Classname"));
//                   i.putExtra("School",ob.get(position).getString("School"));
//                    i.putExtra("Subject",ob.get(position).getString("Subject"));
////                    // Open SingleItemView.java Activity
//                    startActivity(i);
            //    }
            //  });
        }
    }

    public List<FlashCardsSet> parseObjectToFlashCardSetConverter(List<ParseObject> list) {
        List<FlashCardsSet> lFC = new ArrayList<FlashCardsSet>();
        FlashCardsSet fcObject;
        if (list != null) {
            for (ParseObject po : list) {
                if (po != null) {
                    fcObject = new FlashCardsSet(po);
                    lFC.add(fcObject);
                }
            }
        }
        return lFC;
    }

    private List<FlashCard> createList(int size, final List<ParseObject> fcList) throws ParseException {
        result = new ArrayList<FlashCard>();

        size = size - 1;
        for (int i = 0; i <= size; i++) {
            final int a = i;
            if (fcList.get(i).get("picture") == null) {
                FlashCard co = new FlashCard(fcList.get(i).get("term").toString(), fcList.get(i).get("description").toString());
                result.add(co);
                Log.d("Hola", "It went here Sorry");
            } else {

                ParseFile image = (ParseFile) fcList.get(i).get("picture");
                //Bitmap bmp= bytesOfObject(image);
                byte[] bitmapdata = image.getData();
                //loadBytes(image,imageView);
                Bitmap bmp = BitmapFactory.decodeByteArray(bitmapdata,0,bitmapdata.length);
                FlashCard co = new FlashCard(fcList.get(a).get("term").toString(), fcList.get(a).get("description").toString(), bmp);
                result.add(co);

            }
        }
        return result;
    }

    public void loadBytes(ParseFile image, final ImageView IV) {
        byte[] bito;
        image.getDataInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] bytes, ParseException e) {
                if(e==null){
                Log.d("Hola", "IS THIS SHOWING?");
                h = bytes.toString();
                Log.d("AAAAAAAAA:  ", h);
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                h=bmp.toString();
                Log.d("BMP VALUE:", h);
                //IV.setImageBitmap(bmp);
                }
                else{}
            }


        });
        Log.d("BMP VALUE:", h);
    }
}


















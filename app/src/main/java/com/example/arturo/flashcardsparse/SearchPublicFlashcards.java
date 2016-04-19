package com.example.arturo.flashcardsparse;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;


import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Owner on 4/3/2016.
 */
public class SearchPublicFlashcards extends AppCompatActivity {
    FlashCardSetAdapter adapter;
    private List<SetsFC> setsList = null;
    ParseUser user;
    ArrayList<FlashCardsSet> arFlashCardsSet;
    List<ParseObject> fcSets;
    EditText search;
    List<FlashCard> result;

    ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_public_flashcards);


        listView = (ListView) findViewById(R.id.listView2);


        Date now = new Date();
        long nowMillis = now.getTime();
        long oneHourInMillis = 3600000;
        //below, the commented will need to be another searchView
        //long tenMinuteMillis=600000;
        //Date anTenMinAgo=new Date(nowMillis-tenMinuteMillis);
        //query.whereGreaterThanOrEqualTo("createdAt",anTenMinAgo);
      ////  Date anHourAgo = new Date(nowMillis - oneHourInMillis);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("SetFC");
        query.whereEqualTo("Public", Boolean.TRUE);
        query.addAscendingOrder("createdAt");
      //  query.whereGreaterThanOrEqualTo("createdAt", anHourAgo);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    fcSets = scoreList;
                    Log.d("score", "Retrieved " + scoreList.size() + " scores");
                    //arFlashCardsSet.addAll(scoreList);
                    arFlashCardsSet = new ArrayList<FlashCardsSet>();
                    arFlashCardsSet.addAll(parseObjectToFlashCardSetConverter(scoreList));
                    adapter = new FlashCardSetAdapter(getApplicationContext(), arFlashCardsSet);
                    //   Log.d("score", "Retriiiiiieved " + scoreList.size() + " scores");
                    listView.setAdapter(adapter);
                    listView.setTextFilterEnabled(true);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                            final ParseObject fcSetParse = fcSets.get(position);
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



                    search=(EditText)findViewById(R.id.searchET);
                    search.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            adapter.getFilter().filter(s.toString());

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });


    }

    public List<FlashCardsSet> parseObjectToFlashCardSetConverter(List<ParseObject> list){
        List<FlashCardsSet> lFC = new ArrayList<FlashCardsSet>();
        FlashCardsSet fcObject;
        if(list!=null){
            for(ParseObject po : list){
                if(po!=null){
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


    }

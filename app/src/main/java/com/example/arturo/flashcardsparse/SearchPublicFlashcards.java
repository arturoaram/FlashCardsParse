package com.example.arturo.flashcardsparse;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
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
SearchView searchView2;
    ListView listView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_public_flashcards);


        setTitle("Search for Public Flashcards");

        listView=(ListView)findViewById(R.id.listView2);
        //SearchManager searchManager=(SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView2=(SearchView) findViewById(R.id.searchView2);

        Date now= new Date();
        long nowMillis=now.getTime();
        long oneHourInMillis=3600000;
        //below, the commented will need to be another searchView
        //long tenMinuteMillis=600000;
        //Date anTenMinAgo=new Date(nowMillis-tenMinuteMillis);
        //query.whereGreaterThanOrEqualTo("createAt",anTenMinAgo);
        Date anHourAgo=new Date(nowMillis-oneHourInMillis);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("SetFC");
        query.whereEqualTo("Public", "True");
        query.addAscendingOrder("createdAt");
        query.whereGreaterThanOrEqualTo("createdAt",anHourAgo);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    fcSets = scoreList;
                    Log.d("score", "Retrieved " + scoreList.size() + " scores");
                    //arFlashCardsSet.addAll(scoreList);
                    arFlashCardsSet = new ArrayList<FlashCardsSet>();
                    arFlashCardsSet.addAll(parseObjectToFlashCardSetConverter(scoreList));
                    adapter = new FlashCardSetAdapter(getApplicationContext(), arFlashCardsSet);
                    listView.setAdapter(adapter);

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

    }

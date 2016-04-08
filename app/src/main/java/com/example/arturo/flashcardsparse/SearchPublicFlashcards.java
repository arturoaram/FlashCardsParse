package com.example.arturo.flashcardsparse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.widget.ListView;

/**
 * Created by Owner on 4/3/2016.
 */
public class SearchPublicFlashcards extends AppCompatActivity {

    SearchView searchView;
    ListView listView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_public_flashcards);


        setTitle("Search for Public Flashcards");

        listView=(ListView)findViewById(R.id.listView2);
        //SearchManager searchManager=(SearchManager) getSystemService(Context.SEARCH_SERVICE);
        //searchView=(SearchView) findViewById(R.id.searchView2);

    }


    }

package com.example.arturo.flashcardsparse;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Owner on 3/28/2016.
 */
public class StartStudyingFC extends Activity {


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.display);
        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        //recList.setHasFixedSize(true);
        //recList.
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);


        ListViewAdapter cs = new ListViewAdapter(createList(5));
        recList.setAdapter(cs);


        //recList.setClick

    }
        private List<FlashCard> createList ( int size){
            List<FlashCard> result = new ArrayList<FlashCard>();
            for (int i = 1; i <= size; i++) {

                FlashCard ci = new FlashCard("j", "hfghfgh");
                FlashCard co = new FlashCard("asdf", "asdfasdfasdfasdf");
                result.add(ci);
                result.add(co);
            }
            return result;
        }
    }

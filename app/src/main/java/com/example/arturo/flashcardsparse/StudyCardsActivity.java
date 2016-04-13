package com.example.arturo.flashcardsparse;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Owner on 4/1/2016.
 */
public class StudyCardsActivity extends AppCompatActivity {
    List<ParseObject> fcSets;
    ArrayList<FlashCardsSet> arFlashCardsSet;
    Button Correct, Incorrect;
    int correctNUM=0;
    int incorrectNum=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardviewpager);
        Correct=(Button)findViewById(R.id.correctbtn);
        Incorrect=(Button)findViewById(R.id.incorrectbtn);

      //  final ParseObject fcSetParse = fcSets.get(i);
        //  FlashCardsSet fcSet = arFlashCardsSet.get(i);

//        ParseQuery<ParseObject> query = ParseQuery.getQuery("FlashCard");
//        query.whereEqualTo("Parent", fcSetParse);
//        query.findInBackground(new FindCallback<ParseObject>() {
//            public void done(List<ParseObject> FlashCardsList, ParseException e) {
//                if (e == null) {
//                    Log.d("MY FLASHCARDSSSSSSSS", "Retrieved " + FlashCardsList.size() + " scores");
Correct.setOnClickListener(new OnClickListener() {
    @Override
    public void onClick(View v) {


  correctNUM++;
        Correct.setText(correctNUM);
    }
});
        Incorrect.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

        incorrectNum++;
                Incorrect.setText(incorrectNum);


            }
        });



        FCardAdapter adapter = new FCardAdapter(getFragmentManager());
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
       viewPager.setAdapter(adapter);


    }


 public   class FCardAdapter extends android.support.v13.app.FragmentPagerAdapter{


        public FCardAdapter(FragmentManager fm){
            super(fm);
        }
        public Fragment getItem(int i){
            return new FCardContainerFragment();
        }

        public int getCount(){
            return 30;
        }
    }


//    private List<FlashCard> createList(int size, List<ParseObject> fcList){
//        List<FlashCard> result=new ArrayList<FlashCard>();
//        size = size-1;
//        for(int i=0;i<=size;i++){
//
//            FlashCard co=new FlashCard(fcList.get(i).get("term").toString(),fcList.get(i).get("description").toString());
//            result.add(co);
//        }
//        return result;
//    }

//
//    public List<FlashCardsSet> parseObjectToFlashCardSetConverter(List<ParseObject> list){
//        List<FlashCardsSet> lFC = new ArrayList<FlashCardsSet>();
//        FlashCardsSet fcObject;
//        if(list!=null){
//            for(ParseObject po : list){
//                if(po!=null){
//                    fcObject = new FlashCardsSet(po);
//                    lFC.add(fcObject);
//                }
//            }
//        }
//        return lFC;
//    }
//
//    private List<FlashCard> createList(int size, List<ParseObject> fcList){
//        List<FlashCard> result=new ArrayList<FlashCard>();
//        size = size-1;
//        for(int i=0;i<=size;i++){
//
//            FlashCard co=new FlashCard(fcList.get(i).get("term").toString(),fcList.get(i).get("description").toString());
//            result.add(co);
//        }
//        return result;
//    }
//


}

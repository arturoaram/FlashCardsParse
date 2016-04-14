package com.example.arturo.flashcardsparse;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Owner on 4/1/2016.
 */
public class StudyCardsActivity extends AppCompatActivity {
    List<ParseObject> fcSets;
    ArrayList<FlashCardsSet> arFlashCardsSet;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardviewpager);


      //  final ParseObject fcSetParse = fcSets.get(i);
        //  FlashCardsSet fcSet = arFlashCardsSet.get(i);

//        ParseQuery<ParseObject> query = ParseQuery.getQuery("FlashCard");
//        query.whereEqualTo("Parent", fcSetParse);
//        query.findInBackground(new FindCallback<ParseObject>() {
//            public void done(List<ParseObject> FlashCardsList, ParseException e) {
//                if (e == null) {
//                    Log.d("MY FLASHCARDSSSSSSSS", "Retrieved " + FlashCardsList.size() + " scores");


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
}

package com.example.arturo.flashcardsparse;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Owner on 4/1/2016.
 */
public class StudyCardsActivity extends Activity {
    List<ParseObject> fcSets;
    ArrayList<FlashCardsSet> arFlashCardsSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardviewpager);




       FCardAdapter adapter = new FCardAdapter(getFragmentManager());
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
       viewPager.setAdapter(adapter);

      //  ListViewAdapter cs = new ListViewAdapter(createList(FlashCardsList.size(),FlashCardsList));
    //    viewPager.setAdapter(cs);

    }


    public class FCardAdapter extends android.support.v13.app.FragmentPagerAdapter{


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

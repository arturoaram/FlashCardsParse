package com.example.arturo.flashcardsparse;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Owner on 4/1/2016.
 */
public class StudyCardsActivity extends AppCompatActivity {
    List<ParseObject> fcSets;
    List<FlashCard> FCList;
    ArrayList<FlashCardsSet> arFlashCardsSet;
    int index;
    int increaseNum=0;
    Button correct,incorrect;
    Chronometer chronometer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardviewpager);

        Bundle bundle = getIntent().getExtras();
        FCList = (List<FlashCard>)bundle.getSerializable("list");
        index = bundle.getInt("index");

        FCardAdapter adapter = new FCardAdapter(getFragmentManager());
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);

        ButtonAction testListener=new ButtonAction();
        ButtonAction2 testListener2=new ButtonAction2();

        correct=(Button)findViewById(R.id.correctbtn);
        correct.setOnClickListener(testListener);
        correct.setOnFocusChangeListener(testListener);

        incorrect=(Button)findViewById(R.id.incorrectbtn);
        incorrect.setOnClickListener(testListener2);

        chronometer=(Chronometer)findViewById(R.id.chronometer);
        chronometer.start();
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
           @Override
           public void onChronometerTick(Chronometer chronometer) {
               String ct=chronometer.getText().toString();

               if(ct.equals("05:00")){
                   chronometer.stop();
                   Toast toast = Toast.makeText(getApplicationContext(),
                           "The timer has stopped! Did you get many correct?", Toast.LENGTH_LONG);
                   toast.show();
               }
           }
       });

    }

    public   class FCardAdapter extends android.support.v13.app.FragmentPagerAdapter{


        public FCardAdapter(FragmentManager fm){
            super(fm);

        }
        public Fragment getItem(int i){
            return new FCardContainerFragment(FCList,i,index);
        }

        public int getCount(){
            return FCList.size();
        }
    }

    public class ButtonAction2 implements Button.OnClickListener,Button.OnFocusChangeListener{


        @Override
        public void onClick(View v) {
            increaseNum++;
            incorrect.setText(""+Integer.toString(increaseNum));
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {

            if(hasFocus==true){


                incorrect.setText("Tap me!");
            }else{

                incorrect.setText("Out of focus!");
            }
        }
    }

    public class ButtonAction implements Button.OnClickListener,Button.OnFocusChangeListener{


        @Override
        public void onClick(View v) {
            increaseNum++;
            correct.setText(""+Integer.toString(increaseNum));
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {

            if(hasFocus==true){


                correct.setText("Tap me!");
            }else{

                correct.setText("Out of focus!");
            }
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



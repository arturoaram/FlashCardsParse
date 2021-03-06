package com.example.arturo.flashcardsparse;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Owner on 4/1/2016.
 */
public class FCardContainerFragment extends Fragment {



    private boolean cardFlipped= false;
    List<FlashCard> lFC;
    int counter,index;
    ImageView iv;


//    public FCardContainerFragment(){
//        setHasOptionsMenu(true);
//    }

    public FCardContainerFragment(List<FlashCard> list,int a,int index){
        lFC = list;
        counter=a;
        index=a;
        setHasOptionsMenu(true);
    }
    public FCardContainerFragment(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_container, container, false);

        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.container, new CardFrontFragment())
                .commit();

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_flip:
                flipCard();
                return true;
        }

        return false;
   }

    public void flipCard() {
        Fragment newFragment;
        if (cardFlipped) {
            newFragment = new CardFrontFragment();
        } else {
            newFragment = new CardBackFragment();
        }

        getChildFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in, R.animator.card_flip_left_out)
                .replace(R.id.container, newFragment)
                .commit();

        cardFlipped = !cardFlipped;
    }

    public class CardFrontFragment extends Fragment {


        public CardFrontFragment() { }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.study_cards, container, false);
            TextView tv;
            tv= (TextView)rootView.findViewById(R.id.termCard);
            tv.setText(lFC.get(counter).getTerm());
            return rootView;
        }
    }

    public class CardBackFragment extends Fragment {

        public CardBackFragment() { }
        boolean isImageFitToScreen;
        @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                                 final Bundle savedInstanceState) {
            String key = lFC.get(counter).getKEY();
            final View rootView = inflater.inflate(R.layout.study_cards_back, container, false);
            TextView tv;
            iv = (ImageView) rootView.findViewById(R.id.optionalImageView);
            iv.setVisibility(View.INVISIBLE);
            tv= (TextView)rootView.findViewById(R.id.definitionCard);
            if (lFC.get(counter).getBp() == null) {
                iv.setVisibility(View.VISIBLE);
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent innerIntent = new Intent( getActivity(),imageDisplayActivity.class);
                        innerIntent.putExtra("KEY",lFC.get(counter).getKEY());
                        Log.e("THis is your key: ",lFC.get(counter).getKEY() );
                        startActivity(innerIntent);

                    }
                });
            }
            tv.setText(lFC.get(counter).getDefinition());
            return rootView;
        }
    }
}

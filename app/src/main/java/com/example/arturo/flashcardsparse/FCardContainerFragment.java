package com.example.arturo.flashcardsparse;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Owner on 4/1/2016.
 */
public class FCardContainerFragment extends Fragment {


    private boolean cardFlipped= false;


    public FCardContainerFragment(){

        setHasOptionsMenu(true);
    }

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

    public static class CardFrontFragment extends Fragment {

        public CardFrontFragment() { }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.study_cards, container, false);
            return rootView;
        }
    }

    public static class CardBackFragment extends Fragment {

        public CardBackFragment() { }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.study_cards_back, container, false);
            return rootView;
        }
    }
}

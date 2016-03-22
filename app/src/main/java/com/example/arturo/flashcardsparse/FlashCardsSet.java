package com.example.arturo.flashcardsparse;

/**
 * Created by Arturo on 3/12/2016.
 */
public class FlashCardsSet {
    String name;
    FlashCard fc;
    FlashCard[] fcArr;

    public FlashCardsSet(String n){
        n = name;
        fc = null;
        fcArr = new FlashCard[40];
    }

    public boolean isEmpty(){
        for(int i = 0; i < 40; i++){
            if(fcArr[i] != null)
            return false;
        }
        return true;
    }
    public void addFlashCard(FlashCard flashcard){
        fc = flashcard;
    }
    public void deleteCurrentFlashCard(FlashCard currentFC){

    }
    public void delete(){

    }
    public FlashCard getFlashCard(int i){
        return fc;
    }
}

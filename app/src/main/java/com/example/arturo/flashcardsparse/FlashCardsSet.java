package com.example.arturo.flashcardsparse;

import com.parse.ParseObject;

/**
 * Created by Arturo on 3/12/2016.
 */
public class FlashCardsSet {
    String title;
    String className;
    String subject;
    String school;
    Boolean Permission;
    FlashCard fc;
    FlashCard[] fcArr;

    public FlashCardsSet(String t, String c, String s, String sc, Boolean p){
        title = t;
        className = c;
        subject = s;
        school = sc;
        Permission=p;
    }

    public FlashCardsSet(ParseObject PO){
        title = PO.getString("Title");
        className = PO.getString("Classname");
        subject = PO.getString("Subject");
        school = PO.getString("School");
        Permission = PO.getBoolean("Public");

    }

    public FlashCardsSet(String n){
        title = n;
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

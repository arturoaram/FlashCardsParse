package com.example.arturo.flashcardsparse;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.parse.ParseFile;
import com.parse.ParseObject;

import java.io.ByteArrayOutputStream;

/**
 * Created by Arturo on 3/12/2016.
 */
public class FlashCard {
    private String term;
    private String definition;
    private ImageView img;

    public FlashCard(String n, String d, ImageView i) {
        term = n;
        definition = d;
        img = i;
    }

    public FlashCard(String n, String d) {
        term = n;
        definition = d;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public String getTerm() {
        return term;
    }

    public String getDefinition() {
        return definition;
    }

    public ImageView getImg() {
        return img;
    }

    public void add(ParseObject PO) {

        if (img != null) {

            img.setDrawingCacheEnabled(true);
            img.buildDrawingCache();
            Bitmap bm = img.getDrawingCache();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] data = stream.toByteArray();

            ParseFile file = new ParseFile("hola.png", data);
            file.saveInBackground();

            ParseObject flashCard = new ParseObject("FlashCard");
            flashCard.put("term" , getTerm());
            flashCard.put("description", getDefinition());
            flashCard.put("picture",file);
            flashCard.put("Parent",PO);
            flashCard.saveInBackground();
        }

        else{
            ParseObject flashCard = new ParseObject("FlashCard");
            flashCard.put("term" , getTerm());
            flashCard.put("description", getDefinition());
            flashCard.put("Parent",PO);
            flashCard.saveInBackground();
        }

    }

}

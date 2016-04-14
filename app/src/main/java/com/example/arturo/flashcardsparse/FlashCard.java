package com.example.arturo.flashcardsparse;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.parse.ParseFile;
import com.parse.ParseObject;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

/**
 * Created by Arturo on 3/12/2016.
 */
public class FlashCard implements Serializable{
    protected String term;
    protected String definition;
    protected ImageView img;
    protected Bitmap bp;

    protected static final String TERM_PREFIX = "Term_";
    protected static final String DEFINITION_PREFIX = "Definition_";

    public FlashCard(String n, String d, ImageView i) {
        term = n;
        definition = d;
        img = i;
    }
    public FlashCard(String n, String d, Bitmap p) {
        term = n;
        definition = d;
        bp = p;
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

    public void addBitmap(ParseObject PO) {

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] data = stream.toByteArray();

            ParseFile file = new ParseFile("hola.png", data);
            file.saveInBackground();

            ParseObject flashCard = new ParseObject("FlashCard");
            flashCard.put("term" , getTerm());
            flashCard.put("description", getDefinition());
            flashCard.put("picture",file);
            flashCard.put("Parent",PO);
            flashCard.saveInBackground();
            Log.e("AQUI HAY FOTO? ","SI HAY FOTO");




    }
    public void add(ParseObject PO) {

            ParseObject flashCard = new ParseObject("FlashCard");
            flashCard.put("term" , getTerm());
            flashCard.put("description", getDefinition());
            flashCard.put("Parent",PO);
            flashCard.saveInBackground();
            Log.e("AQUI HAY FOTO? ", "NO HAY FOTO");

    }

}

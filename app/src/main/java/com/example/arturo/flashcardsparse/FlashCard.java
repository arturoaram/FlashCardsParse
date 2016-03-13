package com.example.arturo.flashcardsparse;

import android.media.Image;

/**
 * Created by Arturo on 3/12/2016.
 */
public class FlashCard {
    private String term;
    private String definition;
    private Image img;

    public FlashCard(String n, String d, Image i) {
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

    public void setImg(Image img) {
        this.img = img;
    }

    public String getTerm() {
        return term;
    }

    public String getDefinition() {
        return definition;
    }

    public Image getImg() {
        return img;
    }
}

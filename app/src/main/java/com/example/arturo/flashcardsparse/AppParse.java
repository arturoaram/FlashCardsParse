package com.example.arturo.flashcardsparse;

import android.app.Application;

import com.parse.Parse;


/**
 * Created by Arturo on 3/3/2016.
 */
public class AppParse extends Application{


    public void onCreate(){
        super.onCreate();
        Parse.initialize(this, getString(R.string.parse_app_id),getString(R.string.parse_client_id));
    }
}

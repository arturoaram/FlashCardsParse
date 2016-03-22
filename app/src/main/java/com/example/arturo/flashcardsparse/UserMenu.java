package com.example.arturo.flashcardsparse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by Arturo on 3/3/2016.
 */
public class UserMenu extends AppCompatActivity {

    String UN, PW, UsernameName;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_menu);

        setTitle("Main Menuuuu");

        UN = getIntent().getStringExtra("USERNAME");
        PW = getIntent().getStringExtra("PASSWORD");

        Log.e("Username: ", UN.toString());
        Log.e("Password: ", UN.toString());

        if (!UN.isEmpty() && !PW.isEmpty()) {

            ParseUser.logInInBackground(UN, PW, new LogInCallback() {
                public void done(ParseUser user, ParseException e) {
                    if (user != null) {

                        UsernameName = user.get("Name").toString();

                        setTitle("Welcome: " + UsernameName);
                    } else {
                        // Signup failed. Look at the ParseException to see what happened.
                    }
                }
            });
        }


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    public void createFlashCards(View view) {

        Intent i = new Intent(this, CreateFlashcards.class);
        startActivity(i);

    }
    public void ViewFlashCards(View view) {

        Intent i = new Intent(this, display.class);
        startActivity(i);

    }
}

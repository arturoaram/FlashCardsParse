package com.example.arturo.flashcardsparse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
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

        ParseUser user = ParseUser.getCurrentUser();

        UsernameName = user.get("Name").toString();

        setTitle("Welcome: " + UsernameName);
        // Signup failed. Look at the ParseException to see what happened.


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    public void createFlashCards(View view) {

        Intent i = new Intent(this, SetTitleofFC.class);
        startActivity(i);

    }

    public void ViewFlashCards(View view) {

        Intent i = new Intent(this, UsersFlashcardSets.class);
        startActivity(i);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.action_settings:
                Intent i = new Intent(this, setting_in_usermenu.class);
                startActivity(i);
                closeOptionsMenu();
                return true;


            //case R.id.quit:
            //  setContentView(R.layout.user_menu);

        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void searchPublicFC() {

        Intent i = new Intent(this, SearchPublicFlashcards.class);
        startActivity(i);

    }
}


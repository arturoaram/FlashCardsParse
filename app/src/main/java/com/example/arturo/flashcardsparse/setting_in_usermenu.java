package com.example.arturo.flashcardsparse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

/**
 * Created by Owner on 4/3/2016.
 */
public class setting_in_usermenu extends AppCompatActivity {


    Button Logout;
    Button resetButton;
    ImageButton doNotdisturb;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);


        Logout=(Button)findViewById(R.id.logOutButton);
        resetButton=(Button)findViewById(R.id.resetBtn);

        setTitle("Settings");



resetButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        ParseUser.requestPasswordResetInBackground("myemail@example.com", new RequestPasswordResetCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // An email was successfully sent with reset instructions.
                } else {
                    // Something went wrong. Look at the ParseException to see what's up.
                }
            }
        });
    }
});



}







}

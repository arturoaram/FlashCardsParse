package com.example.arturo.flashcardsparse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by Arturo on 2/29/2016.
 */
public class Credentials extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;
    Button  signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credentials);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this);

        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        signInButton = (Button) findViewById(R.id.signInButton);
    }

    public void logInButton(View view){

        ParseUser.logInInBackground(usernameEditText.getText().toString(), passwordEditText.getText().toString(), new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {

                    Log.e("Did you log in?", "YES");
                } else {
                    // Signup failed. Look at the ParseException to see what happened.
                    Toast toast = Toast.makeText(getApplicationContext(), "Failed to Log In, Try again!", Toast.LENGTH_SHORT);
                    toast.show();
                    Log.e("Did you log in?", "NEGATIVE");
                }
            }
        });
    }



}

package com.example.arturo.flashcardsparse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by Arturo on 2/29/2016.
 */
public class Credentials extends AppCompatActivity {

    EditText usernameEditText;

    EditText passwordEditText;
    //Button  signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credentials);


        usernameEditText = (EditText) findViewById(R.id.credentialsUsernameEditText);
        passwordEditText = (EditText) findViewById(R.id.credentialsPasswordEditText);
        //signInButton = (Button) findViewById(R.id.credentialsSignInButton);
    }

    public void logInButton(View view) throws ParseException {

        Boolean check = true;

        if (isEmpty(usernameEditText) ){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Username Field is empty", Toast.LENGTH_SHORT);
            toast.show();
            check = false;
        }
        else if ( isEmpty(passwordEditText) ){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Password Field is empty", Toast.LENGTH_SHORT);
            toast.show();
            check = false;
        }

        if(check){
            ParseUser.logInInBackground(usernameEditText.getText().toString(), passwordEditText.getText().toString(), new LogInCallback() {
                public void done(ParseUser user, ParseException e) {
                    if (user != null) {

                        Log.e("Did you log in?", "YES");

                    } else {
                        // Signup failed. Look at the ParseException to see what happened.
                        Toast toast = Toast.makeText(getApplicationContext(), "Failed to Log In, Try again!", Toast.LENGTH_SHORT);
                        toast.show();
                        //Log.e("Did you log in?", "NEGATIVE");

                        passwordEditText.getText().clear();
                    }
                }
            });
    }
    }

    //test
    public void registerMe(View view) {
        //Parse.
        Intent intent = new Intent(this, RegisterCredentials.class);
        startActivity(intent);
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }

    //        ParseQuery<ParseObject> query = ParseQuery.getQuery("TestObject");
//        query.whereEqualTo("foo", "Arturo");
//        query.findInBackground(new FindCallback<ParseObject>() {
//            public void done(List<ParseObject> scoreList, ParseException e) {
//                if (e == null) {
//                    for (int i = 0; i < scoreList.size(); i++)
//                        Log.e("Number that Arturo cont", "Here are the numbers of Arturo " +
//                                scoreList.get(i).get("Numbers").toString());
//                    Log.e("score", "Retrieved " + scoreList.size() + " Numbers of Arturo");
//                } else {
//                    Log.e("score", "Error: " + e.getMessage());
//                }
//            }
//        });
}
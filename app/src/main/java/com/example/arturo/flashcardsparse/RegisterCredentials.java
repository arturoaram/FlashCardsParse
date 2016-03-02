package com.example.arturo.flashcardsparse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.parse.Parse;

/**
 * Created by Owner on 3/2/2016.
 */
public class RegisterCredentials extends AppCompatActivity {

    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_cred);
        //Parse.enableLocalDatastore(this);
        //
        // Parse.initialize(this);
        cancelButton = (Button) findViewById(R.id.cancelBtn);

    }
    public void registerButton(){
        //intent possibly
        //puts information into parse
        //and should go back to login

    }
//goes back to previous activity
    public void cancelButton(View view){

    finish();
    }
}



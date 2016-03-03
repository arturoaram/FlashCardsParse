package com.example.arturo.flashcardsparse;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by Owner on 3/2/2016.
 */
public class RegisterCredentials extends Activity {

    Button registerButton;
    EditText nameText;
    EditText emailText;
    EditText usernameText;
    EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_cred);
        //Parse.initialize(this);
        //cancelButton = (Button) findViewById(R.id);
        nameText = (EditText) findViewById(R.id.registerNameEditText);
        emailText = (EditText) findViewById(R.id.registerEmailEditText);
        usernameText = (EditText) findViewById(R.id.registerUsernameEditText);
        passwordText = (EditText) findViewById(R.id.registerPasswordEditText);
        registerButton = (Button) findViewById(R.id.registerRegisterButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(nameText.getText().toString().equalsIgnoreCase("")){
                    Toast toast = Toast.makeText(getApplicationContext(), "Please input your Name!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                if(emailText.getText().toString().equalsIgnoreCase("")){
                    Toast toast = Toast.makeText(getApplicationContext(), "Please input your Email Address!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                if(usernameText.getText().toString().equalsIgnoreCase("")){
                    Toast toast = Toast.makeText(getApplicationContext(), "Please input your Username!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                if(passwordText.getText().toString().equalsIgnoreCase("")){
                    Toast toast = Toast.makeText(getApplicationContext(), "Please input your Password", Toast.LENGTH_SHORT);
                    toast.show();
                }

                //dialog
                final ProgressDialog dlg = new ProgressDialog(RegisterCredentials.this);
                dlg.setTitle("Hola");
                dlg.setMessage("Signing UP!!!");
                dlg.show();

                //Adding data to the internal database
                ParseUser pUser = new ParseUser();
                pUser.setEmail(emailText.getText().toString());
                pUser.setUsername(usernameText.getText().toString());
                pUser.setPassword(passwordText.getText().toString());
                //pUser.put("Name", nameText.getText().toString());

                //Adding data to the parse backend database
                pUser.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        dlg.dismiss();
                        if (e != null) {
                            Log.e("Registration","Failure!!!!!!!!!");
                        } else {
                            Log.e("Registration","Success");
                        }
                    }
                });

            }
        });

    }
//    public void registerButton(View view){
//
//        //Inserting into database
//
//
//        //Checking for empty fields
//
//
//        //and should go back to login
//
//
//    }
//goes back to previous activity
    public void cancelButton(View view){

    finish();
    }
}



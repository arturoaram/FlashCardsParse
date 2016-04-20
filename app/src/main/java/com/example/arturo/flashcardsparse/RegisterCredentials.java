package com.example.arturo.flashcardsparse;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by Owner on 3/2/2016.
 */
public class RegisterCredentials extends Activity {

    ImageButton registerButton;
    EditText nameText;
    EditText emailText;
    EditText usernameText;
    EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_cred);

        //LoginandRegisterTab loginandRegisterTab=(LoginandRegisterTab) this.getParent();
        //String currentTab= loginandRegisterTab.getTabHost().getCurrentTabTag();

        //Parse.enableLocalDatastore(this);
        //Parse.initialize(this);
        //cancelButton = (Button) findViewById(R.id);
        nameText = (EditText) findViewById(R.id.registerNameEditText);
        emailText = (EditText) findViewById(R.id.registerEmailEditText);
        usernameText = (EditText) findViewById(R.id.registerUsernameEditText);
        passwordText = (EditText) findViewById(R.id.registerPasswordEditText);
        registerButton = (ImageButton) findViewById(R.id.registerRegisterButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Boolean checkForToast = false;

                if (isEmpty(nameText)) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please input your Name!", Toast.LENGTH_SHORT);
                    toast.show();
                    checkForToast = true;

                } else if (emailText.getText().toString().equalsIgnoreCase("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please input your Email Address!", Toast.LENGTH_SHORT);
                    toast.show();
                    checkForToast = true;
                } else if (!emailText.getText().toString().contains("@") &&
                        !emailText.getText().toString().contains(".com")) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "This is not a valid Email Address",
                            Toast.LENGTH_SHORT);
                    toast.show();
                    checkForToast = true;
                } else if (usernameText.getText().toString().equalsIgnoreCase("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please input your Username!", Toast.LENGTH_SHORT);
                    toast.show();
                    checkForToast = true;

                } else if (passwordText.getText().toString().equalsIgnoreCase("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please input your Password", Toast.LENGTH_SHORT);
                    toast.show();
                    checkForToast = true;
                }

                //dialog
                final ProgressDialog dlg = new ProgressDialog(RegisterCredentials.this);
                dlg.setTitle("Hola");
                dlg.setMessage("Signing UP!!!");
                dlg.show();

                //Checking if Username and Email are not duplicates


//                if (checkForToast == false) {
//                    ParseQuery<ParseUser> queryUsername = ParseUser.getQuery();
//                    queryUsername.whereEqualTo("username", usernameText.getText().toString());
//                    queryUsername.findInBackground(new FindCallback<ParseUser>() {
//                        public void done(List<ParseUser> objects, ParseException e) {
//                            if (e == null) {
//                                Toast toastInner = Toast.makeText(getApplicationContext(),
//                                        "This username is already being used, Please use another one",
//                                        Toast.LENGTH_SHORT);
//                                toastInner.show();
//
//                            } else {
//                                // Something went wrong.
//                            }
//                        }
//                    });
//                }

                //Checking for the email to be a credible one


                //Adding data to the internal database

                ParseUser pUser = new ParseUser();
                pUser.setEmail(emailText.getText().toString());
                pUser.setUsername(usernameText.getText().toString());
                pUser.setPassword(passwordText.getText().toString());
                pUser.put("Name", nameText.getText().toString());

                //Adding data to the parse backend database
                pUser.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        dlg.dismiss();
                        if (e != null) {
                            Toast toastInner = Toast.makeText(getApplicationContext(),
                                    "This username or email is already being used, Please use another one",
                                    Toast.LENGTH_SHORT);
                            toastInner.show();
                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), "You have Registered Succesfully", Toast.LENGTH_SHORT);
                            toast.show();
                            //finish();

                            Toast toastLogin = Toast.makeText(getApplicationContext(), "Now Log in in the Login Tab!", Toast.LENGTH_SHORT);
                            toastLogin.show();


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
    public void cancelButton(View view) {

        finish();
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() <= 0;
    }
}



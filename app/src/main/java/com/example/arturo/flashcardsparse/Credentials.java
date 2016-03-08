package com.example.arturo.flashcardsparse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by Arturo on 2/29/2016.
 */
public class Credentials extends Activity {

    EditText usernameEditText;

    EditText passwordEditText;
    //Button  signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credentials);

        //LoginandRegisterTab loginandRegisterTab=(LoginandRegisterTab) this.getParent();
      //  String currentTab= loginandRegisterTab.getTabHost().getCurrentTabTag();


          //tabHost.setup();

        //TabHost.TabSpec tabSpec= tabHost.newTabSpec("login");
        //tabSpec.setContent(R.id.Login);
        //tabSpec.setIndicator("Login");
        //tabHost.addTab(tabSpec);


        //tabSpec= tabHost.newTabSpec("register");
        //tabSpec.setContent(R.id.Register);
        //tabSpec.setIndicator("Register");
        //tabHost.addTab(tabSpec);

        usernameEditText = (EditText) findViewById(R.id.credentialsUsernameEditText);
        passwordEditText = (EditText) findViewById(R.id.credentialsPasswordEditText);
        //regNameET=(EditText)findViewById(R.id.nameET);
        //regUsernameET=(EditText)findViewById(R.id.usernameET);
        //regPasswordET=(EditText)findViewById(R.id.passwordET);
        //regEmailET=(EditText)findViewById(R.id.emailET);

        //signInButton = (Button) findViewById(R.id.credentialsSignInButton);
    }
//private static View createTabView(Context context, String tabText){
  //  View view= LayoutInflater.from(context).inflate(R.layout.custom_tab,null,false);
    //TextView tv=(TextView)view.findViewById(R.id.tabTitleText);
    //tv.setText(tabText);
    //return view;




//}
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
                        Intent intent = new Intent(getApplicationContext(),UserMenu.class);
                        intent.putExtra("USERNAME",usernameEditText.getText().toString());
                        intent.putExtra("PASSWORD",passwordEditText.getText().toString());
                        startActivity(intent);

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
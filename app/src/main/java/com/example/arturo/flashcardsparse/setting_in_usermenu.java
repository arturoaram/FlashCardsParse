package com.example.arturo.flashcardsparse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.parse.ParseUser;

/**
 * Created by Owner on 4/3/2016.
 */
public class setting_in_usermenu extends AppCompatActivity {


    Button Logout;
    Button saveButton;
    EditText newPwd;
    EditText oldPwd;
    ImageButton doNotdisturb;
    ParseUser userP=ParseUser.getCurrentUser();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.settings);

        newPwd=(EditText)findViewById(R.id.enternewpwd);
        oldPwd=(EditText)findViewById(R.id.enteroldpwd);
        Logout=(Button)findViewById(R.id.logOutButton);
        saveButton=(Button)findViewById(R.id.savePwdbtn);
        doNotdisturb =(ImageButton)findViewById(R.id.doNotdistubBTN);
        setTitle("Settings");


//hides the status bar
        //not sure what setContentView does
doNotdisturb.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
      //  setContentView(R.layout.studying_cards);
    }
});


      saveButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if(oldPwd.getText().toString().equals(userP.get("Password").toString())){
                  userP.setPassword(newPwd.getText().toString());
                  Log.e("something jade made:"," IT WORKS!!!!");
              }
              else
                  Log.e("somethingjademade ","Never do this again!");

          }
      });

Logout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i = new Intent(getApplicationContext(),LoginandRegisterTab.class);
        startActivity(i);
        ParseUser.logOut();
    }
});
}







}

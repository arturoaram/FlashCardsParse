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
    Button saveButton;
    EditText newPwd;
    EditText oldPwd;
    ImageButton doNotdisturb;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        newPwd=(EditText)findViewById(R.id.newpwd);
        oldPwd=(EditText)findViewById(R.id.oldpwd);
        Logout=(Button)findViewById(R.id.logOutButton);
        saveButton=(Button)findViewById(R.id.savePwdbtn);
        doNotdisturb =(ImageButton)findViewById(R.id.doNotdistubBTN);
        setTitle("Settings");







}







}

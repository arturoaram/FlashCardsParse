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

/**
 * Created by Owner on 4/3/2016.
 */
public class setting_in_usermenu extends AppCompatActivity {

    EditText oldpwdET;
    EditText newpwdET;
    Button Logout;
    Button Save;
    ImageButton doNotdisturb;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        doNotdisturb=(ImageButton)findViewById(R.id.doNotdistubBTN);
        oldpwdET=(EditText)findViewById(R.id.oldpwd);
        newpwdET=(EditText)findViewById(R.id.newpwd);
        Logout=(Button)findViewById(R.id.logOutButton);
        Save=(Button)findViewById(R.id.save_btn);

        setTitle("Settings");







}







}

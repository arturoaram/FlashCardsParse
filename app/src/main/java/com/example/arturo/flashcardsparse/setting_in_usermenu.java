package com.example.arturo.flashcardsparse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.parse.ParseUser;

/**
 * Created by Owner on 4/3/2016.
 */
public class setting_in_usermenu extends AppCompatActivity {


    Button Logout;
    Button resetButton;
    ImageButton doNotdisturb;
    ParseUser user = ParseUser.getCurrentUser();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);


        Logout=(Button)findViewById(R.id.logOutButton);
        resetButton=(Button)findViewById(R.id.resetBtn);

        setTitle("Settings");



resetButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});



}







}

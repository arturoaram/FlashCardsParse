package com.example.arturo.flashcardsparse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

/**
 * Created by Owner on 3/22/2016.
 */
public class SetTitleofFC extends Activity implements AdapterView.OnItemSelectedListener {

EditText title,classnum,subject;
    Button continuebtn,cancelbtn;
    Spinner schools;
    Switch statusCards;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_title_of_fc);


         title=(EditText)findViewById(R.id.title);
         classnum=(EditText)findViewById(R.id.classED);
         subject=(EditText)findViewById(R.id.subjectED);
         continuebtn=(Button) findViewById(R.id.continueBtn);
         cancelbtn=(Button)findViewById(R.id.cancelBtn);
         schools=(Spinner)findViewById(R.id.spinner);
         statusCards=(Switch)findViewById(R.id.switch1);

        //adapter for spinner
        //items in spinner already set in strings.xml
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
               R.array.school_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        schools.setAdapter(adapter);

        //switch for public and private
        statusCards.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                }else{

                }
            }
        });
    }

//goes back to home screen
    public void canceltoHome(){

        finish();
    }
    //goes on ahead and creates new set of cards.
    public void continueToCF(){
        Intent i=new Intent(this,CreateFlashcards.class);
        startActivity(i);
    }

    //when a school is selected
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }
//when a school is not selected
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
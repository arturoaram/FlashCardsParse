package com.example.arturo.flashcardsparse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by Owner on 3/22/2016.
 */
public class SetTitleofFC extends Activity implements AdapterView.OnItemSelectedListener {

    EditText title, classnum, subject;
    Button continuebtn, cancelbtn;
    Spinner schools;
    Switch statusCards;
    String objectID;
    final ParseUser currentUser = ParseUser.getCurrentUser();
    ParseObject flashCardSet = new ParseObject("SetFC");

    String x = "...";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_title_of_fc);



        title = (EditText) findViewById(R.id.title);
        classnum = (EditText) findViewById(R.id.classED);
        subject = (EditText) findViewById(R.id.subjectED);
        continuebtn = (Button) findViewById(R.id.continueBtn);
        cancelbtn = (Button) findViewById(R.id.cancelBtn);
        schools = (Spinner) findViewById(R.id.spinner);
        statusCards = (Switch) findViewById(R.id.switch1);

        //adapter for spinner
        //items in spinner already set in strings.xml
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.school_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        schools.setAdapter(adapter);

        //switch for public and private
        statusCards.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                } else {

                }
            }
        });

        if(currentUser !=null){
            Log.e("User: ", "Success");

        }
        else{
            Log.e("User: ", "FAIL");
        }
    }

    //goes back to home screen
    public void cancelToHome() {

        Intent i= new Intent(this, UserMenu.class);
     startActivity(i);
    }

    //goes on ahead and creates new set of cards.
    public void continueToCF(View view) {
        //ParseObject flashCardSet = new ParseObject("SetFC");

        flashCardSet.put("Title", title.getText().toString());
        flashCardSet.put("Classname", classnum.getText().toString());
        flashCardSet.put("Subject", subject.getText().toString());
        flashCardSet.put("School", schools.getSelectedItem().toString());
        flashCardSet.put("Public", statusCards.isChecked());
        flashCardSet.put("Parent", currentUser);

        flashCardSet.saveInBackground();

        flashCardSet.fetchInBackground(new GetCallback<ParseObject>() {


            @Override
            public void done(ParseObject parseObject, ParseException e) {
                if (e == null) {
                    // Saved successfully.
                    Log.d("MY STUPID ASS OBJECT ID", "User update saved!");
                    final String s = parseObject.getObjectId().toString();
                    Log.d("MY STUPID ASS OBJECT ID", "The object id is: " + s);
                    //x = s;
                    Intent i = new Intent(getApplicationContext(), CreateFlashcards.class);
                    //Log.e("HOLAAAAAAAAAAAA: ", "WOW QUE MALDITA MOVIE"+x + "Hola");
                    i.putExtra("ID", s);
                    startActivity(i);
                } else {
                    // The save failed.
                    Log.d("MY STUPID ASS OBJECT ID", "User update error: " + e);
                }

            }
        });

    }

    public boolean isEmpty(EditText string){
        if(string.getText().toString() == "") return true;

        else return false;
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
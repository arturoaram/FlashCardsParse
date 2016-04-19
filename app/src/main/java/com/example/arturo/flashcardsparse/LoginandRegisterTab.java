package com.example.arturo.flashcardsparse;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * Created by Owner on 3/7/2016.
 */
public class LoginandRegisterTab extends TabActivity{


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_and_registertab);


        TabHost tabHost = getTabHost();

        TabHost.TabSpec spec;
    Intent intent;
    intent= new Intent().setClass(this,Credentials.class);
    View tabView =createTabView(this,"Login");
    spec= tabHost.newTabSpec("tab1").setIndicator(tabView).setContent(intent);
    tabHost.addTab(spec);
    tabView=createTabView(this, "Register");
    intent=new Intent().setClass(this,RegisterCredentials.class);
    spec=tabHost.newTabSpec("tab2").setIndicator(tabView).setContent(intent);
    tabHost.addTab(spec);


//push this


}
    private static View createTabView(Context context, String tabText){
        View view= LayoutInflater.from(context).inflate(R.layout.custom_tab,null,false);
        TextView tv=(TextView)view.findViewById(R.id.tabTitleText);
        tv.setText(tabText);
        return view;




    }

    public void onClick(View view){

        Intent i =new Intent(this,SearchPublicFlashcards.class);
        startActivity(i);
    }


}
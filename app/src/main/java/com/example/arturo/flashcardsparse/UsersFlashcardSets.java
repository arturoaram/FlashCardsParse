package com.example.arturo.flashcardsparse;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

/**
 * Created by Owner on 3/28/2016.
 */
public class UsersFlashcardSets extends Activity {
ListView listview;
    List<ParseObject> ob;
    ProgressDialog mProgressDialog;
   ListViewAdapter adapter;
    private List<SetsFC> setsList=null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users_fcsets);


        new RemoteDataTask().execute();


    }  // RemoteDataTask AsyncTask
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(UsersFlashcardSets.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Parse.com Simple ListView Tutorial");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Locate the class table named "SetFC" in Parse.com
            setsList=new ArrayList<SetsFC>();
            try{
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
                    "SetFC");
            query.orderByAscending("_created_at");
                ob=query.find();
                for (ParseObject SetFC : ob) {
                    SetsFC map=new SetsFC();
                    map.setTitle((String)SetFC.get("Title"));
                   // map.setClassname((String)SetFC.get("Classname"));
                   // map.setSubject((String)SetFC.get("Subject"));
                  //  map.setSchool((String)SetFC.get("School"));
                    setsList.add(map);}

              //  ob = query.find();
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Locate the listview in listview_main.xml
            listview = (ListView) findViewById(R.id.listView);
            // Pass the results into an ArrayAdapter
            adapter = new ListViewAdapter(UsersFlashcardSets.this,
                    setsList);
            // Retrieve object "name" from Parse.com database

            //    adapter.add((String) SetFC.get("Title"));
              //  adapter.add((String) SetFC.get("Classname"));
               // adapter.add((String) SetFC.get("School"));
               //adapter.add((String) SetFC.get("Subject"));
          //  }
            // Binds the Adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();

            // Capture button clicks on ListView items
//          listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//               @Override
//              public void onItemClick(AdapterView<?> parent, View view,
//                                       int position, long id) {
//                   // Send single item click data to SingleItemView Class
//                    Intent i = new Intent(UsersFlashcardSets.this,
//                          SingleSetView.class);
//                  // Pass data "name" followed by the position
//                   i.putExtra("Title", ob.get(position).getString("Title"));                    i.putExtra("Classname", ob.get(position).getString("Classname"));
//                   i.putExtra("School",ob.get(position).getString("School"));
//                    i.putExtra("Subject",ob.get(position).getString("Subject"));
////                    // Open SingleItemView.java Activity
//                    startActivity(i);
            //    }
          //  });
        }
    }
}


















package com.example.android.data;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.data.model.DataItem;
import com.example.android.data.sample.SampleDataProvider;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final int SIGNIN_REQUEST = 1001;
    public static final String MY_GLOBAL_PREFERENCES = "my_global_prefer";
    // import list data items to main activity
    List<DataItem> dataItemList = SampleDataProvider.dataItemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //sort data
        Collections.sort(dataItemList, new Comparator<DataItem>() {
            @Override
            public int compare(DataItem o1, DataItem o2) {
                return o1.getItemName().compareTo(o2.getItemName());
            }
        });

        /*for grid view if user select grid view it saved ion shared pref and using shared prefs
        * we give the view to the user */
        // access the shared prefs
        SharedPreferences  settings = PreferenceManager.getDefaultSharedPreferences(this);
        //get boolean (the user selected shared prefs) if the data is didn't available it returns the false
        boolean grid = settings.getBoolean(getString(R.string.pref_display_grid), false);

        //instantiate the data adapter for recycler view
        DataItemAdapter adapter = new DataItemAdapter(this, dataItemList);
        //find the recycler view id
        RecyclerView recyclerList = (RecyclerView) findViewById(R.id.recycle_id);

        //if user give selected the grid view this code will executes
        if (grid){
            //override the default recycler view and give the grid view to the user
            //this give you not good looking output you have to customize the view
            recyclerList.setLayoutManager(new GridLayoutManager(this, 3));
        }
        //bind the recycler to the data adapter
        recyclerList.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_signin:
                Intent sighIntent = new Intent(this, SigninActivity.class);
                startActivityForResult(sighIntent, SIGNIN_REQUEST);
                return true;

            case R.id.settings:
                Intent settingIntent = new Intent(this, PrefsActivity.class);
                startActivity(settingIntent);
                return true;

            case R.id.import_to_json:
                Intent importIntent = new Intent(this, ImportJsonActivity.class );

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == SIGNIN_REQUEST){
            String email = data.getStringExtra(SigninActivity.EMAIL_KEY);
            Toast.makeText(this, "Your Login with " +email, Toast.LENGTH_SHORT).show();

            SharedPreferences.Editor editor =
                    getSharedPreferences(MY_GLOBAL_PREFERENCES, MODE_PRIVATE).edit();
            editor.putString(SigninActivity.EMAIL_KEY, email);
            editor.apply();
        }
    }



}
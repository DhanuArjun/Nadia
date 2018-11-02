package com.example.android.data;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import com.example.android.data.model.DataItem;
import com.example.android.data.sample.SampleDataProvider;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MainActivity extends AppCompatActivity {


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


        DataItemAdapter adapter = new DataItemAdapter(this, dataItemList);

        RecyclerView recyclerList = (RecyclerView) findViewById(R.id.recycle_id);

        recyclerList.setAdapter(adapter);

    }
}
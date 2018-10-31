package com.example.android.data;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;
import com.example.android.data.model.DataItem;
import com.example.android.data.sample.SampleDataProvider;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.example.android.data.sample.SampleDataProvider.dataItemList;

public class MainActivity extends AppCompatActivity {

    @SuppressWarnings("FieldCanBeLocal")
    private TextView tvOut;


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


        /*for (DataItem item : dataItemList) {
            tvOut.append(item.getItemName() + "\n");
            itemNames.add(item.getCategory());*/

        DataItemAdapter adapter = new DataItemAdapter(this, dataItemList);

        ListView list = (ListView) findViewById(android.R.id.list);

        list.setAdapter(adapter);


    }
}

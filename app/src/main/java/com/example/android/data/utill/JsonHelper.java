package com.example.android.data.utill;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.example.android.data.model.DataItem;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static android.content.ContentValues.TAG;

public class JsonHelper {


    public static final String FILE_NAME = "json_file.json";

    /*before json import we have to import the data and then convert that data to json*/
    public static boolean exportJson(Context context, List<DataItem> dataItemList) {



        //call the objects
        DataItems menuItems = new DataItems();
        menuItems.setDataItems(dataItemList);

        Gson file = new Gson();
        String json_string = file.toJson(dataItemList);

        Log.i(TAG, "export to json " + json_string );

        FileOutputStream fileOutputStream = null;
        File json_file = new File(Environment.getExternalStorageDirectory(), FILE_NAME);

        try {
            fileOutputStream = new FileOutputStream(json_file);
            fileOutputStream.write(json_string.getBytes());
            Toast.makeText(context, "Your file " + FILE_NAME, Toast.LENGTH_SHORT).show();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }


    /*convert the references to object by set and get method  */

    public static List<DataItem> importFromJson(Context context) {
        return null;
    }

    public static class DataItems {
        List<DataItem> dataItems;

        public List<DataItem> getDataItems() {
            return dataItems;
        }

        private void setDataItems(List<DataItem> dataItems) {
            this.dataItems = dataItems;
        }

    }


}

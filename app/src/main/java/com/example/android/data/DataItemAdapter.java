package com.example.android.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.data.model.DataItem;

import java.util.List;
import java.util.zip.Inflater;

public class DataItemAdapter extends ArrayAdapter {

    private List<DataItem> mDataItems;
    // create the layoutInflater to read the xml layout file
    LayoutInflater mInflater;

    public DataItemAdapter(@NonNull Context context,  @NonNull List objects) {
        super(context, R.layout.list_item, objects);

        mDataItems = objects;
        mInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            convertView = mInflater.inflate(R.layout.list_item, parent, false);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.item_name);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.image_view);

        DataItem item = mDataItems.get(position);
        textView.setText(item.getItemName());
        imageView.setImageResource(R.drawable.apple_pie);


        return convertView;
    }

}

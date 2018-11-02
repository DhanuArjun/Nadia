package com.example.android.data;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.data.model.DataItem;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class DataItemAdapterListView extends ArrayAdapter {

    private List<DataItem> mDataItems;
    // create the layoutInflater to read the xml layout file
    LayoutInflater mInflater;

    public DataItemAdapterListView(@NonNull Context context, @NonNull List objects) {
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

        String imageFile = item.getImage();

        InputStream inputStream = null;
        try {
            inputStream = getContext().getAssets().open(imageFile);
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            imageView.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            try {
                if(inputStream != null){
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return convertView;
    }

}

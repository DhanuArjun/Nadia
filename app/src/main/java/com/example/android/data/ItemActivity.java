package com.example.android.data;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.android.data.model.DataItem;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.Locale;

public class ItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);


        /*Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
*/
        // for single item
        /*String itemId = getIntent().getExtras().getString(DataItemAdapter.ITEM_ID_KEY);
        DataItem item = SampleDataProvider.dataItemMap.get(itemId);*/

        //to Show the toast message

        /*DataItem itemId = getIntent().getExtras().getParcelable(DataItemAdapter.ITEM_ID);
        if (itemId != null) {
            Toast.makeText(this, "Item received ", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Item not received ", Toast.LENGTH_SHORT).show();
        }*/

        // take the if from intent

        DataItem item = getIntent().getExtras().getParcelable(DataItemAdapter.ITEM_ID);
        if (item == null) {
            throw new AssertionError("Data is not available ");

        }


        //find the views to display the item details `
        TextView itemName = (TextView) findViewById(R.id.tvItemNameNew);
        TextView itemDescription = (TextView) findViewById(R.id.tvDescriptionNew);
        TextView itemPrice = (TextView) findViewById(R.id.tvPriceNew);
        ImageView itemImage = (ImageView) findViewById(R.id.itemImageNew);

        //display the data to the screen by calling the text and set data to the display
        itemName.setText(item.getItemName());
        itemDescription.setText(item.getDescription());

        //for testing purpose
        /*String price = "" + item.getPrice();
        priceView.setText(price);*/

        NumberFormat nf = NumberFormat.getNumberInstance(Locale.getDefault());
        itemPrice.setText(nf.format(item.getPrice()));

        /*to display the photos we have to read the item name string from data base
        * and display the same name images from asserts folder we use inputStream
        * for read from the assets folder
        * then use try catch if to prevent crashing if image is not available */

        InputStream inputStream = null;

        try {
            //get image name from dataItem
            String imageFile  = item.getImage();
            //compare the image string name with assets folder
            inputStream = getAssets().open(imageFile);
            //take same name images from drawable folder
            Drawable d = Drawable.createFromStream(inputStream, null);
            //put in image view
            itemImage.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                //close the input stream if it is null
                if(inputStream != null){
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
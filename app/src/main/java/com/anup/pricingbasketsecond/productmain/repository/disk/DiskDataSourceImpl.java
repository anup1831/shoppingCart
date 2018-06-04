package com.anup.pricingbasketsecond.productmain.repository.disk;

import android.util.Log;

import com.anup.pricingbasketsecond.R;
import com.anup.pricingbasketsecond.models.ItemGridViewObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anup on 5/30/2018.
 */

public class DiskDataSourceImpl/* implements DiskDataSource*/{
    public static final String TAG = DiskDataSourceImpl.class.getSimpleName();
    List<ItemGridViewObject> allItems;

    public DiskDataSourceImpl() {
        super();
//        getProductMain();
//        Log.i(TAG, "Anup - DDSI Constructor -"+allItems.size());
    }


    public List<ItemGridViewObject> getProductMain() {
        List<ItemGridViewObject> allItems = new ArrayList<>();
        allItems.add(new ItemGridViewObject(R.drawable.beans, "Beans", .73 ));
        allItems.add(new ItemGridViewObject(R.drawable.greenpeas, "Peas", .95));
        allItems.add(new ItemGridViewObject(R.drawable.eggs, "Eggs", 2.10));
        allItems.add(new ItemGridViewObject(R.drawable.milk, "Milk", 1.30));
        Log.i(TAG, "Anup - DDSI AllItemList -"+allItems.size());
        return allItems;
    }

    /*public String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream is = Context.getAssets().open("dishes.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        Log.e(, "Json response " + json);
        return json;

    }*/
}

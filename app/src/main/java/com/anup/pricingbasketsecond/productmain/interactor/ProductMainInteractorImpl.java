package com.anup.pricingbasketsecond.productmain.interactor;

import android.util.Log;

import com.anup.pricingbasketsecond.R;
import com.anup.pricingbasketsecond.models.ItemGridViewObject;
import com.anup.pricingbasketsecond.productmain.repository.disk.DiskDataSource;
import com.anup.pricingbasketsecond.productmain.repository.disk.DiskDataSourceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

/**
 * Created by Anup on 5/30/2018.
 */

public class ProductMainInteractorImpl implements ProductMainInteractor {
    public static final String TAG = ProductMainInteractorImpl.class.getSimpleName();

    @Override
    public void getProductItem(final OnFinishedListener listener) {
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onFinished(getProductMain());
            }
        }, 2000);

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
//    private DiskDataSourceImpl repository;
//
//    public ProductMainInteractorImpl(/*DiskDataSource repository*/) {
//        Log.i(TAG, "Anup - initialize InteractorImpl calling DDSI");
//        repository = new DiskDataSourceImpl();
//    }
//
//    @Override
//    public List<ItemGridViewObject> getProductGridList() {
//        return repository.getProductMain();
//    }
}

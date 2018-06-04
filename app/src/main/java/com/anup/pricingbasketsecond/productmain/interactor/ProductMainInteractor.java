package com.anup.pricingbasketsecond.productmain.interactor;

import android.database.Observable;

import com.anup.pricingbasketsecond.models.ItemGridViewObject;

import java.util.List;

/**
 * Created by Anup on 5/29/2018.
 */

public interface ProductMainInteractor {
    //List<ItemGridViewObject> getProductGridList();
    interface OnFinishedListener{
        void onFinished(List<ItemGridViewObject> productList);
    }
    void getProductItem(OnFinishedListener listener);
}

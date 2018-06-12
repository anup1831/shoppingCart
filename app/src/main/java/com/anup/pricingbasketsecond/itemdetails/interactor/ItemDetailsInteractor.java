package com.anup.pricingbasketsecond.itemdetails.interactor;

import android.content.Context;

import com.anup.pricingbasketsecond.models.ItemGridViewObject;

/**
 * Created by Anup on 6/5/2018.
 */

public interface ItemDetailsInteractor {
    interface OnAddToCartListener{
        void onQtyError();
        void onSuccess();
    }

    void onAddToCart(Context context, ItemGridViewObject object, String qty, OnAddToCartListener listener);
}

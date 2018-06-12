package com.anup.pricingbasketsecond.itemdetails.presenter;

import android.content.Context;

import com.anup.pricingbasketsecond.models.ItemGridViewObject;

/**
 * Created by Anup on 6/5/2018.
 */

public interface ItemDetailsPresenter {
    void validateQty(Context context, ItemGridViewObject object, String qty);
    void onDestroy();
}

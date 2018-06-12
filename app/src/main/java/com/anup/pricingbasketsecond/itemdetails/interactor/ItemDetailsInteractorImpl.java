package com.anup.pricingbasketsecond.itemdetails.interactor;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.anup.pricingbasketsecond.dbutil.LocalDbHelper;
import com.anup.pricingbasketsecond.models.CartItemsModel;
import com.anup.pricingbasketsecond.models.ItemGridViewObject;

/**
 * Created by Anup on 6/5/2018.
 */

public class ItemDetailsInteractorImpl implements ItemDetailsInteractor {
    //logic of adding items into the cart goes here
    LocalDbHelper dbHelper;

    @Override
    public void onAddToCart(final Context context, final ItemGridViewObject object, final String qty, final OnAddToCartListener listener) {
        //new Handler().postDelayed(new Runnable() {
         //   @Override
         //   public void run() {
                if(TextUtils.isEmpty(qty)){
                    listener.onQtyError();
                    return;
                } else {
                    insertCartItemData(context, object, qty);
                }
                listener.onSuccess();
        //    }
       // }, 1000);
    }


    private void insertCartItemData(Context context, ItemGridViewObject object, String qty) {
        //Activity activity = (Activity)
        dbHelper = new LocalDbHelper(context);
        CartItemsModel cartItemsModel = new CartItemsModel();
        Log.i("Anup", "IDV "+object.getImageView());
        cartItemsModel.setItemImage(object.getImageView());
        cartItemsModel.setItemName(object.getName());
        cartItemsModel.setItemPrice(String.valueOf(object.getPrice()));
        cartItemsModel.setQty(qty);
        dbHelper.insertDataIntoDB(cartItemsModel);
    }


}

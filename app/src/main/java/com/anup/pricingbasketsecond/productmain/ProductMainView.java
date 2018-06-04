package com.anup.pricingbasketsecond.productmain;

import com.anup.pricingbasketsecond.models.ItemGridViewObject;

import java.util.List;

/**
 * Created by Anup on 5/29/2018.
 */

public interface ProductMainView {

    //Show product
    void setProductGridList(List<ItemGridViewObject> gridViewObjectList);

    //handle gridview click
//    void onItemDetailsViewStartListener(ItemGridViewObject itemGridViewObject);

    //Use to show empty screen message
    void showEmptyView();
    //Use to show validation error message
//    void showError();
    //Use to show network error
//    void showNetworkError();
    boolean isNetworkAvailable();
    //Show LoadingScreen
    void showFullScreenLoading();
    //Hide LoadingScreen
    void hideFullScreenLoading();

   // void showMessage(String message);

    void setItemObject(ItemGridViewObject itemAtposition);
}

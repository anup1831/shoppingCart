package com.anup.pricingbasketsecond.itemdetails.presenter;

import android.content.Context;

import com.anup.pricingbasketsecond.itemdetails.ItemDetailsView;
import com.anup.pricingbasketsecond.itemdetails.interactor.ItemDetailsInteractor;
import com.anup.pricingbasketsecond.models.ItemGridViewObject;

/**
 * Created by Anup on 6/5/2018.
 */

public class ItemDetailsPresenterImpl implements ItemDetailsPresenter, ItemDetailsInteractor.OnAddToCartListener {
    private ItemDetailsView itemDetailsView;
    private ItemDetailsInteractor itemDetailsInteractor;

    public ItemDetailsPresenterImpl(ItemDetailsView itemDetailsView, ItemDetailsInteractor itemDetailsInteractor) {
        this.itemDetailsView = itemDetailsView;
        this.itemDetailsInteractor = itemDetailsInteractor;
    }

    @Override
    public void validateQty(Context context, ItemGridViewObject object, String qty) {

        if(itemDetailsView != null){
            itemDetailsView.showFullScreenLoading();
        }
        itemDetailsInteractor.onAddToCart(context, object, qty, this);
    }

    @Override
    public void onDestroy() {
        itemDetailsView = null;
    }

    @Override
    public void onQtyError() {
        if(itemDetailsView != null){
            itemDetailsView.showQtyEtError();
            itemDetailsView.hideFullscreenLoading();
        }
    }

    @Override
    public void onSuccess() {

        if (itemDetailsView != null){
            itemDetailsView.navigateToCheckoutScreen();
        }
    }
}

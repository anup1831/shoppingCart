package com.anup.pricingbasketsecond.productmain.presenter;

import com.anup.pricingbasketsecond.models.ItemGridViewObject;
import com.anup.pricingbasketsecond.productmain.ProductMainView;
import com.anup.pricingbasketsecond.productmain.interactor.ProductMainInteractor;

import java.util.List;

/**
 * Created by Anup on 5/29/2018.
 */

public class ProductMainPresenterImpl implements ProductMainPresenter, ProductMainInteractor.OnFinishedListener {
    public static final String TAG = ProductMainPresenterImpl.class.getSimpleName();
    private ProductMainView mainView;
    private ProductMainInteractor interactor;

    public ProductMainPresenterImpl(ProductMainView mainView, ProductMainInteractor interactor) {
        this.mainView = mainView;
        this.interactor = interactor;
    }

    @Override
    public void onFinished(List<ItemGridViewObject> productList) {
        if (mainView != null){
            mainView.setProductGridList(productList);
            mainView.hideFullScreenLoading();
        }
    }

    @Override
    public void onGridViewClick(ItemGridViewObject itemAtposition) {
        if (mainView != null) {
            mainView.setItemObject(itemAtposition);
            //mainView.showMessage(String.format("Position %d clicked", "Clicked"));
        }
    }

    @Override
    public void onResume() {
        if (mainView != null) {
            mainView.showFullScreenLoading();
        }
        interactor.getProductItem(this);
    }

    @Override
    public void onDestroy() {
        mainView = null;
    }
}

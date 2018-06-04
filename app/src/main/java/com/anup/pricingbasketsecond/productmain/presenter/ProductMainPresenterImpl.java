package com.anup.pricingbasketsecond.productmain.presenter;

import android.util.Log;

import com.anup.pricingbasketsecond.models.ItemGridViewObject;
import com.anup.pricingbasketsecond.productmain.ProductMainView;
import com.anup.pricingbasketsecond.productmain.interactor.ProductMainInteractor;
import com.anup.pricingbasketsecond.productmain.interactor.ProductMainInteractorImpl;
import com.anup.pricingbasketsecond.productmain.repository.disk.DiskDataSource;

import java.util.List;

/**
 * Created by Anup on 5/29/2018.
 */

public class ProductMainPresenterImpl implements ProductMainPresenter, ProductMainInteractor.OnFinishedListener {
    public static final String TAG = ProductMainPresenterImpl.class.getSimpleName();
    private ProductMainInteractor productMainInteractor;
    private ProductMainView productMainView;

    public ProductMainPresenterImpl(ProductMainInteractor productMainInteractor, ProductMainView productMainView) {
        this.productMainInteractor = productMainInteractor;
        this.productMainView = productMainView;
    }

    /*public ProductMainPresenterImpl(ProductMainView productView) {
        this.productMainView = productView;
        this.productMainInteractor = productMainInteractor;
    }*/

    @Override
    public void onGridViewClick(int position) {
        if (productMainView != null) {
            productMainView.showMessage(String.format("Position %d clicked", position + 1));
        }
    }

    @Override
    public void onResume() {
        if (productMainView != null) {
            productMainView.showFullScreenLoading();
        }
    }

    @Override
    public void onDestroy() {
        productMainView = null;
    }

    @Override
    public void onFinished(List<ItemGridViewObject> productList) {
        if (productMainView != null) {
            productMainView.onProductGridListReceived(productList);
            productMainView.hideFullScreenLoading();
        }
    }
    //Testing view purpose
    public ProductMainView getMainView() {
        return productMainView;
    }

   /* @Override
    public ItemGridViewObject getGridViewObject() {
        return null;
    }*/

    /*@Override
    public void onAttach(ProductMainView mainView) {
        this.productMainView = mainView;
    }*/


}

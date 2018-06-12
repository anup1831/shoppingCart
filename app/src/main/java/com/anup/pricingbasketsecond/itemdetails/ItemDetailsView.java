package com.anup.pricingbasketsecond.itemdetails;

import android.content.Context;

/**
 * Created by Anup on 6/4/2018.
 */

public interface ItemDetailsView {
    void showFullScreenLoading();
    void hideFullscreenLoading();
    void showQtyEtError();
    void navigateToCheckoutScreen();
    Context getContext();

}

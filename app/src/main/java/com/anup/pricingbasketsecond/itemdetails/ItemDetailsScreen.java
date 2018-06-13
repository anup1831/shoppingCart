package com.anup.pricingbasketsecond.itemdetails;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anup.pricingbasketsecond.CheckoutActivity;
import com.anup.pricingbasketsecond.R;
import com.anup.pricingbasketsecond.dbutil.LocalDbHelper;
import com.anup.pricingbasketsecond.itemdetails.interactor.ItemDetailsInteractorImpl;
import com.anup.pricingbasketsecond.itemdetails.presenter.ItemDetailsPresenter;
import com.anup.pricingbasketsecond.itemdetails.presenter.ItemDetailsPresenterImpl;
import com.anup.pricingbasketsecond.models.CartItemsModel;
import com.anup.pricingbasketsecond.models.ItemGridViewObject;
import com.anup.pricingbasketsecond.utils.FullScreenLoading;

/**
 * Created by Anup on 5/21/2018.
 */

public class ItemDetailsScreen extends Activity implements ItemDetailsView, View.OnClickListener {
    TextView tv_nameValue, tv_priceValue;
    EditText et_qty;
    Button btnAddToCart, btnCheckout;
    ImageView imageView;
    ItemGridViewObject object;
    String enteredQty;
    //private MySharedPreference sharedPreference;
    LocalDbHelper dbHelper;
    private FullScreenLoading progressDialog;
    private ItemDetailsPresenter presenter;
    private ItemDetailsView itemDetailsView;

    public String getEnteredQty() {
        return enteredQty;
    }

    public void setEnteredQty(String enteredQty) {
        this.enteredQty = enteredQty;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        Intent intent = getIntent();
        object = (ItemGridViewObject) intent.getParcelableExtra("INTENT_OBJECT");
        initPresenter();
        //sharedPreference = new MySharedPreference(ItemDetailsScreen.this);
        dbHelper = new LocalDbHelper(ItemDetailsScreen.this);
        initView();

        //controller = (Controller) getApplicationContext();
    }

    private void initPresenter() {
        presenter = new ItemDetailsPresenterImpl(this, new ItemDetailsInteractorImpl());
    }

    private void initView() {
        progressDialog = new FullScreenLoading(this);
        imageView = (ImageView)findViewById(R.id.iv_display);
        tv_nameValue = (TextView) findViewById(R.id.tv_item_tag_value);
        tv_priceValue = (TextView) findViewById(R.id.tv_item_price_tag_value);
        btnAddToCart = (Button) findViewById(R.id.add_to_cart);
        btnCheckout = (Button) findViewById(R.id.checkout);
        btnCheckout.setOnClickListener(this);
        btnAddToCart.setOnClickListener(this);
        imageView.setImageResource(object.getImageView());
        tv_nameValue.setText(object.getName());
        tv_priceValue.setText(""+object.getPrice());

        et_qty = (EditText) findViewById(R.id.et_qty_value);
        et_qty.requestFocus();
        et_qty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               // setEnteredQty(Integer.parseInt(s.toString()));
                Log.i("Anup", "count -"+count);
                if(count == 0){
                    btnCheckout.setText("CHECKOUT");
                }else{}
                s="";
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.add_to_cart){
            if(!(et_qty.getText().toString().equals(null)) || !(et_qty.getText().toString() == null)){
                setEnteredQty(et_qty.getText().toString());
                presenter.validateQty(getContext(), object, getEnteredQty());

            } else{
                Toast.makeText(getApplicationContext(), "Enter Quantity!", Toast.LENGTH_SHORT).show();
            }

        } else if (v.getId() == R.id.checkout){
            /*Intent intent = new Intent(ItemDetailsScreen.this, CheckoutActivity.class);
            intent.putExtra("QTY", getEnteredQty());
            intent.putExtra("INTENT_OBJECT", object);
            startActivity(intent);*/
        }
    }

    /*private void insertCartItemData(String qty) {

        CartItemsModel cartItemsModel = new CartItemsModel();
        Log.i("Anup", "IDV "+object.getImageView());
        cartItemsModel.setItemImage(object.getImageView());
        cartItemsModel.setItemName(object.getName());
        cartItemsModel.setItemPrice(String.valueOf(object.getPrice()));
        cartItemsModel.setQty(qty);
        dbHelper.insertDataIntoDB(cartItemsModel);
    }*/

    @Override
    public void showFullScreenLoading() {
        if(progressDialog != null && !progressDialog.isShowing()){
            progressDialog.show();
        }
    }

    @Override
    public void hideFullscreenLoading() {
        if(progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    @Override
    public void showQtyEtError() {
        et_qty.setError("Quantity can not be empty!");
    }

    @Override
    public void navigateToCheckoutScreen() {
        Intent intent = new Intent(getContext(), CheckoutActivity.class);
        Log.i("Anup", "CheckoutActivity -putting to intent "+getEnteredQty());
        intent.putExtra("QTY", getEnteredQty());
        intent.putExtra("INTENT_OBJECT", object);
        startActivity(intent);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    /*@Override
    protected void onResume() {
        super.onResume();
        if(progressDialog.isShowing())
            progressDialog.dismiss();
    }
*/
    @Override
    protected void onRestart() {
        super.onRestart();
        if(progressDialog.isShowing())
            progressDialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }
}

package com.anup.pricingbasketsecond;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Anup on 5/21/2018.
 */

public class ItemDetailsView extends Activity implements View.OnClickListener {
    TextView tv_nameValue, tv_priceValue;
    EditText et_qty;
    Button btnAddToCart, btnCheckout;
    ImageView imageView;
    ItemGridViewObject object;
    String enteredQty;
    //private MySharedPreference sharedPreference;
    LocalDbHelper dbHelper;

    //private Controller controller=null;

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
        Log.i("Anup", "ItemSetailsView Content - "+object.getName() +" - "+object.getImageView() + " - "+object.getPrice());
        //sharedPreference = new MySharedPreference(ItemDetailsView.this);
        dbHelper = new LocalDbHelper(ItemDetailsView.this);
        initView();

        //controller = (Controller) getApplicationContext();
    }

    private void initView() {
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
                //ItemGridViewObject itemGridViewObject = null;
                //itemGridViewObject = new ItemGridViewObject(object.getImageView(), object.getName(), object.getPrice());
                //controller.setProducts(itemGridViewObject);
                //int size = controller.getProductArrayListSize();
                btnCheckout.setText("CHECKOUT - "+et_qty.getText().toString());
                setEnteredQty(et_qty.getText().toString());
                insertCartItemData(et_qty.getText().toString());

                Toast.makeText(getApplicationContext(), ""+getEnteredQty(), Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(getApplicationContext(), "Enter Quantity!", Toast.LENGTH_SHORT).show();
            }

        } else if (v.getId() == R.id.checkout){
            Intent intent = new Intent(ItemDetailsView.this, CheckoutActivity.class);
            intent.putExtra("QTY", getEnteredQty());
            intent.putExtra("INTENT_OBJECT", object);
            startActivity(intent);
        }
    }

    private void insertCartItemData(String qty) {

        CartItemsModel cartItemsModel = new CartItemsModel();
        Log.i("Anup", "IDV "+object.getImageView());
        cartItemsModel.setItemImage(object.getImageView());
        cartItemsModel.setItemName(object.getName());
        cartItemsModel.setItemPrice(String.valueOf(object.getPrice()));
        cartItemsModel.setQty(qty);
        dbHelper.insertDataIntoDB(cartItemsModel);
    }
}

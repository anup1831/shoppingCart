package com.anup.pricingbasketsecond;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.anup.pricingbasketsecond.models.DataFixer;
import com.anup.pricingbasketsecond.models.Rates;
import com.anup.pricingbasketsecond.models.RatesModel;
import com.anup.pricingbasketsecond.network.ApiInterface;
import com.anup.pricingbasketsecond.network.RetrofitApiClient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Anup on 5/21/2018.
 */

public class CheckoutActivity extends Activity implements AdapterView.OnItemSelectedListener/*, CartItemAdapter.calculatePriceListener*/{
   private CartItemAdapter cartItemAdapter;
    List<CartItemsModel> rowListItem;
    RecyclerView rView;
    RecyclerView.LayoutManager mLayoutManager;
    ItemGridViewObject object;
    LocalDbHelper dbHelper;
    DataFixer dataFixer;
    Spinner currencySpinner;
    TextView tv_total;
    List<RatesModel> ratesModelList;
    RatesModel ratesModel;
    double totalPrice;
    String qty;
    double currencyValue;

    public double getCurrencyValue() {
        return currencyValue;
    }

    public void setCurrencyValue(double currencyValue) {
        this.currencyValue = currencyValue;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        Intent intent = getIntent();
        qty = intent.getStringExtra("QTY");
        object = intent.getParcelableExtra("INTENT_OBJECT");
        getJsonDataFromFixer();
        dataFixer = new DataFixer();
        tv_total = (TextView) findViewById(R.id.tv_total);
        int qtty = Integer.parseInt(qty);
        Log.i("Anup", "Error -"+ " getCurrencyValue"+getCurrencyValue()+
                +object.getPrice());
        Log.i("Anup", "Error -"+ qtty);

        /*dbHelper = new LocalDbHelper(CheckoutActivity.this);
        rowListItem = dbHelper.getAllCartItems();
        currencySpinner = (Spinner) findViewById(R.id.spinner1);
        rView = (RecyclerView) findViewById(R.id.rc_cartview);
        tv_total = (TextView) findViewById(R.id.tv_total);

        cartItemAdapter = new CartItemAdapter(CheckoutActivity.this, rowListItem);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rView.setLayoutManager(mLayoutManager);
        rView.setItemAnimator(new DefaultItemAnimator());
        rView.setAdapter(cartItemAdapter);*/


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        dbHelper.deleteTableContent();
    }

    @Override
    protected void onResume() {
        super.onResume();
/*
        cartItemAdapter.setOnItemClickListener(new CartItemAdapter.OnRecyclerCartViewClickListener() {
            @Override
            public void onItemClick(Context context, CartItemsModel itemAtPosition) {
                Toast.makeText(context, "Feature will be added soon -" + itemAtPosition, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onMinusClick(Context context, int quantity) {
                int qty = quantity - 1;
                Toast.makeText(context, "Feature will be added soon -" + qty, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPlusClick(Context context, int quantity) {
                int qty = quantity + 1;
                Toast.makeText(context, "Feature will be added soon -" + qty, Toast.LENGTH_LONG).show();
            }

            @Override
            public void calculatePrice(Context context, int quantity, double basePrice) {
                Log.i("Anup", "qty - price "+ quantity + "- "+basePrice);
                 setTotalPrice(quantity*basePrice);
            }
        });*/

    }

    private void getJsonDataFromFixer() {
        ApiInterface requestInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
        final Call<DataFixer> jsonHeadCall = requestInterface.getHeadJson();

        jsonHeadCall.enqueue(new Callback<DataFixer>() {
            @Override
            public void onResponse(Call<DataFixer> call, Response<DataFixer> response) {
                ratesModelList = new ArrayList<>();
                boolean success = response.body().getSuccess();
                dataFixer.setSuccess(response.body().getSuccess());
                dataFixer.setBase(response.body().getBase());
                dataFixer.setDate(response.body().getDate());
                dataFixer.setTimestamp(response.body().getTimestamp());
                dataFixer.setRates(response.body().getRates());
                ratesModelList.add(new RatesModel("USD", dataFixer.getRates().getUSD()));
                ratesModelList.add(new RatesModel("AUD", dataFixer.getRates().getAUD()));
                ratesModelList.add(new RatesModel("CAD", dataFixer.getRates().getCAD()));
                ratesModelList.add(new RatesModel("PLZ", dataFixer.getRates().getPLN()));
                ratesModelList.add(new RatesModel("MXN", dataFixer.getRates().getMXN()));
                Log.i("Anup", "status -"+success + " - "+dataFixer.getRates().getAUD() + " - "+ratesModelList.size());
                setUpCurrencySpinner(ratesModelList);
            }

            @Override
            public void onFailure(Call<DataFixer> call, Throwable t) {
                Log.i("Anup", "Error -"+t.getMessage().toString());
            }
        });
    }

    private void setUpCurrencySpinner(List<RatesModel> rModelList) {
        List<RatesModel> ratesModels = rModelList;
        List<String> currencyName = new ArrayList<>();

        for (RatesModel ratemodel: ratesModels) {
            currencyName.add(ratemodel.getCurrenyName());
            Log.i("Anup", "SpinneItem- "+ratemodel.getCurrenyName());
        }



        currencySpinner = (Spinner) findViewById(R.id.spinner1);
        currencySpinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, currencyName);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        currencySpinner.setAdapter(spinnerAdapter);

        //Toast.makeText(this, ""+Double.parseDouble(qty.trim())*object.getPrice() *getCurrencyValue(), Toast.LENGTH_SHORT).show();
        //tv_total.setText("Total : "+Double.parseDouble(qty)*object.getPrice() *getCurrencyValue());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getItemAtPosition(position).equals("USD")){
            setCurrencyValue(dataFixer.getRates().getUSD());
        } else if (parent.getItemAtPosition(position).equals("AUD")){
            setCurrencyValue(dataFixer.getRates().getAUD());
        }else if (parent.getItemAtPosition(position).equals("CAD")){
            setCurrencyValue(dataFixer.getRates().getCAD());
        }else if (parent.getItemAtPosition(position).equals("PLZ")){
            setCurrencyValue(dataFixer.getRates().getPLN());
        }else if (parent.getItemAtPosition(position).equals("MXN")){
            setCurrencyValue(dataFixer.getRates().getMXN());
        }
        Log.i("Anup", "SpinneItemSelected itemAtPosition- "+parent.getItemAtPosition(position).toString()
        + "getSelectedItem "+parent.getSelectedItem().toString() + " total- "+getTotalPrice());

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
/*
    @Override
    public void calculateTotal(int quantity, double basePrice) {
        tv_total.setText("Total : "+quantity*basePrice * dataFixer.getRates().getUSD());
    }*/
}

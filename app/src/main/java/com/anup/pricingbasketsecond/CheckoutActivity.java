package com.anup.pricingbasketsecond;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.anup.pricingbasketsecond.dbutil.LocalDbHelper;
import com.anup.pricingbasketsecond.models.DataFixer;
import com.anup.pricingbasketsecond.models.Rates;
import com.anup.pricingbasketsecond.models.RatesModel;
import com.anup.pricingbasketsecond.network.ApiInterface;
import com.anup.pricingbasketsecond.network.RetrofitApiClient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
    //LocalDbHelper dbHelper;
    DataFixer dataFixer;
    Spinner currencySpinner;
    TextView tv_total, tv_exchangeTotal;
    List<RatesModel> ratesModelList, fetchRatesDataList;
    List<String> cName;
    RatesModel ratesModel;
    double totalPrice;
    int qty;
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
        getJsonDataFromFixer();
        initializeDataMembers();
        initView();

        if(qty == 0){
            tv_total.setText("Total - ");
        } else{
            totalPrice = qty*object.getPrice();
            setTotalPrice(totalPrice);
            tv_total.setText("Total - "+ totalPrice);
        }
        LocalDbHelper dbHelper = new LocalDbHelper(CheckoutActivity.this);
        fetchRatesDataList = dbHelper.getAllCurrency();
        setUpCurrencySpinner(fetchRatesDataList);
        //rowListItem = dbHelper.getAllCartItems();
        //rView = (RecyclerView) findViewById(R.id.rc_cartview);
        /*cartItemAdapter = new CartItemAdapter(CheckoutActivity.this, rowListItem);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rView.setLayoutManager(mLayoutManager);
        rView.setItemAnimator(new DefaultItemAnimator());
        rView.setAdapter(cartItemAdapter);*/


    }

    private void initializeDataMembers() {
        Intent intent = getIntent();
        qty = Integer.parseInt(intent.getStringExtra("QTY"));
        object = intent.getParcelableExtra("INTENT_OBJECT");


        dataFixer = new DataFixer();
        ratesModelList = new ArrayList<>();
        fetchRatesDataList = new ArrayList<RatesModel>();
        cName = new ArrayList<String>();
    }

    private void initView() {
        tv_total = (TextView) findViewById(R.id.tv_total);
        tv_exchangeTotal = (TextView) findViewById(R.id.tv_exchange_total);
        currencySpinner = (Spinner) findViewById(R.id.spinner1);
        currencySpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LocalDbHelper dbHelper = new LocalDbHelper(CheckoutActivity.this);
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
                LocalDbHelper dbHelper = new LocalDbHelper(CheckoutActivity.this);
                boolean success = response.body().getSuccess();
                dataFixer.setSuccess(response.body().getSuccess());
                dataFixer.setBase(response.body().getBase());
                dataFixer.setDate(response.body().getDate());
                dataFixer.setTimestamp(response.body().getTimestamp());
                dataFixer.setRates(response.body().getRates());

                ratesModelList.add(new RatesModel("USD", response.body().getRates().getUSD()));
                ratesModelList.add(new RatesModel("AUD", response.body().getRates().getAUD()));
                ratesModelList.add(new RatesModel("CAD", response.body().getRates().getCAD()));
                ratesModelList.add(new RatesModel("PLN", response.body().getRates().getPLN()));
                ratesModelList.add(new RatesModel("MXN", response.body().getRates().getMXN()));
                dbHelper.insertExhangeRatesIntoDB(ratesModelList);
                //addCurrencyRatesInDB(response.body());

            }

            @Override
            public void onFailure(Call<DataFixer> call, Throwable t) {
                Log.i("Anup", "Error -"+t.getMessage().toString());
            }
        });
    }

    private void setUpCurrencySpinner(List<RatesModel> rateslList) {

       for (int i = 0; i< rateslList.size(); i++){
           cName.add(rateslList.get(i).getCurrenyName());
       }
        Log.i("Anup", "Error -"+cName.size() + " - "+rateslList.size());

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cName);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        currencySpinner.setAdapter(spinnerAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        for (int i=0; i<ratesModelList.size();i++){
            setCurrencyValue(ratesModelList.get(position).getRate());
            tv_exchangeTotal.setText("After Exchange Total : "+getTotalPrice() * getCurrencyValue());
            Log.i("Anup", "SpinneItemSelected itemAtPosition- "+parent.getItemAtPosition(position).toString());

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
/*
    @Override
    public void calculateTotal(int quantity, double basePrice) {
        tv_total.setText("Total : "+quantity*basePrice * dataFixer.getRates().getUSD());
    }*/

    @Override
    protected void onPause() {
        super.onPause();
        ratesModelList = null;
        fetchRatesDataList = null;
        totalPrice=0;
        currencyValue=0;
        qty=0;
        dataFixer=null;
        object = null;

    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalDbHelper dbHelper = new LocalDbHelper(CheckoutActivity.this);
        dbHelper.deleteTableContent();
    }
}

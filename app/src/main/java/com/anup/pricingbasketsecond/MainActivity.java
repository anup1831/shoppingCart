package com.anup.pricingbasketsecond;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.anup.pricingbasketsecond.adapter.RecyclerViewAdapter;
import com.anup.pricingbasketsecond.models.ItemGridViewObject;
import com.anup.pricingbasketsecond.productmain.ProductMainView;
import com.anup.pricingbasketsecond.productmain.interactor.ProductMainInteractorImpl;
import com.anup.pricingbasketsecond.productmain.presenter.ProductMainPresenter;
import com.anup.pricingbasketsecond.productmain.presenter.ProductMainPresenterImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ProductMainView, RecyclerViewAdapter.OnRecyclerViewClickListener{

    public static final String TAG = MainActivity.class.getSimpleName();
    private GridLayoutManager lLayout;
    RecyclerViewAdapter rcAdapter;
    List<ItemGridViewObject> rowListItem;
    ProductMainPresenter presenter;
    RecyclerView rView;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPresenter();
        //List<ItemGridViewObject> rowListItem = getAllItemList();
        lLayout = new GridLayoutManager(getApplicationContext(), 2);

        progressBar = (ProgressBar) findViewById(R.id.progress);
        rView = (RecyclerView) findViewById(R.id.rc_view);

    }

    private void initPresenter() {
        //presenter.onAttach(this);
        Log.i(TAG, "Anup - initialize Presenter Impl");
        presenter = new ProductMainPresenterImpl(new ProductMainInteractorImpl(), this);
    }

   /* private List<ItemGridViewObject> getAllItemList(){

        List<ItemGridViewObject> allItems = new ArrayList<ItemGridViewObject>();
        allItems.add(new ItemGridViewObject(R.drawable.beans, "Beans", .73 ));
        allItems.add(new ItemGridViewObject(R.drawable.greenpeas, "Peas", .95));
        allItems.add(new ItemGridViewObject(R.drawable.eggs, "Eggs", 2.10));
        allItems.add(new ItemGridViewObject(R.drawable.milk, "Milk", 1.30));

        return allItems;
    }*/

/*    @Override
    protected void onResume() {
        super.onResume();
        *//*rcAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnRecyclerViewClickListener() {
            @Override
            public void onItemClick(Context context, ItemGridViewObject position) {
                Toast.makeText(context.getApplicationContext(), "ItemATPosition - " + position.getName() +
                        position.getPrice()+ " - "+position.getImageView(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, ItemDetailsView.class);
                intent.putExtra("INTENT_OBJECT", position);
                startActivity(intent);
            }
        });*//*
    }*/

    @Override
    public void onProductGridListReceived(List<ItemGridViewObject> gridViewObjectList) {
        Log.i(TAG, "ProductList - "+gridViewObjectList.size());
        rowListItem = gridViewObjectList;
        rcAdapter = new RecyclerViewAdapter(MainActivity.this, gridViewObjectList);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);
        rView.setAdapter(rcAdapter);
    }

   /* @Override
    public void onItemDetailsViewStartListener(ItemGridViewObject itemGridViewObject) {
        *//*rcAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnRecyclerViewClickListener() {
            @Override
            public void onItemClick(Context context, ItemGridViewObject position) {
                Toast.makeText(context.getApplicationContext(), "ItemATPosition - " + position.getName() +
                        position.getPrice()+ " - "+position.getImageView(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, ItemDetailsView.class);
                intent.putExtra("INTENT_OBJECT", position);
                startActivity(intent);
            }
        });*//*
        Intent intent = new Intent(MainActivity.this, ItemDetailsView.class);
        intent.putExtra("INTENT_OBJECT", itemGridViewObject);
        startActivity(intent);
    }*/

    @Override
    public void showEmptyView() {

    }

  /*  @Override
    public void showError() {

    }

    @Override
    public void showNetworkError() {

    }*/

    @Override
    public boolean isNetworkAvailable() {
        // Make this method return true for now by default it will be false
        return true;
    }

    @Override
    public void showFullScreenLoading() {
        progressBar.setVisibility(View.VISIBLE);
        rView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideFullScreenLoading() {
        progressBar.setVisibility(View.INVISIBLE);
        rView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(Context context, ItemGridViewObject itemAtPosition) {
        //if get whole object pass it to next screen
        Toast.makeText(this, itemAtPosition.getName(), Toast.LENGTH_LONG).show();
    }

   /* public String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream is = getAssets().open("dishes.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        Log.e(TAG, "Json response " + json);
        return json;

    }*/
}

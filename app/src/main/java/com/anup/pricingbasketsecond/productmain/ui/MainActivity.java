package com.anup.pricingbasketsecond.productmain.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.anup.pricingbasketsecond.itemdetails.ItemDetailsScreen;
import com.anup.pricingbasketsecond.R;
import com.anup.pricingbasketsecond.adapter.RecyclerViewAdapter;
import com.anup.pricingbasketsecond.models.ItemGridViewObject;
import com.anup.pricingbasketsecond.productmain.interactor.ProductMainInteractorImpl;
import com.anup.pricingbasketsecond.productmain.presenter.ProductMainPresenter;
import com.anup.pricingbasketsecond.productmain.presenter.ProductMainPresenterImpl;
import com.anup.pricingbasketsecond.utils.FullScreenLoading;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ProductMainView, RecyclerViewAdapter.OnRecyclerViewClickListener{

    public static final String TAG = MainActivity.class.getSimpleName();
    private GridLayoutManager lLayout;
    RecyclerViewAdapter rcAdapter;
    List<ItemGridViewObject> rowListItem;
    ProductMainPresenter presenter;
    RecyclerView rView;
    private ProgressBar progressBar;
    private FullScreenLoading progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPresenter();
        initUI();

    }

    private void initUI(){
        progressBar = (ProgressBar) findViewById(R.id.progress);
        rView = (RecyclerView) findViewById(R.id.rc_view);
        lLayout = new GridLayoutManager(getApplicationContext(), 2);
        progressDialog = new FullScreenLoading(this);
    }

    private void initPresenter() {
        //presenter.onAttach(this);
        Log.i(TAG, "Anup - initialize Presenter Impl");
        presenter = new ProductMainPresenterImpl(this, new ProductMainInteractorImpl());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void setProductGridList(List<ItemGridViewObject> gridViewObjectList) {
        Log.i(TAG, "ProductList - "+gridViewObjectList.size());
        rowListItem = gridViewObjectList;
        rcAdapter = new RecyclerViewAdapter(MainActivity.this, gridViewObjectList);
        rcAdapter.setOnItemClickListener(this);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);
        rView.setAdapter(rcAdapter);
    }

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
       /* progressBar.setVisibility(View.VISIBLE);
        rView.setVisibility(View.INVISIBLE);*/
        if(progressDialog != null && !progressDialog.isShowing()){
            progressDialog.show();
        }
    }

    @Override
    public void hideFullScreenLoading() {
        /*progressBar.setVisibility(View.INVISIBLE);
        rView.setVisibility(View.VISIBLE);*/
        if(progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToDetailsScreenWithIntentObject(ItemGridViewObject itemAtposition) {
        Toast.makeText(this, itemAtposition.getName() + " - "+itemAtposition.getImageView() + " - "+itemAtposition.getPrice(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainActivity.this, ItemDetailsScreen.class);
        intent.putExtra("INTENT_OBJECT", itemAtposition);
        startActivity(intent);
    }

    @Override
    public void onItemClick(ItemGridViewObject itemAtPosition) {
        presenter.onGridViewClick(itemAtPosition);
    }

    @Override
    public void getItemPosition(int position) {
        presenter.showMessageAtPosition(position);
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

package com.anup.pricingbasketsecond;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private GridLayoutManager lLayout;
    RecyclerViewAdapter rcAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<ItemGridViewObject> rowListItem = getAllItemList();
        lLayout = new GridLayoutManager(getApplicationContext(), 2);

        RecyclerView rView = (RecyclerView) findViewById(R.id.rc_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);


        rcAdapter = new RecyclerViewAdapter(MainActivity.this, rowListItem);
        rView.setAdapter(rcAdapter);
    }

    private List<ItemGridViewObject> getAllItemList(){

        List<ItemGridViewObject> allItems = new ArrayList<ItemGridViewObject>();
        allItems.add(new ItemGridViewObject(R.drawable.beans, "Beans", 73 ));
        allItems.add(new ItemGridViewObject(R.drawable.greenpeas, "Peas", 95));
        allItems.add(new ItemGridViewObject(R.drawable.eggs, "Eggs", 2.10));
        allItems.add(new ItemGridViewObject(R.drawable.milk, "Milk", 1.30));

        return allItems;
    }

    @Override
    protected void onResume() {
        super.onResume();
        rcAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnRecyclerViewClickListener() {
            @Override
            public void onItemClick(Context context, ItemGridViewObject position) {
                Toast.makeText(context.getApplicationContext(), "ItemATPosition - " + position.getName() +
                        position.getPrice()+ " - "+position.getImageView(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, ItemDetailsView.class);
                intent.putExtra("INTENT_OBJECT", position);
                startActivity(intent);
            }
        });
    }
}

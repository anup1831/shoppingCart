package com.anup.pricingbasketsecond.dbutil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.anup.pricingbasketsecond.models.CartItemsModel;
import com.anup.pricingbasketsecond.models.RatesModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anup on 5/22/2018.
 */

public class LocalDbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "cart";
    public static final int DB_VERSION = 2;
    //Table details - cart_items
    public static final String TB_CART_ITEMS = "cart_Items";
    public static final String ID = "id";
    public static final String ITEM_NAME = "name";
    public static final String ITEM_IMAGE = "image";
    public static final String ITEM_PRICE = "price";
    public static final String ITEM_QTY = "qty";
    //Table rates
    public static final String TB_RATES = "exchange_rates";
    public static final String CURRENCY_ID = "id";
    public static final String CURRENCY_NAME = "currency_name";
    public static final String EXCHANGE_RATE = "exchange_rate";
    //Query part
    public static final String DROP = "DROP TABLE IF EXISTES ";

    public LocalDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createCartItems = "CREATE TABLE "+ TB_CART_ITEMS + " ("
                + ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ITEM_NAME+ " TEXT NOT NULL, "
                + ITEM_IMAGE+ " INTEGER NOT NULL, "
                + ITEM_PRICE + " TEXT NOT NULL, "
                + ITEM_QTY + " TEXT NOT NULL"
                + " )";

        String createExchangeRates = "CREATE TABLE "+ TB_RATES + " ("
                + CURRENCY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CURRENCY_NAME+ " TEXT NOT NULL, "
                + EXCHANGE_RATE+ " DOUBLE NOT NULL"
                + " )";

        db.execSQL(createCartItems);
        db.execSQL(createExchangeRates);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            db.execSQL(DROP + TB_CART_ITEMS);
            db.execSQL(DROP + TB_RATES);
        }
        onCreate(db);
    }

    //Insert items to table to show for review before checkout
    public long insertDataIntoDB(CartItemsModel cartItemsModel){
        long rowInserted = -5;
        SQLiteDatabase db = this.getWritableDatabase();

        try{
            ContentValues values = new ContentValues();
            values.put(ITEM_NAME, cartItemsModel.getItemName());
            values.put(ITEM_IMAGE, cartItemsModel.getItemImage());
            values.put(ITEM_PRICE, cartItemsModel.getItemPrice());
            values.put(ITEM_QTY, cartItemsModel.getQty());
            rowInserted = db.insert(TB_CART_ITEMS, null, values);
            Log.i("Anup",  "Anup "+rowInserted);
            return rowInserted;
        }catch (Exception e){

        }
        return 0;
    }

    public void insertExhangeRatesIntoDB(List<RatesModel> ratesModelList){
        Log.i("Anup",  "RatesModelListSize "+ratesModelList.size());
        long rowInserted = -5;
        SQLiteDatabase db = getWritableDatabase();
        for (int i=0; i<ratesModelList.size(); i++){
            try{
                ContentValues values = new ContentValues();
                Log.i("Anup",  "Anup inserted Rates "+ratesModelList.get(i).getCurrenyName() + " - "+ratesModelList.get(i).getRate());
                values.put(CURRENCY_NAME, ratesModelList.get(i).getCurrenyName());
                values.put(EXCHANGE_RATE, ratesModelList.get(i).getRate());

                db.insert(TB_RATES, null, values);
            }catch (Exception e){

            }
        }
        db.close();
    }

    // get number of item inserted or row count
    public int getRowCounts(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try{
            String countQuery = "SELECT * FROM " + TB_CART_ITEMS;
            cursor = db.rawQuery(countQuery, null);
            return cursor.getCount();
        }catch (Exception e){

        }


        return 0;
    }

    //fetched all data to show in cartview
    public List<CartItemsModel> getAllCartItems(){
        List<CartItemsModel> cartItemList = new ArrayList<CartItemsModel>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT ROWID, * FROM " + TB_CART_ITEMS;

        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor != null && cursor.getCount() > -1){
            while (cursor.moveToNext()){
                CartItemsModel cartModel = new CartItemsModel();
                Log.i("Anup", "fetched CartItems ID, NAME, PRICE, QTY "
                        +cursor.getInt(cursor.getColumnIndex(LocalDbHelper.ID))
                        +cursor.getString(cursor.getColumnIndex(LocalDbHelper.ITEM_NAME))
                        +cursor.getInt(cursor.getColumnIndex(LocalDbHelper.ITEM_IMAGE))
                        +cursor.getString(cursor.getColumnIndex(LocalDbHelper.ITEM_PRICE))
                        +cursor.getInt(cursor.getColumnIndex(LocalDbHelper.ITEM_QTY)));

                cartModel.setItemId(cursor.getInt(cursor.getColumnIndex(LocalDbHelper.ID)));
                cartModel.setItemName(cursor.getString(cursor.getColumnIndex(LocalDbHelper.ITEM_NAME)));
                cartModel.setItemImage(cursor.getInt(cursor.getColumnIndex(LocalDbHelper.ITEM_IMAGE)));
                cartModel.setItemPrice(cursor.getString(cursor.getColumnIndex(LocalDbHelper.ITEM_PRICE)));
                cartModel.setQty(cursor.getString(cursor.getColumnIndex(LocalDbHelper.ITEM_QTY)));
                cartItemList.add(cartModel);
            }
        }
        return cartItemList;
    }

    public List<RatesModel> getAllCurrency(){
        List<RatesModel> ratesModelList = new ArrayList<RatesModel>();
        RatesModel ratesMode;
        SQLiteDatabase db = getReadableDatabase();

        if(db.isOpen()){
            Log.i("Anup", "isDbOpen "
                    +db.isOpen());
            String selectQuery = "SELECT * FROM " + TB_RATES;
            Cursor cursor = db.rawQuery(selectQuery, null);
            Log.i("Anup", "fetched CartItems ID, NAME, PRICE, QTY "
                    +cursor.getCount());
            //if(cursor != null && cursor.getCount() > -1){
            if (cursor.moveToFirst()) {
                do {
                    ratesMode = new RatesModel();
                    Log.i("Anup", "fetched CartItems ID, NAME, PRICE, QTY "
                            +cursor.getInt(cursor.getColumnIndex(LocalDbHelper.CURRENCY_ID))
                            +cursor.getString(cursor.getColumnIndex(LocalDbHelper.CURRENCY_NAME))
                            +cursor.getDouble(cursor.getColumnIndex(LocalDbHelper.EXCHANGE_RATE)));

                    ratesMode.setId(cursor.getInt(cursor.getColumnIndex(LocalDbHelper.CURRENCY_ID)));
                    ratesMode.setCurrenyName(cursor.getString(cursor.getColumnIndex(LocalDbHelper.CURRENCY_NAME)));
                    ratesMode.setRate(cursor.getDouble(cursor.getColumnIndex(LocalDbHelper.EXCHANGE_RATE)));
                    ratesModelList.add(ratesMode);
                } while (cursor.moveToNext());
            }
            /*while (cursor.moveToNext()){
                ratesMode = new RatesModel();
                Log.i("Anup", "fetched CartItems ID, NAME, PRICE, QTY "
                        +cursor.getInt(cursor.getColumnIndex(LocalDbHelper.CURRENCY_ID))
                        +cursor.getString(cursor.getColumnIndex(LocalDbHelper.CURRENCY_NAME))
                        +cursor.getDouble(cursor.getColumnIndex(LocalDbHelper.EXCHANGE_RATE)));

                ratesMode.setId(cursor.getInt(cursor.getColumnIndex(LocalDbHelper.CURRENCY_ID)));
                ratesMode.setCurrenyName(cursor.getString(cursor.getColumnIndex(LocalDbHelper.CURRENCY_NAME)));
                ratesMode.setRate(cursor.getDouble(cursor.getColumnIndex(LocalDbHelper.EXCHANGE_RATE)));
                ratesModelList.add(ratesMode);
                return ratesModelList;
            }*/
            cursor.close();
        }
            db.close();
        //}
        return ratesModelList;
    }

    //Update qty
    public void updateQty(int Id, int qtyTobeUpdated){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues updatedValues = new ContentValues();
        //updatedValues.put(ID, Id);
        Log.i("Anup", "updated value -"+qtyTobeUpdated);
        updatedValues.put(ITEM_QTY, qtyTobeUpdated);

        db.update(TB_CART_ITEMS, updatedValues, ID + " = ?", new String[] { String.valueOf(Id)});

    }

    //Delete table data
    public void deleteTableContent(){
        SQLiteDatabase db = this.getWritableDatabase();
        //db.delete(TB_CART_ITEMS, null, null);
        db.execSQL("delete from "+ TB_CART_ITEMS);
        db.execSQL("delete from "+ TB_RATES);
        db.execSQL("vacuum");
    }
}

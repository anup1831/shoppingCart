package com.anup.pricingbasketsecond;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anup on 5/22/2018.
 */

public class LocalDbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "cart";
    public static final int DB_VERSION = 1;
    //Table details
    public static final String TB_CART_ITEMS = "cart_Items";
    public static final String ID = "id";
    public static final String ITEM_NAME = "name";
    public static final String ITEM_IMAGE = "image";
    public static final String ITEM_PRICE = "price";
    public static final String ITEM_QTY = "qty";
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

        db.execSQL(createCartItems);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            db.execSQL(DROP + TB_CART_ITEMS);
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

        } finally {
            db.close();
        }
        return 0;
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

        }finally {
            db.close();
        }


        return 0;
    }

    //fetched all data to show in cartview
    public List<CartItemsModel> getAllCartItems(){
        List<CartItemsModel> cartItemList = new ArrayList<CartItemsModel>();

        String selectQuery = "SELECT ROWID, * FROM " + TB_CART_ITEMS;
        SQLiteDatabase db = this.getWritableDatabase();

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

    //Update qty
    public void updateTodo(int Id, int qtyTobeUpdated){
        String updatedDesc = "";
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
        db.execSQL("vacuum");
    }
}

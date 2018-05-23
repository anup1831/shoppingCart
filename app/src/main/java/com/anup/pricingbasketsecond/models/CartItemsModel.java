package com.anup.pricingbasketsecond.models;

/**
 * Created by Anup on 5/22/2018.
 */

public class CartItemsModel {
    private int itemId;
    private String itemName;
    private int itemImage;
    private String itemPrice;
    private String qty;

    public CartItemsModel() {

    }

    public CartItemsModel(int itemId, String itemName, int itemImage, String itemPrice, String qty) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemImage = itemImage;
        this.itemPrice = itemPrice;
        this.qty = qty;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public int getItemImage() {
        return itemImage;
    }

    public void setItemImage(int itemImage) {
        this.itemImage = itemImage;
    }
}

package com.anup.pricingbasketsecond.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Anup on 5/20/2018.
 */

public class ItemGridViewObject implements /*Serializable*/ Parcelable{
    private int imageView;
    private String name;
    private double price;

    public ItemGridViewObject(int imageView, String name, double price) {
        this.imageView = imageView;
        this.name = name;
        this.price = price;
    }

    protected ItemGridViewObject(Parcel in) {
        imageView = in.readInt();
        name = in.readString();
        price = in.readDouble();
    }

    public static final Creator<ItemGridViewObject> CREATOR = new Creator<ItemGridViewObject>() {
        @Override
        public ItemGridViewObject createFromParcel(Parcel in) {
            return new ItemGridViewObject(in);
        }

        @Override
        public ItemGridViewObject[] newArray(int size) {
            return new ItemGridViewObject[size];
        }
    };

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageView);
        dest.writeString(name);
        dest.writeDouble(price);
    }
}

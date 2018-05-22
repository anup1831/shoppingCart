package com.anup.pricingbasketsecond.models;

/**
 * Created by Anup on 5/22/2018.
 */

public class RatesModel {
    private String currenyName;
    private double rate;

    public RatesModel(String currenyName, double rate) {
        this.currenyName = currenyName;
        this.rate = rate;
    }

    public String getCurrenyName() {
        return currenyName;
    }

    public void setCurrenyName(String currenyName) {
        this.currenyName = currenyName;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}

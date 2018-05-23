package com.anup.pricingbasketsecond.models;

/**
 * Created by Anup on 5/22/2018.
 */

public class RatesModel {
    private int id;
    private String currenyName;
    private double rate;

    public RatesModel() {
    }

    public RatesModel(String currenyName, double rate) {
        this.currenyName = currenyName;
        this.rate = rate;
    }
    public RatesModel(int id, String currenyName, double rate) {
        this.id = id;
        this.currenyName = currenyName;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

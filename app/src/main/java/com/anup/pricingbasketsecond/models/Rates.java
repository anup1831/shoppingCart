package com.anup.pricingbasketsecond.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by Anup on 5/22/2018.
 */

public class Rates {

    @SerializedName("USD")
    @Expose
    private Double uSD;
    @SerializedName("AUD")
    @Expose
    private Double aUD;
    @SerializedName("CAD")
    @Expose
    private Double cAD;
    @SerializedName("PLN")
    @Expose
    private Double pLN;
    @SerializedName("MXN")
    @Expose
    private Double mXN;

    public Rates(Double uSD, Double aUD, Double cAD, Double pLN, Double mXN) {
        this.uSD = uSD;
        this.aUD = aUD;
        this.cAD = cAD;
        this.pLN = pLN;
        this.mXN = mXN;
    }

    public Double getUSD() {
        return uSD;
    }

    public void setUSD(Double uSD) {
        this.uSD = uSD;
    }

    public Double getAUD() {
        return aUD;
    }

    public void setAUD(Double aUD) {
        this.aUD = aUD;
    }

    public Double getCAD() {
        return cAD;
    }

    public void setCAD(Double cAD) {
        this.cAD = cAD;
    }

    public Double getPLN() {
        return pLN;
    }

    public void setPLN(Double pLN) {
        this.pLN = pLN;
    }

    public Double getMXN() {
        return mXN;
    }

    public void setMXN(Double mXN) {
        this.mXN = mXN;
    }
}

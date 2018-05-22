package com.anup.pricingbasketsecond.network;

import com.anup.pricingbasketsecond.models.DataFixer;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Anup on 5/22/2018.
 */

public interface ApiInterface {
    @GET("latest?access_key=e0933299b9f377526869f41a90237704&symbols=USD,AUD,CAD,PLN,MXN&format=1")
    Call<DataFixer> getHeadJson();// java version
}

package com.anup.pricingbasketsecond.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Anup on 5/22/2018.
 */

public class RetrofitApiClient {

    public static final String BASE_URL="http://data.fixer.io/api/";//"http://data.fixer.io/api/latest?access_key=e0933299b9f377526869f41a90237704/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

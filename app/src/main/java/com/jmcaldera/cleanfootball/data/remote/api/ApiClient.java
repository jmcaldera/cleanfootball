package com.jmcaldera.cleanfootball.data.remote.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jmcaldera on 26/05/17.
 */

public class ApiClient {

    private static final String BASE_URL = "http://api.football-data.org/v1/";

    public ApiEndpoints establishConnection() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiEndpoints.class);
    }
}

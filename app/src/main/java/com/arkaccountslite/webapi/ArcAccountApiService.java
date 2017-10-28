package com.arkaccountslite.webapi;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;


public class ArcAccountApiService {

    private static final EnvironmentConfig CONFIG = new EnvironmentConfig();
    private static ArcAccountApiService arcAccountApiService = new ArcAccountApiService();
    private ArcAccountApiClient service;

    public static ArcAccountApiService getInstance() {
        if (arcAccountApiService == null)
            return new ArcAccountApiService();
        else
            return arcAccountApiService;
    }

   /* public Result<CategoryList> getCategories() {
        try {
            return new Result<>(getService().getCategories());
        } catch (RetrofitError error) {
            return new Result<>(error);
        }
    }*/


    private ArcAccountApiClient getService() {
        if (service == null) {
            RestAdapter retrofit = new RestAdapter.Builder()
                    .setEndpoint(CONFIG.getBaseUrl())
                    .setLogLevel(CONFIG.getRetrofitLogLevel())
                    .setClient(new OkClient(new OkHttpClient()))
                    .build();

            service = retrofit.create(ArcAccountApiClient.class);
        }
        return service;
    }

}

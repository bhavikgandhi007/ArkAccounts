package com.arkaccountslite.rest;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class MainTransport {

    private static final String TAG = MainTransport.class.getSimpleName();

    private MainTransport() {
    }

    /**
     * Init with custom interceptor and increased timeout.
     *
     * @param <T>     the type parameter
     * @param service the service class
     * @return service object
     */
    public static <T> T init(Class<T> service) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        // Customize the request
                        Request request = original.newBuilder()
                                .header("Content-Type", "application/json")
                                .build();
                        Response response = chain.proceed(request);
                        response.cacheResponse();
                        // Customize or return the response
                        return response;
                    }
                })
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit adapter = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://arkbookapi-dev.ap-south-1.elasticbeanstalk.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return adapter.create(service);
    }


    /**
     * Init with custom interceptor, increased timeout custom gson.
     *
     * @param <T>        the type parameter
     * @param service    the service class
     * @param customGson the custom gson for parsing
     * @return service object
     */
    public static <T> T init(Class<T> service, Gson customGson) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        // Customize the request
                        Request request = original.newBuilder()
                                .header("Content-Type", "application/json")
                                .build();
                        Response response = chain.proceed(request);
                        response.cacheResponse();
                        // Customize or return the response
                        return response;
                    }
                })
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();


        Retrofit adapter = new Retrofit.Builder()
                .baseUrl("http://arkbookapi-dev.ap-south-1.elasticbeanstalk.com/")
                .addConverterFactory(GsonConverterFactory.create(customGson))
                .build();

        return adapter.create(service);
    }

}
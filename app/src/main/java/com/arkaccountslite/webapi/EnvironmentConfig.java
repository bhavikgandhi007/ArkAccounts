package com.arkaccountslite.webapi;

import android.util.Log;

import static retrofit.RestAdapter.LogLevel;

public class EnvironmentConfig {

    public static final String TAG = EnvironmentConfig.class.getSimpleName();

    public static final String ENV = "production";

    public EnvironmentConfig() {
        Log.i(TAG, "ENV: " + ENV);
    }

    public String getBaseUrl() {
        return "http://192.168.1.45:93";
    }

    public LogLevel getRetrofitLogLevel() {
        return LogLevel.FULL;
    }

}
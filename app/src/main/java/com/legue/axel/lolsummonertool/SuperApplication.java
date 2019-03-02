package com.legue.axel.lolsummonertool;

import android.app.Application;

import com.legue.axel.lolsummonertool.retrofit.RetrofitManager;

import okhttp3.ResponseBody;

public class SuperApplication extends Application {

    private static SuperApplication instance;
    private RetrofitManager mRetrofitManager;
    private ResponseBody responseBody;

    public static SuperApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mRetrofitManager = new RetrofitManager();
    }

    public RetrofitManager getRetrofitManager() {
        return mRetrofitManager;
    }

    public ResponseBody getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }
}

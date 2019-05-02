package com.legue.axel.lolsummonertool;

import android.app.Application;
import com.legue.axel.lolsummonertool.network.response.champion.ChampionsResponse;
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitManager;


public class SuperApplication extends Application {

    private static SuperApplication instance;
    private RetrofitManager mRetrofitManager;
    private ChampionsResponse championsResponse;

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

    public ChampionsResponse getChampionsResponse() {
        return championsResponse;
    }

    public void setChampionsResponse(ChampionsResponse championsResponse) {
        this.championsResponse = championsResponse;
    }
}

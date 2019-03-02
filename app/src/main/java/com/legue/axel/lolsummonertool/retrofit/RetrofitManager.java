package com.legue.axel.lolsummonertool.retrofit;


import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private static final String TAG = RetrofitManager.class.getSimpleName();

    //private static final  String API_BASE_URL = "https://na1.api.riotgames.com";
    private static final String API_BASE_URL = "http://ddragon.leagueoflegends.com/";
    private static final String PAGE_KEY = "page";
    private static final String LANGUAGE_KEY = "language";
    private static final String API_KEY = "api_key";

    private final RiotService riotService;
    private final Retrofit retrofit;

    public RetrofitManager() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        riotService = retrofit.create(RiotService.class);
    }

    public Observable<ResponseBody> getChampions() {
        return riotService.getChampions();
    }


}

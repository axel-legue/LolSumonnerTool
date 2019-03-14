package com.legue.axel.lolsummonertool.retrofit;


import com.legue.axel.lolsummonertool.network.ChampionsResponse;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private static final String TAG = RetrofitManager.class.getSimpleName();
    private final RiotService riotService;


    public RetrofitManager() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        ErrorInterceptor errorInterceptor = new ErrorInterceptor();

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addNetworkInterceptor(errorInterceptor)
                .build();

        Retrofit retrofitDataDragon = new Retrofit.Builder()
                .baseUrl(Constants.API_DRAGON_BASE_CDN)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        riotService = retrofitDataDragon.create(RiotService.class);

    }

    public Observable<ChampionsResponse> getChampions() {
        String url = Constants.API_DRAGON_BASE_CDN + Constants.API_CHAMPION_VERSION + "/" + Constants.API_TYPE_DATA + "/" + Constants.LANGUAGE_KEY + "/" + "champion.json";
        return riotService.getChampions(url);
    }


}

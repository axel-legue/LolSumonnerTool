package com.legue.axel.lolsummonertool.network.retrofit;


import com.google.gson.GsonBuilder;
import com.legue.axel.lolsummonertool.network.response.champion.ChampionsResponse;
import com.legue.axel.lolsummonertool.network.response.item.ItemsResponse;
import com.legue.axel.lolsummonertool.network.response.mastery.MasteryResponse;
import com.legue.axel.lolsummonertool.network.response.summonerspell.SummonerSpellsResponse;

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
                .baseUrl(RetrofitConstants.API_DRAGON_BASE_CDN)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()))
                .build();

        riotService = retrofitDataDragon.create(RiotService.class);

    }

    public Observable<ChampionsResponse> getChampions() {
        String url = RetrofitConstants.API_DRAGON_BASE_CDN + RetrofitConstants.API_CHAMPION_VERSION + "/" + RetrofitConstants.API_TYPE_DATA + "/" + RetrofitConstants.LANGUAGE_KEY + "/" + "champion.json";
        return riotService.getChampions(url);
    }

    public Observable<ItemsResponse> getItems() {
        String url = RetrofitConstants.API_DRAGON_BASE_CDN + RetrofitConstants.API_CHAMPION_VERSION + "/" + RetrofitConstants.API_TYPE_DATA + "/" + RetrofitConstants.LANGUAGE_KEY + "/" + "item.json";
        return riotService.getItems(url);
    }

    public Observable<MasteryResponse> getMasteries() {
        String url = RetrofitConstants.API_DRAGON_BASE_CDN + RetrofitConstants.API_MASTERY_VERSION + "/" + RetrofitConstants.API_TYPE_DATA + "/" + RetrofitConstants.LANGUAGE_KEY + "/" + "mastery.json";
        return riotService.getMasteries(url);
    }

    public Observable<SummonerSpellsResponse> getSummonerSpells() {
        String url = RetrofitConstants.API_DRAGON_BASE_CDN + RetrofitConstants.API_MASTERY_VERSION + "/" + RetrofitConstants.API_TYPE_DATA + "/" + RetrofitConstants.LANGUAGE_KEY + "/" + "summoner.json";
        return riotService.getSummonerSpells(url);
    }


}

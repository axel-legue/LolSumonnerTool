package com.legue.axel.lolsummonertool.network.retrofit;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.GsonBuilder;
import com.legue.axel.lolsummonertool.Constants;
import com.legue.axel.lolsummonertool.network.response.champion.ChampionInfoResponse;
import com.legue.axel.lolsummonertool.network.response.champion.ChampionsResponse;
import com.legue.axel.lolsummonertool.network.response.item.ItemsResponse;
import com.legue.axel.lolsummonertool.network.response.mastery.MasteryResponse;
import com.legue.axel.lolsummonertool.network.response.summonerspell.SummonerSpellsResponse;

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

    public Observable<ChampionInfoResponse> getChampionByName(String championId) {
        String url = RetrofitConstants.API_DRAGON_BASE_CDN + RetrofitConstants.API_CHAMPION_VERSION + "/" + RetrofitConstants.API_TYPE_DATA + "/" + RetrofitConstants.LANGUAGE_KEY + "/" + RetrofitConstants.API_TYPE_CHAMPION + "/" + championId + ".json";
        return riotService.getChampionByName(url);
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

    public Observable<ResponseBody> getSummonerProfil(Activity activity, String summonerName) {
        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        String prefixRegion = sharedPreferences.getString(Constants.KEY_PREFIX_SELECTED_REGION, "EUW1");
        String url = RetrofitConstants.API_HTTPS
                + prefixRegion
                + RetrofitConstants.API_RIOTGAMES_BASE
                + RetrofitConstants.API_SUMMONER_NAME_V4_BY_NAME
                + summonerName;

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put(RetrofitConstants.API_KEY_PARAMETER, "RGAPI-8f3165cb-e8e9-46e5-95bc-f4ea276cf640");

        return riotService.getSummonerProfil(url, queryParams);


    }


}

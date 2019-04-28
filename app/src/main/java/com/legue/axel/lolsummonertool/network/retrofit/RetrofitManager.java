package com.legue.axel.lolsummonertool.network.retrofit;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.GsonBuilder;
import com.legue.axel.lolsummonertool.Constants;
import com.legue.axel.lolsummonertool.database.model.mastery.Mastery;
import com.legue.axel.lolsummonertool.database.model.summoner.Summoner;
import com.legue.axel.lolsummonertool.network.response.champion.ChampionInfoResponse;
import com.legue.axel.lolsummonertool.network.response.champion.ChampionsResponse;
import com.legue.axel.lolsummonertool.network.response.item.ItemsResponse;
import com.legue.axel.lolsummonertool.network.response.mastery.MasteryResponse;
import com.legue.axel.lolsummonertool.network.response.match.MatcheResponse;
import com.legue.axel.lolsummonertool.network.response.summonerspell.SummonerSpellsResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private static final String TAG = RetrofitManager.class.getSimpleName();
    private final RiotService riotService;

    // TODO: 27/04/2019 Hide ApiKey
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

    public Observable<Summoner> getSummonerProfil(Activity activity, String summonerName) {
        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        String prefixRegion = sharedPreferences.getString(Constants.KEY_PREFIX_SELECTED_REGION, "EUW1");
        String url = RetrofitConstants.API_HTTPS
                + prefixRegion
                + RetrofitConstants.API_RIOTGAMES_BASE
                + RetrofitConstants.API_SUMMONER_NAME_V4_BY_NAME
                + summonerName;

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put(RetrofitConstants.API_KEY_PARAMETER, "RGAPI-490a4c7a-8fa4-4b14-abff-073235f570e5");

        return riotService.getSummonerProfil(url, queryParams);
    }

    /**
     * @param activity          for SharedPreferences
     * @param summonerAccountId summonerAccountId
     * @param endIndex          must be superior to beginIndex
     * @param beginIndex        must be inferior to endIndex
     * @return List<MatcheResponse>
     */
    public Observable<MatcheResponse> getSummonerMatches(Activity activity, String summonerAccountId, int endIndex, int beginIndex) {
        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        String prefixRegion = sharedPreferences.getString(Constants.KEY_PREFIX_SELECTED_REGION, "EUW1");
        String url = RetrofitConstants.API_HTTPS
                + prefixRegion
                + RetrofitConstants.API_RIOTGAMES_BASE
                + RetrofitConstants.API_MATCH_V4_BY_ENCRYTPED_ACCOUNT_ID
                + summonerAccountId;

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put(RetrofitConstants.API_KEY_END_INDEX, String.valueOf(endIndex));
        queryParams.put(RetrofitConstants.API_KEY_BEGIN_INDEX, String.valueOf(beginIndex));
        queryParams.put(RetrofitConstants.API_KEY_PARAMETER, "RGAPI-490a4c7a-8fa4-4b14-abff-073235f570e5");

        return riotService.getSummonerMatches(url, queryParams);
    }


}

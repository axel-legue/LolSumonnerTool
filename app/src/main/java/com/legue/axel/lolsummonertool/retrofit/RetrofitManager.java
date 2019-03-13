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
    // Content Delivery Network
    private static final String API_DRAGON_BASE_CDN = "https://ddragon.leagueoflegends.com/cdn/";

    private static final String API_ITEM_VERSION = "9.5.1";
    private static final String API_RUNE_VERSION = "7.23.1";
    private static final String API_MASTERY_VERSION = "7.23.1";
    private static final String API_SUMMONER_VERSION = "9.5.1";
    private static final String API_CHAMPION_VERSION = "9.5.1";
    private static final String API_PROFIL_ICON_VERSION = "9.5.1";
    private static final String API_MAP_VERSION = "9.5.1";
    private static final String API_LANGUAGE_VERSION = "9.5.1";
    private static final String API_STICKER_VERSION = "9.5.1";

    private static final String API_TYPE_DATA = "data";
    private static final String API_TYPE_IMAGE = "img";

    private static final String LANGUAGE_KEY = "en_GB";
    private static final String API_KEY = "api_key";

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
                .baseUrl(API_DRAGON_BASE_CDN)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        riotService = retrofitDataDragon.create(RiotService.class);

    }

    public Observable<ChampionsResponse> getChampions() {
        String url = API_DRAGON_BASE_CDN + API_CHAMPION_VERSION + "/" + API_TYPE_DATA + "/" + LANGUAGE_KEY + "/" + "champion.json";
        return riotService.getChampions(url);
    }


}

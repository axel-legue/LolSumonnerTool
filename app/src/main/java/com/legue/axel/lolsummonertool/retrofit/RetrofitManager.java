package com.legue.axel.lolsummonertool.retrofit;


import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private static final String TAG = RetrofitManager.class.getSimpleName();

    private static final String API_RIOT_GAME_BASE_URL = "https://na1.api.riotgames.com/";
    private static final String API_DRAGON_BASE_URL = "http://ddragon.leagueoflegends.com/";
    private static final String PAGE_KEY = "page";
    private static final String LANGUAGE_KEY = "language";
    private static final String API_KEY = "api_key";

    private final RiotDragonService riotDragonService;
    private final RiotGameService riotGameService;

    public RetrofitManager() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        ErrorInterceptor errorInterceptor = new ErrorInterceptor();

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addNetworkInterceptor(errorInterceptor)
                .build();

        Retrofit retrofitDataDragon = new Retrofit.Builder()
                .baseUrl(API_DRAGON_BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Retrofit retrofitRiotGame = new Retrofit.Builder()
                .baseUrl(API_RIOT_GAME_BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        riotDragonService = retrofitDataDragon.create(RiotDragonService.class);
        riotGameService = retrofitRiotGame.create(RiotGameService.class);
    }

    public Observable<ResponseBody> getChampions() {
        return riotDragonService.getChampions();
    }


}

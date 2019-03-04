package com.legue.axel.lolsummonertool.retrofit;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

public interface RiotGameService {

    @GET("lol/summoner/v3/summoners/by-name/{name}")
    Observable<ResponseBody> getSummonersByName();
}

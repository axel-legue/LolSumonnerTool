package com.legue.axel.lolsummonertool.retrofit;

import com.legue.axel.lolsummonertool.network.ChampionsResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RiotDragonService {

    @GET("cdn/6.24.1/data/en_US/champion.json")
    Observable<ChampionsResponse> getChampions();
}

package com.legue.axel.lolsummonertool.retrofit;

import com.legue.axel.lolsummonertool.network.ChampionsResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RiotService {

    @GET
    Observable<ChampionsResponse> getChampions(@Url String url);
}

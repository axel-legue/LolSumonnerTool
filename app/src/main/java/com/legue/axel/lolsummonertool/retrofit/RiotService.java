package com.legue.axel.lolsummonertool.retrofit;

import com.legue.axel.lolsummonertool.network.ChampionsResponse;
import com.legue.axel.lolsummonertool.network.ItemsResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RiotService {

    @GET
    Observable<ChampionsResponse> getChampions(@Url String url);

    @GET
    Observable<ItemsResponse> getItems(@Url String url);
}

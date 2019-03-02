package com.legue.axel.lolsummonertool.retrofit;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

public interface RiotService {

    @GET("cdn/6.24.1/data/en_US/champion.json")
    Observable<ResponseBody> getChampions();
}

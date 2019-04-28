package com.legue.axel.lolsummonertool.network.retrofit;

import com.legue.axel.lolsummonertool.database.model.summoner.Summoner;
import com.legue.axel.lolsummonertool.network.response.champion.ChampionInfoResponse;
import com.legue.axel.lolsummonertool.network.response.champion.ChampionsResponse;
import com.legue.axel.lolsummonertool.network.response.item.ItemsResponse;
import com.legue.axel.lolsummonertool.network.response.mastery.MasteryResponse;
import com.legue.axel.lolsummonertool.network.response.match.MatcheResponse;
import com.legue.axel.lolsummonertool.network.response.summonerspell.SummonerSpellsResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface RiotService {

    @GET
    Observable<ChampionsResponse> getChampions(@Url String url);

    @GET
    Observable<ChampionInfoResponse> getChampionByName(@Url String url);

    @GET
    Observable<ItemsResponse> getItems(@Url String url);

    @GET
    Observable<MasteryResponse> getMasteries(@Url String url);

    @GET
    Observable<SummonerSpellsResponse> getSummonerSpells(@Url String url);

    @GET
    Observable<Summoner> getSummonerProfil(@Url String url, @QueryMap Map<String, String> paramsMap);

    @GET
    Observable<MatcheResponse> getSummonerMatches(@Url String url, @QueryMap Map<String, String> paramsMap);
}

package com.legue.axel.lolsummonertool.network.retrofit

import com.legue.axel.lolsummonertool.database.model.summoner.Summoner
import com.legue.axel.lolsummonertool.network.response.champion.ChampionInfoResponse
import com.legue.axel.lolsummonertool.network.response.champion.ChampionsResponse
import com.legue.axel.lolsummonertool.network.response.item.ItemsResponse
import com.legue.axel.lolsummonertool.network.response.mastery.MasteryResponse
import com.legue.axel.lolsummonertool.network.response.match.MatchDto
import com.legue.axel.lolsummonertool.network.response.match.MatchlistDto
import com.legue.axel.lolsummonertool.network.response.summonerspell.SummonerSpellsResponse

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface RiotService {

    @GET
    fun getChampions(@Url url: String): Observable<ChampionsResponse>

    @GET
    fun getChampionByName(@Url url: String): Observable<ChampionInfoResponse>

    @GET
    fun getItems(@Url url: String): Observable<ItemsResponse>

    @GET
    fun getMasteries(@Url url: String): Observable<MasteryResponse>

    @GET
    fun getSummonerSpells(@Url url: String): Observable<SummonerSpellsResponse>

    @GET
    fun getSummonerProfil(@Url url: String, @QueryMap paramsMap: Map<String, String>): Observable<Summoner>

    @GET
    fun getSummonerMatches(@Url url: String, @QueryMap paramsMap: Map<String, String>): Observable<MatchlistDto>

    @GET
    fun getMatchInformations(@Url url: String, @QueryMap paramsMap: Map<String, String>): Observable<MatchDto>
}

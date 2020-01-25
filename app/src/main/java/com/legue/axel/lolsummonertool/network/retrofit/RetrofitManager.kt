package com.legue.axel.lolsummonertool.network.retrofit


import android.app.Activity
import android.content.Context
import com.google.gson.GsonBuilder
import com.legue.axel.lolsummonertool.Constants
import com.legue.axel.lolsummonertool.database.model.summoner.Summoner
import com.legue.axel.lolsummonertool.network.response.champion.ChampionInfoResponse
import com.legue.axel.lolsummonertool.network.response.champion.ChampionsResponse
import com.legue.axel.lolsummonertool.network.response.item.ItemsResponse
import com.legue.axel.lolsummonertool.network.response.mastery.MasteryResponse
import com.legue.axel.lolsummonertool.network.response.match.MatchDto
import com.legue.axel.lolsummonertool.network.response.match.MatchReferenceDto
import com.legue.axel.lolsummonertool.network.response.match.MatchlistDto
import com.legue.axel.lolsummonertool.network.response.summonerspell.SummonerSpellsResponse
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class RetrofitManager {
    private val riotService: RiotService

    val champions: Observable<ChampionsResponse>
        get() {
            val url = RetrofitConstants.API_DRAGON_BASE_CDN + RetrofitConstants.API_CHAMPION_VERSION + "/" + RetrofitConstants.API_TYPE_DATA + "/" + RetrofitConstants.LANGUAGE_KEY + "/" + "champion.json"
            return riotService.getChampions(url)
        }

    val items: Observable<ItemsResponse>
        get() {
            val url = RetrofitConstants.API_DRAGON_BASE_CDN + RetrofitConstants.API_CHAMPION_VERSION + "/" + RetrofitConstants.API_TYPE_DATA + "/" + RetrofitConstants.LANGUAGE_KEY + "/" + "item.json"
            return riotService.getItems(url)
        }

    val masteries: Observable<MasteryResponse>
        get() {
            val url = RetrofitConstants.API_DRAGON_BASE_CDN + RetrofitConstants.API_MASTERY_VERSION + "/" + RetrofitConstants.API_TYPE_DATA + "/" + RetrofitConstants.LANGUAGE_KEY + "/" + "mastery.json"
            return riotService.getMasteries(url)
        }

    val summonerSpells: Observable<SummonerSpellsResponse>
        get() {
            val url = RetrofitConstants.API_DRAGON_BASE_CDN + RetrofitConstants.API_MASTERY_VERSION + "/" + RetrofitConstants.API_TYPE_DATA + "/" + RetrofitConstants.LANGUAGE_KEY + "/" + "summoner.json"
            return riotService.getSummonerSpells(url)
        }

    // TODO: 27/04/2019 Hide ApiKey
    init {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val errorInterceptor = ErrorInterceptor()

        val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addNetworkInterceptor(errorInterceptor)
                .build()

        val retrofitDataDragon = Retrofit.Builder()
                .baseUrl(RetrofitConstants.API_DRAGON_BASE_CDN)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
                .build()

        riotService = retrofitDataDragon.create(RiotService::class.java)

    }

    fun getChampionByName(championId: String): Observable<ChampionInfoResponse> {
        val url = RetrofitConstants.API_DRAGON_BASE_CDN + RetrofitConstants.API_CHAMPION_VERSION + "/" + RetrofitConstants.API_TYPE_DATA + "/" + RetrofitConstants.LANGUAGE_KEY + "/" + RetrofitConstants.API_TYPE_CHAMPION + "/" + championId + ".json"
        return riotService.getChampionByName(url)
    }

    fun getSummonerProfil(activity: Activity, summonerName: String): Observable<Summoner> {
        val sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE)
        val prefixRegion = sharedPreferences.getString(Constants.KEY_PREFIX_SELECTED_REGION, "EUW1")
        val url = (RetrofitConstants.API_HTTPS
                + prefixRegion
                + RetrofitConstants.API_RIOTGAMES_BASE
                + RetrofitConstants.API_SUMMONER_NAME_V4_BY_NAME
                + summonerName)

        val queryParams = HashMap<String, String>()
        queryParams[RetrofitConstants.API_KEY_PARAMETER] = RetrofitConstants.API_KEY_VALUE_LOL_SUMMONER_TOOL

        return riotService.getSummonerProfil(url, queryParams)
    }

    /**
     * @param activity          for SharedPreferences
     * @param summonerAccountId summonerAccountId
     * @param endIndex          must be superior to beginIndex
     * @param beginIndex        must be inferior to endIndex
     * @return List<MatchlistDto>
    </MatchlistDto> */
    fun getSummonerMatches(activity: Activity, summonerAccountId: String, endIndex: Int, beginIndex: Int): Observable<MatchlistDto> {
        val sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE)
        val prefixRegion = sharedPreferences.getString(Constants.KEY_PREFIX_SELECTED_REGION, "EUW1")
        val url = (RetrofitConstants.API_HTTPS
                + prefixRegion
                + RetrofitConstants.API_RIOTGAMES_BASE
                + RetrofitConstants.API_MATCH_V4_BY_ENCRYTPED_ACCOUNT_ID
                + summonerAccountId)

        val queryParams = LinkedHashMap<String, String>()
        queryParams[RetrofitConstants.API_KEY_END_INDEX] = endIndex.toString()
        queryParams[RetrofitConstants.API_KEY_BEGIN_INDEX] = beginIndex.toString()
        queryParams[RetrofitConstants.API_KEY_PARAMETER] = RetrofitConstants.API_KEY_VALUE_LOL_SUMMONER_TOOL

        return riotService.getSummonerMatches(url, queryParams)
    }

    fun getMatchInformations(activity: Activity, matchReferenceDto: MatchReferenceDto): Observable<MatchDto> {
        val sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE)
        val prefixRegion = sharedPreferences.getString(Constants.KEY_PREFIX_SELECTED_REGION, "EUW1")
        val url = (RetrofitConstants.API_HTTPS
                + prefixRegion
                + RetrofitConstants.API_RIOTGAMES_BASE
                + RetrofitConstants.API_MATCH_V4_MATCH_ID
                + matchReferenceDto.gameId)

        val queryParams = LinkedHashMap<String, String>()
        queryParams[RetrofitConstants.API_KEY_PARAMETER] = RetrofitConstants.API_KEY_VALUE_LOL_SUMMONER_TOOL

        return riotService.getMatchInformations(url, queryParams)
    }

    companion object {

        private val TAG = RetrofitManager::class.java.simpleName
    }


    //DEVELOPPER ADRESS :
    // https://euw1.api.riotgames.com/lol/match/v4/matchlists/by-account/m4MEnTvwGD2_glWXi-_DgJCFKCnAWpFybNc64o-7DQooyA?endIndex=10&beginIndex=0&api_key=RGAPI-34f663dc-0c91-45dc-acdf-9126c91c00b2

    //Local ADRESS :
    // https://euw1.api.riotgames.com/lol/Match/v4/matchlists/by-account/m4MEnTvwGD2_glWXi-_DgJCFKCnAWpFybNc64o-7DQooyA?endIndex=10&beginIndex=0&api_key=RGAPI-34f663dc-0c91-45dc-acdf-9126c91c00b2


}

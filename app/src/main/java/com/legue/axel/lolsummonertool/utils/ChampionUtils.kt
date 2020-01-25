package com.legue.axel.lolsummonertool.utils

import com.legue.axel.lolsummonertool.AppExecutors.Companion.instance
import com.legue.axel.lolsummonertool.database.SummonerToolDatabase
import com.legue.axel.lolsummonertool.database.model.champion.Champion
import com.legue.axel.lolsummonertool.database.model.champion.ChampionImage
import com.legue.axel.lolsummonertool.database.model.champion.ChampionInfo
import com.legue.axel.lolsummonertool.database.model.champion.ChampionStats
import com.legue.axel.lolsummonertool.network.response.champion.ChampionDetailResponse
import com.legue.axel.lolsummonertool.network.response.champion.ChampionsResponse
import java.util.*

object ChampionUtils {
    private lateinit var champions: MutableList<Champion>
    private lateinit var images: MutableList<ChampionImage>
    private lateinit var championInfos: MutableList<ChampionInfo>
    private lateinit var championStatsList: MutableList<ChampionStats>
    fun insertChampionResponseInDB(championsResponse: ChampionsResponse, database: SummonerToolDatabase) {
        champions = arrayListOf()
        images = arrayListOf()
        championInfos = arrayListOf()
        championStatsList = arrayListOf()

        val championDetailsList = championsResponse.championList
        val championDetailResponseList: List<ChampionDetailResponse>
        championDetailResponseList = getChampionsDetails(championDetailsList)
        if (championDetailResponseList.isNotEmpty()) {
            for (championDetailResponse in championDetailResponseList) {
                extractChampionDetails(championDetailResponse, database)
            }
            instance.diskIO.execute {
                try {
                    database.championDao().insertAllChampion(champions)
                    database.championInfoDao().insertAllChampionInfo(championInfos)
                    database.championImageDao().insertAllChampionImage(images)
                    database.championStatDao().insertAllChampionStats(championStatsList)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun getChampionsDetails(hashMap: LinkedHashMap<String, ChampionDetailResponse>?): List<ChampionDetailResponse> {
        val championDetailResponseList: MutableList<ChampionDetailResponse> = arrayListOf()
        hashMap?.entries?.forEach { (index, value) ->
            run {
                championDetailResponseList.add(value)
            }
        }
        return championDetailResponseList
    }

    private fun extractChampionDetails(championDetailResponse: ChampionDetailResponse, database: SummonerToolDatabase) {
        val champion = Champion()
        champion.key = Integer.valueOf(championDetailResponse.key)
        champion.id = championDetailResponse.id
        champion.name = championDetailResponse.name
        champion.title = championDetailResponse.title
        champion.blurb = championDetailResponse.blurb
        val tags = championDetailResponse.tags
        if (tags.isNotEmpty()) {
            champion.tags = tags
        }
        champions.add(champion)
        val championInfo = championDetailResponse.info
        championInfo.championId = Integer.valueOf(championDetailResponse.key)
        championInfos.add(championInfo)
        val championImage = championDetailResponse.image
        championImage.championId = Integer.valueOf(championDetailResponse.key)
        images.add(championImage)
        val championStats = championDetailResponse.stats
        championStats.championId = Integer.valueOf(championDetailResponse.key)
        championStatsList.add(championStats)
    }
}
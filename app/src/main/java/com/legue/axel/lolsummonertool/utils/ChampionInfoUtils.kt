package com.legue.axel.lolsummonertool.utils

import android.util.Log
import com.legue.axel.lolsummonertool.AppExecutors.Companion.instance
import com.legue.axel.lolsummonertool.database.SummonerToolDatabase
import com.legue.axel.lolsummonertool.database.model.champion.*
import com.legue.axel.lolsummonertool.network.response.champion.ChampionInfoDetailResponse
import com.legue.axel.lolsummonertool.network.response.champion.ChampionInfoResponse
import java.util.*

object ChampionInfoUtils {
    private val TAG = ChampionInfoUtils::class.java.name
    private lateinit var champion: Champion
    private lateinit var image: ChampionImage
    private lateinit var championInfo: ChampionInfo
    private lateinit var championStats: ChampionStats
    private lateinit var championPassive: Passive
    private lateinit var spellLevelTips: MutableList<LevelTip>
    private lateinit var spellImages: MutableList<SpellImage>
    private lateinit var spells: MutableList<Spell>

    fun updateChampionResponseInDB(championInfoResponse: ChampionInfoResponse?, database: SummonerToolDatabase) {
        spells = arrayListOf()
        spellLevelTips = arrayListOf()
        spellImages = arrayListOf()
        if (championInfoResponse != null) {
            val championDetailsList = championInfoResponse.championList
            val championDetailResponseList: List<ChampionInfoDetailResponse>
            championDetailResponseList = getChampionsDetails(championDetailsList)
            if (championDetailResponseList.size == 1) {
                for (championDetailResponse in championDetailResponseList) {
                    try {
                        extractChampionDetails(championDetailResponse, database)
                    } catch (e: Exception) {
                        Log.i(TAG, "Error when parsing JSON a field must be null")
                        e.printStackTrace()
                    }
                }
                instance.diskIO.execute {
                    try {
                        database.spellImageDao().deleteAll()
                        database.championDao().updateChampion(champion)
                        database.championInfoDao().updateChampionInfo(championInfo)
                        database.championStatDao().updateChampionStats(championStats)
                        database.championImageDao().updateChampionImage(image)
                        database.passiveDao().insertPassive(championPassive)
                        database.spellDao().insertSpells(spells)
                        database.levelTipDao().insertLevelTips(spellLevelTips)
                        database.spellImageDao().insertSpellImages(spellImages)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    private fun getChampionsDetails(hashMap: LinkedHashMap<String, ChampionInfoDetailResponse>?): List<ChampionInfoDetailResponse> {
        val championDetailResponseList: MutableList<ChampionInfoDetailResponse> = arrayListOf()
        hashMap?.entries?.forEach { (index, value) ->
            run {
                championDetailResponseList.add(value)
            }
        }
        return championDetailResponseList
    }

    private fun extractChampionDetails(championDetailResponse: ChampionInfoDetailResponse, database: SummonerToolDatabase) {
        champion = Champion()
        champion.key = Integer.valueOf(championDetailResponse.key)
        champion.id = championDetailResponse.id
        champion.name = championDetailResponse.name
        champion.lore = championDetailResponse.lore
        champion.title = championDetailResponse.title
        champion.blurb = championDetailResponse.blurb
        val tags = championDetailResponse.tags
        if (tags.isNotEmpty()) {
            champion.tags = tags
        }
        championInfo = ChampionInfo()
        championInfo = championDetailResponse.info
        championInfo.championId = Integer.valueOf(championDetailResponse.key)
        image = ChampionImage()
        image = championDetailResponse.image
        image.championId = Integer.valueOf(championDetailResponse.key)
        championStats = ChampionStats()
        championStats = championDetailResponse.stats
        championStats.championId = Integer.valueOf(championDetailResponse.key)
        championPassive = Passive(
                null,
                championDetailResponse.passive.name,
                championDetailResponse.passive.description,
                championDetailResponse.passive.image.full,
                Integer.valueOf(championDetailResponse.key)
        )
        val spellResponseList = championDetailResponse.spells
        if (spellResponseList.isNotEmpty()) {
            for ((id, name, description, toolTip, _, maxRank, cooldown, cooldownBurn, cost, costBurn, _, _, costType, maxAmmo, range, rangeBurn, resource, image1) in spellResponseList) {
                val spell = Spell(
                        id,
                        name,
                        description,
                        toolTip,
                        maxRank,
                        cooldown,
                        cooldownBurn,
                        cost,
                        costBurn,
                        costType,
                        maxAmmo,
                        range,
                        rangeBurn,
                        resource,
                        Integer.valueOf(championDetailResponse.key)
                )
                spells.add(spell)
                //FIXME: see waht to do with level tip
                val levelTip = LevelTip(
                        null,
                        null,
                        null,
                        id
                )
                spellLevelTips.add(levelTip)
                val spellImage = SpellImage(
                        null,
                        image1.full,
                        image1.sprite,
                        image1.group,
                        image1.x,
                        image1.y,
                        image1.h,
                        image1.w,
                        id
                )
                spellImages.add(spellImage)
            }
        }
    }
}
package com.legue.axel.lolsummonertool.utils

import com.legue.axel.lolsummonertool.AppExecutors.Companion.instance
import com.legue.axel.lolsummonertool.database.SummonerToolDatabase
import com.legue.axel.lolsummonertool.database.model.summonerspell.SummonerSpell
import com.legue.axel.lolsummonertool.database.model.summonerspell.SummonerSpellImage
import com.legue.axel.lolsummonertool.network.response.summonerspell.SummonerSpellDetailsResponse
import com.legue.axel.lolsummonertool.network.response.summonerspell.SummonerSpellsResponse
import java.util.*

object SummonerSpellUtils {

    private lateinit var summonerSpells: MutableList<SummonerSpell>
    private lateinit var summonerSpellImages: MutableList<SummonerSpellImage>

    fun insertSummonerSpellResponseInDB(summonerSpellsResponse: SummonerSpellsResponse?, database: SummonerToolDatabase) {
        summonerSpells = arrayListOf()
        summonerSpellImages = arrayListOf()
        if (summonerSpellsResponse != null) {
            extractSummonerSpellDetails(summonerSpellsResponse.summonerSpellList)
            instance.diskIO.execute {
                try {
                    database.summonerSpellDao().insertAllSummonerSpells(summonerSpells)
                    database.summonerSpellImageDao().insertAllSummonerSpellImages(summonerSpellImages)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun extractSummonerSpellDetails(responseLinkedHashMap: LinkedHashMap<String, SummonerSpellDetailsResponse>) {
        responseLinkedHashMap.entries.forEach { (index, value) ->
            run {
                extractMastery(value)
            }
        }
    }

    private fun extractMastery(value: SummonerSpellDetailsResponse) {
        val summonerSpell = SummonerSpell(
                value.id,
                value.key,
                value.name,
                value.description,
                value.tooltip,
                value.maxrank,
                value.cooldownBurn,
                value.costBurn,
                value.summonerLevel,
                value.costType,
                value.maxammo,
                value.rangeBurn,
                value.resource,
                value.cooldown,
                value.effectBurn,
                value.cost,
                value.range,
                value.modes
        )
        val image = SummonerSpellImage(
                null,
                value.image.full,
                value.image.sprite,
                value.image.group,
                value.image.x,
                value.image.y,
                value.image.w,
                value.image.h,
                value.key
        )
        if (!summonerSpell.name!!.contains("Disabled")) {
            summonerSpells.add(summonerSpell)
            summonerSpellImages.add(image)
        }
    }
}
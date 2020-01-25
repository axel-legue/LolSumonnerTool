package com.legue.axel.lolsummonertool.utils

import com.legue.axel.lolsummonertool.AppExecutors.Companion.instance
import com.legue.axel.lolsummonertool.database.SummonerToolDatabase
import com.legue.axel.lolsummonertool.database.model.mastery.Mastery
import com.legue.axel.lolsummonertool.database.model.mastery.MasteryImage
import com.legue.axel.lolsummonertool.network.response.mastery.MasteryDetailResponse
import com.legue.axel.lolsummonertool.network.response.mastery.MasteryResponse
import java.util.*

object MasteryUtils {
    private lateinit var masteries: MutableList<Mastery>
    private lateinit var masteryImages: MutableList<MasteryImage>
    fun insertMasteryResponseInDB(masteryResponse: MasteryResponse?, database: SummonerToolDatabase) {
        masteries = arrayListOf()
        masteryImages = arrayListOf()
        if (masteryResponse != null) {
            extractMasteryDetails(masteryResponse.masteryDetailResponse)
            instance.diskIO.execute {
                try { //TODO : find a way to avoid this delete every time
                    database.masteryDao().deleteAll()
                    database.masteryImageDao().deleteAll()
                    database.masteryDao().insertAllMasteries(masteries)
                    database.masteryImageDao().insertAllMasteryImages(masteryImages)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun extractMasteryDetails(responseLinkedHashMap: LinkedHashMap<String, MasteryDetailResponse>) {
        responseLinkedHashMap.entries.forEach { (index, value) ->
            run {
                extractMastery(value)
            }
        }
    }

    private fun extractMastery(masteryDetailResponse: MasteryDetailResponse) {
        val mastery = Mastery(
                masteryDetailResponse.id,
                masteryDetailResponse.name,
                masteryDetailResponse.description,
                masteryDetailResponse.ranks,
                masteryDetailResponse.prereq
        )
        masteries.add(mastery)
        val masteryImage = MasteryImage(
                null,
                masteryDetailResponse.image.full,
                masteryDetailResponse.image.group,
                masteryDetailResponse.image.sprite,
                masteryDetailResponse.image.x,
                masteryDetailResponse.image.y,
                masteryDetailResponse.image.h,
                masteryDetailResponse.image.w,
                masteryDetailResponse.id
        )
        masteryImages.add(masteryImage)
    }
}
package com.legue.axel.lolsummonertool.network.response.champion

import com.legue.axel.lolsummonertool.database.model.champion.ChampionImage
import com.legue.axel.lolsummonertool.database.model.champion.ChampionInfo
import com.legue.axel.lolsummonertool.database.model.champion.ChampionStats

data class ChampionDetailResponse(
        var key: String,
        var id: String,
        var name: String,
        var title: String,
        var blurb: String,
        var info: ChampionInfo,
        var image: ChampionImage,
        var tags: List<String>,
        var stats: ChampionStats
)
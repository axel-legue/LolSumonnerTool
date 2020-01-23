package com.legue.axel.lolsummonertool.network.response.champion

import com.legue.axel.lolsummonertool.database.model.champion.ChampionImage
import com.legue.axel.lolsummonertool.database.model.champion.ChampionInfo
import com.legue.axel.lolsummonertool.database.model.champion.ChampionStats

data class ChampionInfoDetailResponse(
        var key: String,
        var id: String,
        var name: String,
        var title: String,
        var lore: String,
        var blurb: String,
        var partype: String,
        var info: ChampionInfo,
        var image: ChampionImage,
        var tags: List<String>,
        var stats: ChampionStats,
        var passive: PassiveResponse,
        var spells: List<SpellResponse>)

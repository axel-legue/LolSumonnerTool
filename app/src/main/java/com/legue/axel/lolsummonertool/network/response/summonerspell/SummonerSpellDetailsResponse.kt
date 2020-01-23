package com.legue.axel.lolsummonertool.network.response.summonerspell

import com.legue.axel.lolsummonertool.database.model.summonerspell.SummonerSpellImage

data class SummonerSpellDetailsResponse(
        var id: String,
        var key: Int = 0,
        var name: String,
        var description: String,
        var tooltip: String,
        var maxrank: Int = 0,
        var cooldown: List<Int>,
        var cooldownBurn: String,
        var cost: List<Int>,
        var costBurn: String,
        var effectBurn: List<Float>,
        var summonerLevel: Int = 0,
        var modes: List<String>,
        var costType: String,
        var maxammo: String,
        var range: List<Int>,
        var rangeBurn: String,
        var image: SummonerSpellImage,
        var resource: String
)
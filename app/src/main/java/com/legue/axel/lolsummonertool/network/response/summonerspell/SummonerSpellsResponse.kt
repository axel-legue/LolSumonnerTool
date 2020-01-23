package com.legue.axel.lolsummonertool.network.response.summonerspell

import com.google.gson.annotations.SerializedName
import java.util.*

data class SummonerSpellsResponse(
        var type: String,
        var version: String,
        @SerializedName("data")
        var summonerSpellList: LinkedHashMap<String, SummonerSpellDetailsResponse>
)

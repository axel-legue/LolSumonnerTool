package com.legue.axel.lolsummonertool.network.response.champion

import com.google.gson.annotations.SerializedName
import java.util.*

data class ChampionsResponse(
        var type: String? = null,
        var format: String? = null,
        var version: String? = null,
        @field:SerializedName("data")
        var championList: LinkedHashMap<String, ChampionDetailResponse>? = null)

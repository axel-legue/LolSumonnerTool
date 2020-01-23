package com.legue.axel.lolsummonertool.network.response.champion

import com.google.gson.annotations.SerializedName
import java.util.*

data class ChampionsResponse(
        var type: String?,
        var format: String?,
        var version: String?,
        @field:SerializedName("data")
        var championList: LinkedHashMap<String, ChampionDetailResponse>?)

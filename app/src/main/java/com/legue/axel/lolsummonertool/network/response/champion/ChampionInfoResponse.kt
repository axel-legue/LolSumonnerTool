package com.legue.axel.lolsummonertool.network.response.champion

import com.google.gson.annotations.SerializedName
import java.util.*

data class ChampionInfoResponse(
        var type: String?,
        var format: String?,
        var version: String?,
        @field:SerializedName("data")
        var championList: LinkedHashMap<String, ChampionInfoDetailResponse>?
)

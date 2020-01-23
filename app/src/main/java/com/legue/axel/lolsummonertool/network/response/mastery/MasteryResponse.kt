package com.legue.axel.lolsummonertool.network.response.mastery

import com.google.gson.annotations.SerializedName
import java.util.*

data class MasteryResponse(
        var type: String,
        var version: String,
        @field: SerializedName("data")
        var masteryDetailResponse: LinkedHashMap<String, MasteryDetailResponse>
)

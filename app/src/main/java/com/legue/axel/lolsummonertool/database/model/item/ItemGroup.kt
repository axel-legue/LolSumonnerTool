package com.legue.axel.lolsummonertool.database.model.item

import com.google.gson.annotations.SerializedName

data class ItemGroup(
        val id: String,
        @SerializedName("MaxGroupOwnable")
        val maxGroupOwnable: String
)
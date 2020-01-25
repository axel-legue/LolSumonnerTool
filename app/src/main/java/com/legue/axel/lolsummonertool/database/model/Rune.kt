package com.legue.axel.lolsummonertool.database.model

import com.google.gson.annotations.SerializedName

data class Rune(
        @SerializedName("isrune")
        var isRune: Boolean = false,
        var tier: Int = 0,
        var type: String? = null

)
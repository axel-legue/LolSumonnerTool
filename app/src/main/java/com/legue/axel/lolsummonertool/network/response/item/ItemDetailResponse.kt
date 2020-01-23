package com.legue.axel.lolsummonertool.network.response.item

import com.google.gson.annotations.SerializedName
import com.legue.axel.lolsummonertool.database.model.item.ItemGold
import com.legue.axel.lolsummonertool.database.model.item.ItemImage
import java.util.*

data class ItemDetailResponse(
        var name: String,
        var description: String,
        var colloq: String,
        var plaintext: String,
        var from: List<String>,
        var hideFromAll: Boolean?,
        var into: List<String>,
        @field: SerializedName("image")
        var itemImage: ItemImage,
        var gold: ItemGold,
        var tags: List<String>,
        var maps: LinkedHashMap<String, Boolean>,
        var stats: LinkedHashMap<String, Float>,
        var effect: LinkedHashMap<String, String>,
        var depth: Int)

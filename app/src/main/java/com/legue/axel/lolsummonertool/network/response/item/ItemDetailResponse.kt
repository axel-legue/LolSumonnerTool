package com.legue.axel.lolsummonertool.network.response.item

import com.google.gson.annotations.SerializedName
import com.legue.axel.lolsummonertool.database.model.item.ItemGold
import com.legue.axel.lolsummonertool.database.model.item.ItemImage
import java.util.*

data class ItemDetailResponse(
        var name: String? = null,
        var description: String? = null,
        var colloq: String? = null,
        var plaintext: String? = null,
        var from: List<String>? = null,
        var hideFromAll: Boolean?? = null,
        var into: List<String>? = null,
        @field: SerializedName("image")
        var itemImage: ItemImage? = null,
        var gold: ItemGold? = null,
        var tags: List<String>? = null,
        var maps: LinkedHashMap<String, Boolean>? = null,
        var stats: LinkedHashMap<String, Float>? = null,
        var effect: LinkedHashMap<String, String>? = null,
        var depth: Int? = null)

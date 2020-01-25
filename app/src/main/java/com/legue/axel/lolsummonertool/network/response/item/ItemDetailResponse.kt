package com.legue.axel.lolsummonertool.network.response.item

import com.google.gson.annotations.SerializedName
import com.legue.axel.lolsummonertool.database.model.item.ItemGold
import com.legue.axel.lolsummonertool.database.model.item.ItemImage
import java.util.*

data class ItemDetailResponse(
        val name: String? = null,
        val description: String? = null,
        val colloq: String? = null,
        val plaintext: String? = null,
        val from: List<String>? = null,
        val hideFromAll: Boolean?? = null,
        val into: List<String>? = null,
        @field: SerializedName("image")
        val itemImage: ItemImage? = null,
        val gold: ItemGold? = null,
        val tags: List<String>? = null,
        val maps: LinkedHashMap<String, Boolean>? = null,
        val stats: LinkedHashMap<String, Float>? = null,
        val effect: LinkedHashMap<String, String>? = null,
        val depth: Int? = null)

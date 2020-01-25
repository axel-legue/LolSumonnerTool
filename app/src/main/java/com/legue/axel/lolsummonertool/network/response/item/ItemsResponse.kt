package com.legue.axel.lolsummonertool.network.response.item

import com.google.gson.annotations.SerializedName
import com.legue.axel.lolsummonertool.database.model.item.ItemGroup
import com.legue.axel.lolsummonertool.database.model.item.ItemTree
import java.util.*

data class ItemsResponse(
        var type: String,
        var version: String,
        @field: SerializedName("data")
        var itemList: LinkedHashMap<String, ItemDetailResponse>,
        @field: SerializedName("groups")
        var groupsList: List<ItemGroup>,
        @field: SerializedName("tree")
        var itemTreeList: List<ItemTree>
)
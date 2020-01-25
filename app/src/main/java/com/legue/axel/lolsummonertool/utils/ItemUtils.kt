package com.legue.axel.lolsummonertool.utils

import com.legue.axel.lolsummonertool.AppExecutors
import com.legue.axel.lolsummonertool.database.SummonerToolDatabase
import com.legue.axel.lolsummonertool.database.model.item.*
import com.legue.axel.lolsummonertool.network.response.item.ItemDetailResponse
import com.legue.axel.lolsummonertool.network.response.item.ItemsResponse
import java.util.*

object ItemUtils {

    private lateinit var items: MutableList<Item>
    private lateinit var itemEffects: MutableList<ItemEffect>
    private lateinit var itemGolds: MutableList<ItemGold>
    private lateinit var itemImages: MutableList<ItemImage>
    private lateinit var itemMaps: MutableList<ItemMap>
    private lateinit var itemStats: MutableList<ItemStat>
    private lateinit var itemTags: MutableList<ItemTag>

    fun insertItemResponseInDB(itemsResponse: ItemsResponse?, database: SummonerToolDatabase) {
        items = arrayListOf()
        itemEffects = arrayListOf()
        itemGolds = arrayListOf()
        itemImages = arrayListOf()
        itemMaps = arrayListOf()
        itemStats = arrayListOf()
        itemTags = arrayListOf()

        if (itemsResponse != null) {
            extractItemDetails(itemsResponse.itemList)

            AppExecutors.instance.diskIO.execute {
                try {
                    database.itemDao().insertAllItem(items)
                    database.itemEffectDao().insertAllItemEffect(itemEffects)
                    database.itemGoldDao().insertAllItemGold(itemGolds)
                    database.itemImageDao().insertAllItemImage(itemImages)
                    database.itemMapDao().insertAllItemMap(itemMaps)
                    database.itemStatDao().insertAllItemStat(itemStats)
                    database.itemTagDao().insertAllItemTag(itemTags)
                } catch (e: Exception) {
                    e.printStackTrace()
                }


            }
        }
    }

    private fun extractItemDetails(itemDetailResponseList: LinkedHashMap<String, ItemDetailResponse>) {
        itemDetailResponseList.forEach { (key, value) -> extractItem(key, value) }

    }

    private fun extractItem(key: String, itemDetailResponse: ItemDetailResponse) {
        val item = Item(
                Integer.valueOf(key),
                itemDetailResponse.name,
                itemDetailResponse.description,
                itemDetailResponse.colloq,
                itemDetailResponse.plaintext,
                itemDetailResponse.depth,
                itemDetailResponse.from,
                itemDetailResponse.into
        )
        items.add(item)


        if (itemDetailResponse.gold != null) {
            val itemGold = ItemGold(null,
                    itemDetailResponse.gold.base,
                    itemDetailResponse.gold.total,
                    itemDetailResponse.gold.sell,
                    itemDetailResponse.gold.purchasable,
                    Integer.valueOf(key)
            )
            itemGolds.add(itemGold)
        }

        if (itemDetailResponse.itemImage != null) {
            val itemImage = ItemImage(null,
                    itemDetailResponse.itemImage.full,
                    itemDetailResponse.itemImage.group,
                    itemDetailResponse.itemImage.sprite,
                    itemDetailResponse.itemImage.x,
                    itemDetailResponse.itemImage.y,
                    itemDetailResponse.itemImage.h,
                    itemDetailResponse.itemImage.w,
                    Integer.valueOf(key)
            )
            itemImages.add(itemImage)
        }


        if (itemDetailResponse.tags != null && itemDetailResponse.tags.isNotEmpty()) {
            for (string in itemDetailResponse.tags) {
                val itemTag = ItemTag(null,
                        string,
                        Integer.valueOf(key)
                )
                itemTags.add(itemTag)
            }
        }

        if (itemDetailResponse.effect != null) {
            itemDetailResponse.effect.forEach { (index, value) ->
                run {
                    val itemEffect = ItemEffect(null,
                            index,
                            value,
                            Integer.valueOf(key)
                    )
                    itemEffects.add(itemEffect)
                }
            }
        }

        if (itemDetailResponse.stats != null) {
            itemDetailResponse.stats.forEach { (index, value) ->
                run {
                    val itemStat = ItemStat(null,
                            index,
                            value,
                            Integer.valueOf(key)
                    )
                    itemStats.add(itemStat)
                }
            }
        }

        if (itemDetailResponse.maps != null) {
            itemDetailResponse.maps.forEach { (index, value) ->
                run {
                    val itemMap = ItemMap(null,
                            index,
                            value,
                            Integer.valueOf(key)
                    )
                    itemMaps.add(itemMap)
                }
            }

        }

    }
}

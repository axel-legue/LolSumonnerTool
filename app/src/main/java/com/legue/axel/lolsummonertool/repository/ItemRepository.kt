package com.legue.axel.lolsummonertool.repository

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.legue.axel.lolsummonertool.database.SummonerToolDatabase
import com.legue.axel.lolsummonertool.database.model.item.Item
import com.legue.axel.lolsummonertool.database.model.item.ItemGold
import com.legue.axel.lolsummonertool.database.model.item.ItemImage
import com.legue.axel.lolsummonertool.database.model.item.ItemStat

class ItemRepository private constructor(@NonNull application: Application) : AndroidViewModel(application) {
    private val database: SummonerToolDatabase = SummonerToolDatabase.getInstance(application)

    companion object {
        private val LOCK = Any()
        private var sInstance: ItemRepository? = null

        fun getInstance(application: Application): ItemRepository {
            if (sInstance == null) {
                synchronized(LOCK) {
                    sInstance = ItemRepository(application)
                }
            }
            return sInstance!!
        }
    }

    fun getItems(): LiveData<List<Item>> = database.itemDao().getItems()
    fun getItemById(itemId: Int): LiveData<Item> = database.itemDao().getItemById(itemId)
    fun getItemImage(itemId: Int): LiveData<ItemImage> = database.itemImageDao().getItemImageByItemId(itemId)
    fun getItemGoldByItemId(itemId: Int): LiveData<ItemGold> = database.itemGoldDao().getItemGoldByItemId(itemId)
    fun getItemStatByItemId(itemId: Int): LiveData<ItemStat> = database.itemStatDao().getItemStatByItemId(itemId)


}
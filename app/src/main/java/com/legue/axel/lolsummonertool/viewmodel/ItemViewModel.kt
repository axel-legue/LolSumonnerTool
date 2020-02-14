package com.legue.axel.lolsummonertool.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

import com.legue.axel.lolsummonertool.database.SummonerToolDatabase
import com.legue.axel.lolsummonertool.database.model.item.Item
import com.legue.axel.lolsummonertool.database.model.item.ItemImage

class ItemViewModel(application: Application) : AndroidViewModel(application) {

    val items: LiveData<List<Item>>
    private val database: SummonerToolDatabase = SummonerToolDatabase.getInstance(this.getApplication())

    init {
        items = database.itemDao().getItems()
    }

    fun getItemById(itemId: Int): LiveData<Item> {
        return database.itemDao().getItemById(itemId)
    }

    fun getItemImage(itemId: Int): LiveData<ItemImage> {
        return database.itemImageDao().getItemImageByItemId(itemId)
    }
}

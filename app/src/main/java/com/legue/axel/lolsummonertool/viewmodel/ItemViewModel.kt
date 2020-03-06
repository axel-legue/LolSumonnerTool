package com.legue.axel.lolsummonertool.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

import com.legue.axel.lolsummonertool.database.SummonerToolDatabase
import com.legue.axel.lolsummonertool.database.model.item.Item
import com.legue.axel.lolsummonertool.database.model.item.ItemGold
import com.legue.axel.lolsummonertool.database.model.item.ItemImage
import com.legue.axel.lolsummonertool.repository.ItemRepository

class ItemViewModel(application: Application) : AndroidViewModel(application) {

    fun start(itemId: Int) {
        id = itemId
    }

    private val itemRepository: ItemRepository = ItemRepository.getInstance(application)
    private var id: Int = 0

    fun getItems(): LiveData<List<Item>>  = itemRepository.getItems()
    fun getItemById(): LiveData<Item> = itemRepository.getItemById(id)
    fun getItemImage(): LiveData<ItemImage> = itemRepository.getItemImage(id)
    fun getItemGoldByItemId(): LiveData<ItemGold> = itemRepository.getItemGoldByItemId(id)

}

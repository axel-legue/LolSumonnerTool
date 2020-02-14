package com.legue.axel.lolsummonertool.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

import com.legue.axel.lolsummonertool.database.SummonerToolDatabase
import com.legue.axel.lolsummonertool.database.model.item.ItemGold

class ItemGoldViewModel(application: Application) : AndroidViewModel(application) {

    private val database: SummonerToolDatabase = SummonerToolDatabase.getInstance(this.getApplication())

    fun getItemGoldByItemId(itemId: Int): LiveData<ItemGold> {
        return database.itemGoldDao().getItemGoldByItemId(itemId)
    }
}


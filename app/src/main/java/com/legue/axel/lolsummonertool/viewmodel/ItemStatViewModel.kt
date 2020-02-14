package com.legue.axel.lolsummonertool.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

import com.legue.axel.lolsummonertool.database.SummonerToolDatabase
import com.legue.axel.lolsummonertool.database.model.item.ItemStat

class ItemStatViewModel(application: Application) : AndroidViewModel(application) {

    private val database: SummonerToolDatabase = SummonerToolDatabase.getInstance(this.getApplication())

    fun getItemStatByItemId(itemId: Int): LiveData<ItemStat> {
        return database.itemStatDao().getItemStatByItemId(itemId)
    }
}


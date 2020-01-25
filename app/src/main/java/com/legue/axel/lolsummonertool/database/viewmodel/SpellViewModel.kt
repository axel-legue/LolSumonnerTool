package com.legue.axel.lolsummonertool.database.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

import com.legue.axel.lolsummonertool.database.SummonerToolDatabase
import com.legue.axel.lolsummonertool.database.model.champion.SpellImage

class SpellViewModel(application: Application) : AndroidViewModel(application) {

    private val database: SummonerToolDatabase = SummonerToolDatabase.getInstance(this.getApplication())


    fun getSpellImage(spellName: String): LiveData<SpellImage> {
        return database.spellImageDao().getSpellImageBySpellId(spellName)
    }
}


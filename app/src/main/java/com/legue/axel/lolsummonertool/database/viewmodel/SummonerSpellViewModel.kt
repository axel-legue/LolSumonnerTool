package com.legue.axel.lolsummonertool.database.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

import com.legue.axel.lolsummonertool.database.SummonerToolDatabase
import com.legue.axel.lolsummonertool.database.model.summonerspell.SummonerSpell
import com.legue.axel.lolsummonertool.database.model.summonerspell.SummonerSpellImage

class SummonerSpellViewModel(application: Application) : AndroidViewModel(application) {
    val summonerSpells: LiveData<List<SummonerSpell>>
    private val database: SummonerToolDatabase = SummonerToolDatabase.getInstance(this.getApplication())

    init {
        summonerSpells = database.summonerSpellDao().summonerSpells
    }

    fun getSummonerSpellImage(summonerSpellId: Int): LiveData<SummonerSpellImage> {
        return database.summonerSpellImageDao().getSummonerSpellImageBySummonerSpellId(summonerSpellId)
    }


}

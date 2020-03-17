package com.legue.axel.lolsummonertool.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

import com.legue.axel.lolsummonertool.database.SummonerToolDatabase
import com.legue.axel.lolsummonertool.database.model.champion.SpellImage
import com.legue.axel.lolsummonertool.network.response.champion.SpellResponse
import com.legue.axel.lolsummonertool.repository.SpellRepository

class SpellViewModel(application: Application) : AndroidViewModel(application) {
    private val spellRepository: SpellRepository = SpellRepository.getInstance(application)

    fun getSpellImage(spellName: String): LiveData<SpellImage> = spellRepository.getSpellImage(spellName)
}


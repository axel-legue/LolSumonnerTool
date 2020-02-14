package com.legue.axel.lolsummonertool.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

import com.legue.axel.lolsummonertool.database.SummonerToolDatabase
import com.legue.axel.lolsummonertool.database.model.mastery.Mastery
import com.legue.axel.lolsummonertool.database.model.mastery.MasteryImage

class MasteryViewModel(application: Application) : AndroidViewModel(application) {

    val masteries: LiveData<List<Mastery>>
    private val database: SummonerToolDatabase = SummonerToolDatabase.getInstance(this.getApplication())

    init {
        masteries = database.masteryDao().getMasteries()
    }

    fun getMasteryImage(masteryid: Int): LiveData<MasteryImage> {
        return database.masteryImageDao().getMasteryImageByMasteryId(masteryid)
    }
}
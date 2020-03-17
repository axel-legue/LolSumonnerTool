package com.legue.axel.lolsummonertool.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.legue.axel.lolsummonertool.database.model.mastery.Mastery
import com.legue.axel.lolsummonertool.database.model.mastery.MasteryImage
import com.legue.axel.lolsummonertool.repository.MasteryRepository

class MasteryViewModel(application: Application) : AndroidViewModel(application) {
    private var id: Int = 0
    private val masteryRepository: MasteryRepository = MasteryRepository.getInstance(application)

    fun start(masteryId: Int) {
        id = masteryId
    }

    fun getMasteries(): LiveData<List<Mastery>> = masteryRepository.getMasteries()
    fun getMasteryImage(): LiveData<MasteryImage> = masteryRepository.getMasteryImage(id)
}
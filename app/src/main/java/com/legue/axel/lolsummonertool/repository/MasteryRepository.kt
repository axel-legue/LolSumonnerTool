package com.legue.axel.lolsummonertool.repository

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.legue.axel.lolsummonertool.database.SummonerToolDatabase
import com.legue.axel.lolsummonertool.database.model.mastery.Mastery
import com.legue.axel.lolsummonertool.database.model.mastery.MasteryImage

class MasteryRepository private constructor(@NonNull application: Application) : AndroidViewModel(application) {
    val database: SummonerToolDatabase = SummonerToolDatabase.getInstance(application)

    companion object {
        private val LOCK = Any()
        private var sInstance: MasteryRepository? = null

        fun getInstance(application: Application): MasteryRepository {
            if (sInstance == null) {
                synchronized(LOCK) {
                    sInstance = MasteryRepository(application)
                }
            }
            return sInstance!!
        }
    }

    fun getMasteries(): LiveData<List<Mastery>> = database.masteryDao().getMasteries()
    fun getMasteryImage(masteryId: Int): LiveData<MasteryImage> = database.masteryImageDao().getMasteryImageByMasteryId(masteryId)


}
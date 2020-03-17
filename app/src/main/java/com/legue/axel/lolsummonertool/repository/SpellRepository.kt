package com.legue.axel.lolsummonertool.repository

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.legue.axel.lolsummonertool.database.SummonerToolDatabase
import com.legue.axel.lolsummonertool.database.model.champion.SpellImage

class SpellRepository private constructor(application: Application) : AndroidViewModel(application) {
    val database: SummonerToolDatabase = SummonerToolDatabase.getInstance(application)

    companion object {
        private val LOCK = Any()
        private var sInstance: SpellRepository? = null

        fun getInstance(application: Application): SpellRepository {
            if (sInstance == null) {
                synchronized(LOCK) {
                    sInstance = SpellRepository(application)
                }
            }
            return sInstance!!
        }
    }
    fun getSpellImage(spellName: String): LiveData<SpellImage> = database.spellImageDao().getSpellImageBySpellId(spellName)

}
package com.legue.axel.lolsummonertool.repository

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.legue.axel.lolsummonertool.database.SummonerToolDatabase
import com.legue.axel.lolsummonertool.database.model.champion.*

class ChampionRepository private constructor(@NonNull application: Application) : AndroidViewModel(application) {
    private val database: SummonerToolDatabase = SummonerToolDatabase.getInstance(application)

    companion object {
        private val LOCK = Any()
        private var sInstance: ChampionRepository? = null

        fun getInstance(application: Application): ChampionRepository {
            if (sInstance == null) {
                synchronized(LOCK) {
                    sInstance = ChampionRepository(application)
                }
            }
            return sInstance!!
        }
    }

    fun getChampions(): LiveData<List<Champion>> = database.championDao().champions()
    fun getChampionByKey(championKey: Int): LiveData<Champion> = database.championDao().getChampionByKey(championKey)
    fun getChampionInfo(championKey: Int): LiveData<ChampionInfo> = database.championInfoDao().getChampionInfoByChampionKeyId(championKey)
    fun getChampionStat(championKey: Int): LiveData<ChampionStats> = database.championStatDao().getChampionStatsByChampionKey(championKey)
    fun getChampionPassive(championKey: Int): LiveData<Passive> = database.passiveDao().getPassiveByChampionId(championKey)
    fun getChampionSpells(championKey: Int): LiveData<List<Spell>> = database.spellDao().getChampionSpells(championKey)
    // TODO move that to RiotImageViewModel
    fun getChampionImage(championKey: Int): LiveData<ChampionImage> = database.championImageDao().getChampionImageByChampionId(championKey)
    //    public LiveData<PassiveImage> getChampionPassiveImage(int passiveId)  =  database.passiveImageDao().getPassiveImageByPassiveId(passiveId)


}
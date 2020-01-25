package com.legue.axel.lolsummonertool.database.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

import com.legue.axel.lolsummonertool.database.SummonerToolDatabase
import com.legue.axel.lolsummonertool.database.model.champion.Champion
import com.legue.axel.lolsummonertool.database.model.champion.ChampionImage
import com.legue.axel.lolsummonertool.database.model.champion.ChampionInfo
import com.legue.axel.lolsummonertool.database.model.champion.ChampionStats
import com.legue.axel.lolsummonertool.database.model.champion.Passive
import com.legue.axel.lolsummonertool.database.model.champion.Spell

class ChampionViewModel(application: Application) : AndroidViewModel(application) {

    val champions: LiveData<List<Champion>>
    private val database: SummonerToolDatabase = SummonerToolDatabase.getInstance(this.getApplication())


    init {
        champions = database.championDao().champions()
    }

    fun getChampionByKey(championKey: Int): LiveData<Champion> {
        return database.championDao().getChampionByKey(championKey)
    }

    // TODO move that to RiotImageViewModel
    fun getChampionImage(championKey: Int): LiveData<ChampionImage> {
        return database.championImageDao().getChampionImageByChampionId(championKey)
    }

    fun getChampionInfo(championKey: Int): LiveData<ChampionInfo> {
        return database.championInfoDao().getChampionInfoByChampionKeyId(championKey)
    }

    fun getChampionStat(championKey: Int): LiveData<ChampionStats> {
        return database.championStatDao().getChampionStatsByChampionKey(championKey)
    }

    fun getChampionPassive(championKey: Int): LiveData<Passive> {
        return database.passiveDao().getPassiveByChampionId(championKey)
    }

    //    public LiveData<PassiveImage> getChampionPassiveImage(int passiveId) {
    //        return database.passiveImageDao().getPassiveImageByPassiveId(passiveId);
    //    }


    fun getChampionSpells(championKey: Int): LiveData<List<Spell>> {
        return database.spellDao().getChampionSpells(championKey)
    }


}

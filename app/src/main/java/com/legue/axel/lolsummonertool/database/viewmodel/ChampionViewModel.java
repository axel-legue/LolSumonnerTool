package com.legue.axel.lolsummonertool.database.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.legue.axel.lolsummonertool.database.SummonerToolDatabase;
import com.legue.axel.lolsummonertool.database.model.champion.Champion;
import com.legue.axel.lolsummonertool.database.model.champion.ChampionImage;
import com.legue.axel.lolsummonertool.database.model.champion.ChampionInfo;
import com.legue.axel.lolsummonertool.database.model.champion.ChampionStats;
import com.legue.axel.lolsummonertool.database.model.champion.Passive;
import com.legue.axel.lolsummonertool.database.model.champion.Spell;

import java.util.List;

public class ChampionViewModel extends AndroidViewModel {

    private LiveData<List<Champion>> champions;
    private SummonerToolDatabase database;


    public ChampionViewModel(@NonNull Application application) {
        super(application);
        database = SummonerToolDatabase.Companion.getInstance(this.getApplication());
        champions = database.championDao().champions();
    }

    public LiveData<List<Champion>> getChampions() {
        return champions;
    }

    public LiveData<Champion> getChampionByKey(int championKey) {
        return database.championDao().getChampionByKey(championKey);
    }

    // TODO move that to RiotImageViewModel
    public LiveData<ChampionImage> getChampionImage(int championKey) {
        return database.championImageDao().getChampionImageByChampionId(championKey);
    }

    public LiveData<ChampionInfo> getChampionInfo(int championKey) {
        return database.championInfoDao().getChampionInfoByChampionKeyId(championKey);
    }

    public LiveData<ChampionStats> getChampionStat(int championKey) {
        return database.championStatDao().getChampionStatsByChampionKey(championKey);
    }

    public LiveData<Passive> getChampionPassive(int championKey) {
        return database.passiveDao().getPassiveByChampionId(championKey);
    }

//    public LiveData<PassiveImage> getChampionPassiveImage(int passiveId) {
//        return database.passiveImageDao().getPassiveImageByPassiveId(passiveId);
//    }


    public LiveData<List<Spell>> getChampionSpells(int championKey) {
        return database.spellDao().getChampionSpells(championKey);
    }


}

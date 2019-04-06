package com.legue.axel.lolsummonertool.database.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.legue.axel.lolsummonertool.database.SummonerToolDatabase;
import com.legue.axel.lolsummonertool.database.model.champion.Champion;
import com.legue.axel.lolsummonertool.database.model.champion.ChampionImage;
import com.legue.axel.lolsummonertool.database.model.champion.ChampionInfo;
import com.legue.axel.lolsummonertool.database.model.champion.ChampionStats;

import java.util.List;

public class ChampionViewModel extends AndroidViewModel {

    private LiveData<List<Champion>> champions;
    private SummonerToolDatabase database;


    public ChampionViewModel(@NonNull Application application) {
        super(application);
        database = SummonerToolDatabase.getInstance(this.getApplication());
        champions = database.championDao().getChampions();
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
}

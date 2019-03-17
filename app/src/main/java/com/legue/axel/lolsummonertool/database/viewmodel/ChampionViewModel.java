package com.legue.axel.lolsummonertool.database.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.legue.axel.lolsummonertool.database.SummonerToolDatabase;
import com.legue.axel.lolsummonertool.database.model.champion.ChampionImage;
import com.legue.axel.lolsummonertool.database.model.champion.Champion;

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

    // TODO move that to RiotImageViewModel
    public LiveData<ChampionImage> getChampionImage(int championId) {
        return database.championImageDao().getChampionImageByChampionId(championId);
    }
}

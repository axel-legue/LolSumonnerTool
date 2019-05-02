package com.legue.axel.lolsummonertool.database.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.legue.axel.lolsummonertool.database.SummonerToolDatabase;
import com.legue.axel.lolsummonertool.database.model.summonerspell.SummonerSpell;
import com.legue.axel.lolsummonertool.database.model.summonerspell.SummonerSpellImage;

import java.util.List;

public class SummonerSpellViewModel extends AndroidViewModel {
    private LiveData<List<SummonerSpell>> summonerSpells;
    private SummonerToolDatabase database;

    public SummonerSpellViewModel(@NonNull Application application) {
        super(application);
        database = SummonerToolDatabase.getInstance(this.getApplication());
        summonerSpells = database.summonerSpellDao().getSummonerSpells();
    }

    public LiveData<List<SummonerSpell>> getSummonerSpells() {
        return summonerSpells;
    }

    public LiveData<SummonerSpellImage> getSummonerSpellImage(int summonerSpellId) {
        return database.summonerSpellImageDao().getSummonerSpellImageBySummonerSpellId(summonerSpellId);
    }


}

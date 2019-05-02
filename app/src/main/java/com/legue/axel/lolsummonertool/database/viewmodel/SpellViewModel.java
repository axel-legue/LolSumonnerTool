package com.legue.axel.lolsummonertool.database.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.legue.axel.lolsummonertool.database.SummonerToolDatabase;
import com.legue.axel.lolsummonertool.database.model.champion.SpellImage;

public class SpellViewModel extends AndroidViewModel {

    private SummonerToolDatabase database;


    public SpellViewModel(@NonNull Application application) {
        super(application);
        database = SummonerToolDatabase.getInstance(this.getApplication());
    }

    public LiveData<SpellImage> getSpellImage(String spellName) {
        return database.spellImageDao().getSpellImageBySpellId(spellName);
    }
}

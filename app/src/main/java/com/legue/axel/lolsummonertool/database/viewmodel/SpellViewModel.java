package com.legue.axel.lolsummonertool.database.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

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

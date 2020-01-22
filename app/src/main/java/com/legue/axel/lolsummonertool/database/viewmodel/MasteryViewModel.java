package com.legue.axel.lolsummonertool.database.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.legue.axel.lolsummonertool.database.SummonerToolDatabase;
import com.legue.axel.lolsummonertool.database.model.mastery.Mastery;
import com.legue.axel.lolsummonertool.database.model.mastery.MasteryImage;

import java.util.List;

public class MasteryViewModel extends AndroidViewModel {

    private LiveData<List<Mastery>> masteries;
    private SummonerToolDatabase database;

    public MasteryViewModel(@NonNull Application application) {
        super(application);
        database = SummonerToolDatabase.Companion.getInstance(this.getApplication());
        masteries = database.masteryDao().getMasteries();
    }

    public LiveData<List<Mastery>> getMasteries() {
        return masteries;
    }

    public LiveData<MasteryImage> getMasteryImage(int masteryid) {
        return database.masteryImageDao().getMasteryImageByMasteryId(masteryid);
    }
}
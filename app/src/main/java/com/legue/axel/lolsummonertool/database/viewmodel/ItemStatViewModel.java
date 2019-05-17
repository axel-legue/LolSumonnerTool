package com.legue.axel.lolsummonertool.database.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.legue.axel.lolsummonertool.database.SummonerToolDatabase;
import com.legue.axel.lolsummonertool.database.model.item.ItemStat;

public class ItemStatViewModel extends AndroidViewModel {

    private SummonerToolDatabase database;

    public ItemStatViewModel(@NonNull Application application) {
        super(application);
        database = SummonerToolDatabase.getInstance(this.getApplication());
    }

    public LiveData<ItemStat> getItemStatByItemId(int itemId) {
        return database.itemStatDao().getItemStatByItemId(itemId);
    }
}

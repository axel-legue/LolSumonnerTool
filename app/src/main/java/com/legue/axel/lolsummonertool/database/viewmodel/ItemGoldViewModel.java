package com.legue.axel.lolsummonertool.database.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.legue.axel.lolsummonertool.database.SummonerToolDatabase;
import com.legue.axel.lolsummonertool.database.model.item.ItemGold;

public class ItemGoldViewModel extends AndroidViewModel {

    private SummonerToolDatabase database;

    public ItemGoldViewModel(@NonNull Application application) {
        super(application);
        database = SummonerToolDatabase.getInstance(this.getApplication());
    }

    public LiveData<ItemGold> getItemGoldByItemId(int itemId) {
        return database.itemGoldDao().getItemGoldByItemId(itemId);
    }
}

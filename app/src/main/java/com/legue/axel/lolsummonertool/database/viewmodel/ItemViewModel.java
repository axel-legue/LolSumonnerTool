package com.legue.axel.lolsummonertool.database.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.legue.axel.lolsummonertool.database.SummonerToolDatabase;
import com.legue.axel.lolsummonertool.database.model.item.Item;
import com.legue.axel.lolsummonertool.database.model.item.ItemImage;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {

    private LiveData<List<Item>> items;
    private SummonerToolDatabase database;

    public ItemViewModel(@NonNull Application application) {
        super(application);
        database = SummonerToolDatabase.getInstance(this.getApplication());
        items = database.itemDao().getItems();
    }

    public LiveData<List<Item>> getItems() {
        return items;
    }

    public LiveData<Item> getItemById(int itemId) {
        return database.itemDao().getItemById(itemId);
    }

    public LiveData<ItemImage> getItemImage(int itemId) {
        return database.itemImageDao().getItemImageByItemId(itemId);
    }
}

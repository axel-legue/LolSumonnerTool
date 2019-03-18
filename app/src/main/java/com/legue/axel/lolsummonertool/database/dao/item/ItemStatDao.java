package com.legue.axel.lolsummonertool.database.dao.item;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.legue.axel.lolsummonertool.database.model.item.ItemStat;

import java.util.List;

@Dao
public interface ItemStatDao {

    @Query("SELECT * FROM item_stats ORDER BY id")
    LiveData<List<ItemStat>> getItemStats();

    @Query("SELECT * FROM item_stats WHERE id = :itemStatId")
    LiveData<ItemStat> getItemStatById(int itemStatId);

    @Insert
    void insertItemStat(ItemStat itemStat);

    @Insert
    void insertAllItemStat(List<ItemStat> itemStats);

    @Delete
    void deleteItemStat(ItemStat itemStat);

    @Query("DELETE FROM item_stats")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateItemStat(ItemStat itemStat);
}

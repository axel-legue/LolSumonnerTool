package com.legue.axel.lolsummonertool.database.dao.item;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.legue.axel.lolsummonertool.database.model.item.ItemStat;

import java.util.List;

@Dao
public interface ItemStatDao {

    @Query("SELECT * FROM item_stats ORDER BY id")
    LiveData<List<ItemStat>> getItemStats();

    @Query("SELECT * FROM item_stats WHERE id = :itemStatId")
    LiveData<ItemStat> getItemStatById(int itemStatId);

    @Query("SELECT * FROM item_stats WHERE itemId = :itemId")
    LiveData<ItemStat> getItemStatByItemId(int itemId);

    @Insert
    void insertItemStat(ItemStat itemStat);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllItemStat(List<ItemStat> itemStats);

    @Delete
    void deleteItemStat(ItemStat itemStat);

    @Query("DELETE FROM item_stats")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateItemStat(ItemStat itemStat);
}

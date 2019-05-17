package com.legue.axel.lolsummonertool.database.dao.item;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.legue.axel.lolsummonertool.database.model.item.ItemGold;

import java.util.List;

@Dao
public interface ItemGoldDao {

    @Query("SELECT * FROM item_golds ORDER BY id")
    LiveData<List<ItemGold>> getItemGolds();

    @Query("SELECT * FROM item_golds WHERE id = :itemGoldId")
    LiveData<ItemGold> getItemGoldById(int itemGoldId);

    @Query("SELECT * FROM item_golds WHERE itemId = :itemId")
    LiveData<ItemGold> getItemGoldByItemId(int itemId);

    @Insert
    void insertItemGold(ItemGold itemGold);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllItemGold(List<ItemGold> itemGolds);

    @Delete
    void deleteItemGold(ItemGold itemGold);

    @Query("DELETE FROM item_golds")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateItemGold(ItemGold itemGold);
}

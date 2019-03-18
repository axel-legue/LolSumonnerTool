package com.legue.axel.lolsummonertool.database.dao.item;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.legue.axel.lolsummonertool.database.model.item.ItemGold;

import java.util.List;

@Dao
public interface ItemGoldDao {

    @Query("SELECT * FROM item_golds ORDER BY id")
    LiveData<List<ItemGold>> getItemGolds();

    @Query("SELECT * FROM item_golds WHERE id = :itemGoldId")
    LiveData<ItemGold> getItemGoldById(int itemGoldId);

    @Insert
    void insertItemGold(ItemGold itemGold);

    @Insert
    void insertAllItemGold(List<ItemGold> itemGolds);

    @Delete
    void deleteItemGold(ItemGold itemGold);

    @Query("DELETE FROM item_golds")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateItemGold(ItemGold itemGold);
}

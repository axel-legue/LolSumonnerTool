package com.legue.axel.lolsummonertool.database.dao.item;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.legue.axel.lolsummonertool.database.model.item.ItemEffect;

import java.util.List;

@Dao
public interface ItemEffectDao {
    @Query("SELECT * FROM item_effects ORDER BY id")
    LiveData<List<ItemEffect>> getItemEffects();

    @Query("SELECT * FROM item_effects WHERE id = :itemEffectId")
    LiveData<ItemEffect> getItemEffectById(int itemEffectId);

    @Insert
    void insertItemEffect(ItemEffect itemEffect);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllItemEffect(List<ItemEffect> itemEffects);

    @Delete
    void deleteItemEffect(ItemEffect itemEffect);

    @Query("DELETE FROM item_effects")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateItemEffect(ItemEffect itemEffect);
}

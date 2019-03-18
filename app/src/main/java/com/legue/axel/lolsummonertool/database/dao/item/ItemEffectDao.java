package com.legue.axel.lolsummonertool.database.dao.item;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

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

    @Insert
    void insertAllItemEffect(List<ItemEffect> itemEffects);

    @Delete
    void deleteItemEffect(ItemEffect itemEffect);

    @Query("DELETE FROM item_effects")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateItemEffect(ItemEffect itemEffect);
}

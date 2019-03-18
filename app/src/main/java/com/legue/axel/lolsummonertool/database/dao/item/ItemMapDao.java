package com.legue.axel.lolsummonertool.database.dao.item;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.legue.axel.lolsummonertool.database.model.item.ItemMap;

import java.util.List;

@Dao
public interface ItemMapDao {

    @Query("SELECT * FROM item_maps ORDER BY id")
    LiveData<List<ItemMap>> getItemMaps();

    @Query("SELECT * FROM item_maps WHERE id = :itemMapId")
    LiveData<ItemMap> getItemMapById(int itemMapId);

    @Insert
    void insertItemMap(ItemMap itemMap);

    @Insert
    void insertAllItemMap(List<ItemMap> itemMaps);

    @Delete
    void deleteItemMap(ItemMap itemMap);

    @Query("DELETE FROM item_maps")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateItemMap(ItemMap itemMap);
}

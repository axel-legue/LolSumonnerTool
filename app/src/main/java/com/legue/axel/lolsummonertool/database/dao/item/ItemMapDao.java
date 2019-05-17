package com.legue.axel.lolsummonertool.database.dao.item;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllItemMap(List<ItemMap> itemMaps);

    @Delete
    void deleteItemMap(ItemMap itemMap);

    @Query("DELETE FROM item_maps")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateItemMap(ItemMap itemMap);
}

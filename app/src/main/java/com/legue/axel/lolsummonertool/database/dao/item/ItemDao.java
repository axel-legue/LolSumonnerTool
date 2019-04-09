package com.legue.axel.lolsummonertool.database.dao.item;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.legue.axel.lolsummonertool.database.model.item.Item;

import java.util.List;

@Dao
public interface ItemDao {
    @Query("SELECT * FROM items ORDER BY name")
    LiveData<List<Item>> getItems();

    @Query("SELECT * FROM items WHERE id = :itemId")
    LiveData<Item> getItemById(int itemId);

    @Insert
    void insertItem(Item item);

    @Insert
    void insertAllItem(List<Item> items);

    @Delete
    void deleteItem(Item champion);

    @Query("DELETE FROM items")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateItem(Item item);
}

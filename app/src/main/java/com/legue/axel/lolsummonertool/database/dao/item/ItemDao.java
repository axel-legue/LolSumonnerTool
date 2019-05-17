package com.legue.axel.lolsummonertool.database.dao.item;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllItem(List<Item> items);

    @Delete
    void deleteItem(Item champion);

    @Query("DELETE FROM items")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateItem(Item item);
}

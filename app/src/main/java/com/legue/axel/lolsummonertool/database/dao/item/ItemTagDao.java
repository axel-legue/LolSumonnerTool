package com.legue.axel.lolsummonertool.database.dao.item;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.legue.axel.lolsummonertool.database.model.item.ItemTag;

import java.util.List;

@Dao
public interface ItemTagDao {

    @Query("SELECT * FROM item_tags ORDER BY id")
    LiveData<List<ItemTag>> getItemTags();

    @Query("SELECT * FROM item_tags WHERE id = :itemTagId")
    LiveData<ItemTag> getItemTagById(int itemTagId);

    @Insert
    void insertItemTag(ItemTag itemTag);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllItemTag(List<ItemTag> itemTags);

    @Delete
    void deleteItemTag(ItemTag itemTag);

    @Query("DELETE FROM item_tags")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateItemTag(ItemTag itemTag);
}

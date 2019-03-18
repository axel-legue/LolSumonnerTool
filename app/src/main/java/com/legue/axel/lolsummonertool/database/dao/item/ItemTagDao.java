package com.legue.axel.lolsummonertool.database.dao.item;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

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

    @Insert
    void insertAllItemTag(List<ItemTag> itemTags);

    @Delete
    void deleteItemTag(ItemTag itemTag);

    @Query("DELETE FROM item_tags")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateItemTag(ItemTag itemTag);
}

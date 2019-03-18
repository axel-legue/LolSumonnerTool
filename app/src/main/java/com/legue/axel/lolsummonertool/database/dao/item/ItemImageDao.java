package com.legue.axel.lolsummonertool.database.dao.item;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.legue.axel.lolsummonertool.database.model.item.ItemImage;

import java.util.List;

@Dao
public interface ItemImageDao {

    @Query("SELECT * FROM item_images ORDER BY id")
    LiveData<List<ItemImage>> getItemImages();

    @Query("SELECT * FROM item_images WHERE id = :itemImageId")
    LiveData<ItemImage> getItemImageById(int itemImageId);

    @Insert
    void insertItemImage(ItemImage itemImage);

    @Insert
    void insertAllItemImage(List<ItemImage> itemImages);

    @Delete
    void deleteItemImage(ItemImage itemImage);

    @Query("DELETE FROM item_images")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateItemImage(ItemImage itemImage);
}

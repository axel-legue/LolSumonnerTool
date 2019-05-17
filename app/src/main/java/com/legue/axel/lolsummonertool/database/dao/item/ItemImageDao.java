package com.legue.axel.lolsummonertool.database.dao.item;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.legue.axel.lolsummonertool.database.model.item.ItemImage;

import java.util.List;

@Dao
public interface ItemImageDao {

    @Query("SELECT * FROM item_images ORDER BY id")
    LiveData<List<ItemImage>> getItemImages();

    @Query("SELECT * FROM item_images WHERE id = :itemImageId")
    LiveData<ItemImage> getItemImageById(int itemImageId);

    @Query("SELECT * FROM item_images WHERE itemId = :itemId")
    LiveData<ItemImage> getItemImageByItemId(int itemId);

    @Insert
    void insertItemImage(ItemImage itemImage);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllItemImage(List<ItemImage> itemImages);

    @Delete
    void deleteItemImage(ItemImage itemImage);

    @Query("DELETE FROM item_images")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateItemImage(ItemImage itemImage);
}

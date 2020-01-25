package com.legue.axel.lolsummonertool.database.dao.item

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

import com.legue.axel.lolsummonertool.database.model.item.ItemImage

@Dao
interface ItemImageDao {

    @Query("SELECT * FROM item_images ORDER BY id")
    fun getItemImages(): LiveData<List<ItemImage>>

    @Query("SELECT * FROM item_images WHERE id = :itemImageId")
    fun getItemImageById(itemImageId: Int): LiveData<ItemImage>

    @Query("SELECT * FROM item_images WHERE itemId = :itemId")
    fun getItemImageByItemId(itemId: Int): LiveData<ItemImage>

    @Insert
    fun insertItemImage(itemImage: ItemImage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllItemImage(itemImages: List<ItemImage>)

    @Delete
    fun deleteItemImage(itemImage: ItemImage)

    @Query("DELETE FROM item_images")
    fun deleteAll()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateItemImage(itemImage: ItemImage)
}

package com.legue.axel.lolsummonertool.database.dao.item

import androidx.lifecycle.LiveData
import androidx.room.*
import com.legue.axel.lolsummonertool.database.model.item.ItemTag

@Dao
interface ItemTagDao {

    @Query("SELECT * FROM item_tags ORDER BY id")
    fun getItemTags(): LiveData<List<ItemTag>>

    @Query("SELECT * FROM item_tags WHERE id = :itemTagId")
    fun getItemTagById(itemTagId: Int): LiveData<ItemTag>

    @Insert
    fun insertItemTag(itemTag: ItemTag)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllItemTag(itemTags: List<ItemTag>)

    @Delete
    fun deleteItemTag(itemTag: ItemTag)

    @Query("DELETE FROM item_tags")
    fun deleteAll()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateItemTag(itemTag: ItemTag)
}

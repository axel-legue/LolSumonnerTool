package com.legue.axel.lolsummonertool.database.dao.item

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

import com.legue.axel.lolsummonertool.database.model.item.Item

@Dao
interface ItemDao {
    @Query("SELECT * FROM items ORDER BY name")
    fun getItems(): LiveData<List<Item>>

    @Query("SELECT * FROM items WHERE id = :itemId")
    fun getItemById(itemId: Int): LiveData<Item>

    @Insert
    fun insertItem(item: Item)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllItem(items: List<Item>)

    @Delete
    fun deleteItem(champion: Item)

    @Query("DELETE FROM items")
    fun deleteAll()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateItem(item: Item)
}

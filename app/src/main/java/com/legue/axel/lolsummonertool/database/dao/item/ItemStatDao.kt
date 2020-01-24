package com.legue.axel.lolsummonertool.database.dao.item

import androidx.lifecycle.LiveData
import androidx.room.*
import com.legue.axel.lolsummonertool.database.model.item.ItemStat

@Dao
interface ItemStatDao {

    @Query("SELECT * FROM item_stats ORDER BY id")
    fun getItemStats(): LiveData<List<ItemStat>>

    @Query("SELECT * FROM item_stats WHERE id = :itemStatId")
    fun getItemStatById(itemStatId: Int): LiveData<ItemStat>

    @Query("SELECT * FROM item_stats WHERE itemId = :itemId")
    fun getItemStatByItemId(itemId: Int): LiveData<ItemStat>

    @Insert
    fun insertItemStat(itemStat: ItemStat)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllItemStat(itemStats: List<ItemStat>)

    @Delete
    fun deleteItemStat(itemStat: ItemStat)

    @Query("DELETE FROM item_stats")
    fun deleteAll()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateItemStat(itemStat: ItemStat)
}

package com.legue.axel.lolsummonertool.database.dao.item

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

import com.legue.axel.lolsummonertool.database.model.item.ItemGold

@Dao
interface ItemGoldDao {

    @Query("SELECT * FROM item_golds ORDER BY id")
    fun getItemGolds(): LiveData<List<ItemGold>>

    @Query("SELECT * FROM item_golds WHERE id = :itemGoldId")
    fun getItemGoldById(itemGoldId: Int): LiveData<ItemGold>

    @Query("SELECT * FROM item_golds WHERE itemId = :itemId")
    fun getItemGoldByItemId(itemId: Int): LiveData<ItemGold>

    @Insert
    fun insertItemGold(itemGold: ItemGold)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllItemGold(itemGolds: List<ItemGold>)

    @Delete
    fun deleteItemGold(itemGold: ItemGold)

    @Query("DELETE FROM item_golds")
    fun deleteAll()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateItemGold(itemGold: ItemGold)
}
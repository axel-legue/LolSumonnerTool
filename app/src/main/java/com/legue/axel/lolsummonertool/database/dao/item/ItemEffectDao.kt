package com.legue.axel.lolsummonertool.database.dao.item

import androidx.lifecycle.LiveData
import androidx.room.*
import com.legue.axel.lolsummonertool.database.model.item.ItemEffect

@Dao
interface ItemEffectDao {
    @Query("SELECT * FROM item_effects ORDER BY id")
    fun getItemEffects(): LiveData<List<ItemEffect>>

    @Query("SELECT * FROM item_effects WHERE id = :itemEffectId")
    fun getItemEffectById(itemEffectId: Int): LiveData<ItemEffect>

    @Insert
    fun insertItemEffect(itemEffect: ItemEffect)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllItemEffect(itemEffects: List<ItemEffect>)

    @Delete
    fun deleteItemEffect(itemEffect: ItemEffect)

    @Query("DELETE FROM item_effects")
    fun deleteAll()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateItemEffect(itemEffect: ItemEffect)
}

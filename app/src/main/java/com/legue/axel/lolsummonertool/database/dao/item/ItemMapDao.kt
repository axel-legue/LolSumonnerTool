package com.legue.axel.lolsummonertool.database.dao.item

import androidx.lifecycle.LiveData
import androidx.room.*
import com.legue.axel.lolsummonertool.database.model.item.ItemMap

@Dao
interface ItemMapDao {

    @Query("SELECT * FROM item_maps ORDER BY id")
    fun getItemMaps(): LiveData<List<ItemMap>>

    @Query("SELECT * FROM item_maps WHERE id = :itemMapId")
    fun getItemMapById(itemMapId: Int): LiveData<ItemMap>

    @Insert
    fun insertItemMap(itemMap: ItemMap)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllItemMap(itemMaps: List<ItemMap>)

    @Delete
    fun deleteItemMap(itemMap: ItemMap)

    @Query("DELETE FROM item_maps")
    fun deleteAll()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateItemMap(itemMap: ItemMap)
}

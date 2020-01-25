package com.legue.axel.lolsummonertool.database.dao.champion

import androidx.lifecycle.LiveData
import androidx.room.*
import com.legue.axel.lolsummonertool.database.model.champion.Skin

@Dao
interface SkinDao {
    @Query("SELECT * FROM skins ORDER BY id")
    fun getSkins(): LiveData<List<Skin>>

    @Query("SELECT * FROM skins WHERE id = :skinId")
    fun getSkinById(skinId: Int): LiveData<Skin>

    @Insert
    fun insertSkin(skin: Skin)

    @Delete
    fun deleteSkin(skin: Skin)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateSkin(skin: Skin)
}
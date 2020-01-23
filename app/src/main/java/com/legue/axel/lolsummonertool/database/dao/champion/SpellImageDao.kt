package com.legue.axel.lolsummonertool.database.dao.champion

import androidx.lifecycle.LiveData
import androidx.room.*
import com.legue.axel.lolsummonertool.database.model.champion.SpellImage

@Dao
interface SpellImageDao {
    @Query("SELECT * FROM spell_images ORDER BY id")
    fun getSpellImages(): LiveData<List<SpellImage>>

    @Query("SELECT * FROM spell_images WHERE id = :spellImageId")
    fun getSpellImageById(spellImageId: Int): LiveData<SpellImage>

    @Query("SELECT * FROM spell_images WHERE spellId = :spellId")
    fun getSpellImageBySpellId(spellId: String): LiveData<SpellImage>

    @Insert
    fun insertSpellImage(spellImage: SpellImage)

    @Insert
    fun insertSpellImages(spellImages: List<SpellImage>)

    @Delete
    fun deleteSpellImage(spellImage: SpellImage)

    @Query("DELETE FROM spell_images")
    fun deleteAll()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateSpellImage(spellImage: SpellImage)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateSpellImages(spellImages: List<SpellImage>)
}
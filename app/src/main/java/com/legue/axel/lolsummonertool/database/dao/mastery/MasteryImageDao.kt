package com.legue.axel.lolsummonertool.database.dao.mastery

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

import com.legue.axel.lolsummonertool.database.model.mastery.MasteryImage

@Dao
interface MasteryImageDao {

    @Query("SELECT * FROM mastery_images ORDER BY id")
    fun getMasteryImages(): LiveData<List<MasteryImage>>

    @Query("SELECT * FROM mastery_images WHERE id = :masteryId")
    fun getMasteryImageById(masteryId: Int): LiveData<MasteryImage>

    @Query("SELECT * FROM mastery_images WHERE masteryId = :masteryId")
    fun getMasteryImageByMasteryId(masteryId: Int): LiveData<MasteryImage>

    @Insert
    fun insertMasteryImage(masteryImage: MasteryImage)

    @Insert
    fun insertAllMasteryImages(masteryImages: List<MasteryImage>)

    @Delete
    fun deleteMasteryImage(masteryImage: MasteryImage)

    @Query("DELETE FROM mastery_images")
    fun deleteAll()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateMasteryImage(masteryImage: MasteryImage)
}

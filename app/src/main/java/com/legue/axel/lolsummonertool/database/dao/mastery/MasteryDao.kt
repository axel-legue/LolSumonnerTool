package com.legue.axel.lolsummonertool.database.dao.mastery

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

import com.legue.axel.lolsummonertool.database.model.mastery.Mastery

@Dao
interface MasteryDao {

    @Query("SELECT * FROM masteries ORDER BY id")
    fun getMasteries(): LiveData<List<Mastery>>

    @Query("SELECT * FROM masteries WHERE id = :masteryId")
    fun getMasteryById(masteryId: Int): LiveData<Mastery>

    @Insert
    fun insertMastery(mastery: Mastery)

    @Insert
    fun insertAllMasteries(masteries: List<Mastery>)

    @Delete
    fun deleteMastery(mastery: Mastery)

    @Query("DELETE FROM masteries")
    fun deleteAll()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateMastery(mastery: Mastery)
}

package com.legue.axel.lolsummonertool.database.dao.champion

import androidx.lifecycle.LiveData
import androidx.room.*
import com.legue.axel.lolsummonertool.database.model.champion.Recommended

@Dao
interface RecommendedDao {
    @Query("SELECT * FROM recommended ORDER BY id")
    fun getRecommended(): LiveData<List<Recommended>>

    @Query("SELECT * FROM recommended WHERE id = :recommendedId")
    fun getRecommendedById(recommendedId: Int): LiveData<Recommended>

    @Insert
    fun insertRecommended(recommended: Recommended)

    @Delete
    fun deleteRecommended(recommended: Recommended)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateRecommended(recommended: Recommended)
}
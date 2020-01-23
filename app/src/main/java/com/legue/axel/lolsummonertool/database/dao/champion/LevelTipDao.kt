package com.legue.axel.lolsummonertool.database.dao.champion

import androidx.lifecycle.LiveData
import androidx.room.*
import com.legue.axel.lolsummonertool.database.model.champion.LevelTip

@Dao
interface LevelTipDao {
    @Query("SELECT * FROM level_tip ORDER BY id")
    fun getLevelTips(): LiveData<List<LevelTip>>

    @Query("SELECT * FROM level_tip WHERE id = :levelTipId")
    fun getLevelTipById(levelTipId: Int): LiveData<LevelTip>

    @Insert
    fun insertLevelTip(levelTip: LevelTip)

    @Insert
    fun insertLevelTips(levelTips: List<LevelTip>)

    @Query("DELETE FROM level_tip")
    fun deleteAll()

    @Delete
    fun deleteLevelTip(levelTip: LevelTip)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateLevelTip(levelTip: LevelTip)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateLevelTips(levelTips: List<LevelTip>)
}
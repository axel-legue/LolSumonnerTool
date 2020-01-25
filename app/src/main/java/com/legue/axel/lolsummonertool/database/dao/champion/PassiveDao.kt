package com.legue.axel.lolsummonertool.database.dao.champion

import androidx.lifecycle.LiveData
import androidx.room.*
import com.legue.axel.lolsummonertool.database.model.champion.Passive

@Dao
interface PassiveDao {
    @Query("SELECT * FROM passives ORDER BY id")
    fun getPassives(): LiveData<List<Passive>>

    @Query("SELECT * FROM passives WHERE id = :passiveId")
    fun getPassiveById(passiveId: Int): LiveData<Passive>

    @Query("SELECT * FROM passives WHERE championId = :championId")
    fun getPassiveByChampionId(championId: Int): LiveData<Passive>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPassive(passive: Passive)

    @Delete
    fun deletePassive(passive: Passive)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updatePassive(passive: Passive)
}
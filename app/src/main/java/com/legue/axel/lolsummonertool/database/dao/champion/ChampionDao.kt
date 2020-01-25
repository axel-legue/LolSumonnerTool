package com.legue.axel.lolsummonertool.database.dao.champion

import androidx.lifecycle.LiveData
import androidx.room.*
import com.legue.axel.lolsummonertool.database.model.champion.Champion

@Dao
interface ChampionDao {
    @Query("SELECT * FROM champions ORDER BY name")
    fun champions(): LiveData<List<Champion>>

    @Query("SELECT * FROM champions ORDER BY name")
    fun championsWidget(): List<Champion>

    @Query("SELECT * FROM champions WHERE `key` = :championKey")
    fun getChampionByKey(championKey: Int): LiveData<Champion>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllChampion(champions: List<Champion>)

    @Query("DELETE FROM champions")
    fun deleteAll()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateChampion(champion: Champion)
}
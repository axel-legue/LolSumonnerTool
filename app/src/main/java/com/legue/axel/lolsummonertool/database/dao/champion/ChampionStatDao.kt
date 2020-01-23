package com.legue.axel.lolsummonertool.database.dao.champion

import androidx.lifecycle.LiveData
import androidx.room.*
import com.legue.axel.lolsummonertool.database.model.champion.ChampionStats

@Dao
interface ChampionStatDao {
    @Query("SELECT * FROM champion_stats ORDER BY id")
    fun getChampionStats(): LiveData<List<ChampionStats>>

    @Query("SELECT * FROM champion_stats WHERE id = :championStatId")
    fun getChampionStatsById(championStatId: Int): LiveData<ChampionStats>

    @Query("SELECT * FROM champion_stats WHERE championId = :championKeyId")
    fun getChampionStatsByChampionKey(championKeyId: Int): LiveData<ChampionStats>

    @Insert
    fun insertChampionStats(championStats: ChampionStats)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllChampionStats(championStatsList: List<ChampionStats>)

    @Delete
    fun deleteChampionStats(championStats: ChampionStats)

    @Query("DELETE FROM champion_stats")
    fun deleteAll()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateChampionStats(championStats: ChampionStats)
}
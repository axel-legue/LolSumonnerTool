package com.legue.axel.lolsummonertool.database.dao.match

import androidx.lifecycle.LiveData
import androidx.room.*
import com.legue.axel.lolsummonertool.database.model.match.TeamStat

@Dao
interface TeamStatDao {

    @Query("SELECT * FROM teamStats ORDER BY id")
    fun getTamStats(): LiveData<List<TeamStat>>

    @Query("SELECT * FROM teamStats WHERE matchId = :matchId")
    fun getTeamStat(matchId: Long): LiveData<TeamStat>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTeamStats(teamStats: List<TeamStat>)

    @Query("DELETE FROM teamStats")
    fun deleteAll()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTeamStat(teamStat: TeamStat)
}

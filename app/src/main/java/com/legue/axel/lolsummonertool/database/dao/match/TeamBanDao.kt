package com.legue.axel.lolsummonertool.database.dao.match

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

import com.legue.axel.lolsummonertool.database.model.match.TeamBan

@Dao
interface TeamBanDao {

    @Query("SELECT * FROM teambans ORDER BY pickTurn")
    fun getTeamBans(): LiveData<List<TeamBan>>

    @Query("SELECT * FROM teambans WHERE teamStatId = :teamStatId")
    fun getTeamBan(teamStatId: Int): LiveData<TeamBan>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTeamBans(teamBans: List<TeamBan>)

    @Query("DELETE FROM teambans")
    fun deleteAll()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTeamBan(teamBan: TeamBan)
}

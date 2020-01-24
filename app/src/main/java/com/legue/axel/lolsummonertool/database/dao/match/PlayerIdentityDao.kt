package com.legue.axel.lolsummonertool.database.dao.match

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

import com.legue.axel.lolsummonertool.database.model.match.PlayerIdentity

@Dao
interface PlayerIdentityDao {

    @Query("SELECT * FROM playersIdentity ORDER BY summonerName")
    fun getPlayerIdentitys(): LiveData<List<PlayerIdentity>>

    @Query("SELECT * FROM playersIdentity WHERE matchId = :matchId")
    fun getPlayerIdentity(matchId: Long): LiveData<PlayerIdentity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPlayerIdentitys(playersIdentity: List<PlayerIdentity>)

    @Query("DELETE FROM playersIdentity")
    fun deleteAll()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updatePlayerIdentity(playerIdentity: PlayerIdentity)
}

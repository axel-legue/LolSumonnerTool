package com.legue.axel.lolsummonertool.database.dao.match

import androidx.lifecycle.LiveData
import androidx.room.*
import com.legue.axel.lolsummonertool.database.model.match.ParticipantStat

@Dao
interface ParticipantStatDao {

    @Query("SELECT * FROM participantStats ORDER BY id")
    fun getParticipantStats(): LiveData<List<ParticipantStat>>

    @Query("SELECT * FROM participantStats WHERE participantKey = :participantKey")
    fun getParticipantStat(participantKey: Int): LiveData<ParticipantStat>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllParticipantStats(participantStats: List<ParticipantStat>)

    @Query("DELETE FROM participantStats")
    fun deleteAll()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateParticipantStat(participantStat: ParticipantStat)
}

package com.legue.axel.lolsummonertool.database.dao.match


import androidx.lifecycle.LiveData
import androidx.room.*
import com.legue.axel.lolsummonertool.database.model.match.Participant

@Dao
interface ParticipantDao {

    @Query("SELECT * FROM participants ORDER BY id")
    fun getParticipants(): LiveData<List<Participant>>

    @Query("SELECT * FROM participants WHERE matchId = :matchId")
    fun getParticipant(matchId: Long): LiveData<Participant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllParticipants(participants: List<Participant>)

    @Query("DELETE FROM participants")
    fun deleteAll()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateParticipant(participant: Participant)
}

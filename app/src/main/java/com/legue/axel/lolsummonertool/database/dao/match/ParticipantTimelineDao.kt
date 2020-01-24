package com.legue.axel.lolsummonertool.database.dao.match

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

import com.legue.axel.lolsummonertool.database.model.match.ParticipantTimeline

@Dao
interface ParticipantTimelineDao {

    @Query("SELECT * FROM participantTimelines ORDER BY id")
    fun getParticipantTimelines(): LiveData<List<ParticipantTimeline>>

    @Query("SELECT * FROM participantTimelines WHERE participantKey = :participantKey")
    fun getParticipantTimeline(participantKey: Int): LiveData<ParticipantTimeline>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllParticipantTimelines(participantTimelines: List<ParticipantTimeline>)

    @Query("DELETE FROM participantTimelines")
    fun deleteAll()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateParticipantTimeline(participantTimelinse: ParticipantTimeline)
}

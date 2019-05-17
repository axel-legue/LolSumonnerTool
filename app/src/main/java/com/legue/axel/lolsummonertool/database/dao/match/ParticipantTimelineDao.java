package com.legue.axel.lolsummonertool.database.dao.match;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.legue.axel.lolsummonertool.database.model.match.ParticipantTimeline;

import java.util.List;

@Dao
public interface ParticipantTimelineDao {

    @Query("SELECT * FROM participantTimelines ORDER BY id")
    LiveData<List<ParticipantTimeline>> getParticipantTimelines();

    @Query("SELECT * FROM participantTimelines WHERE participantKey = :participantKey")
    LiveData<ParticipantTimeline> getParticipantTimeline(int participantKey);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllParticipantTimelines(List<ParticipantTimeline> participantTimelines);

    @Query("DELETE FROM participantTimelines")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateParticipantTimeline(ParticipantTimeline participantTimelinse);
}

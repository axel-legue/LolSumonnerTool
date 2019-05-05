package com.legue.axel.lolsummonertool.database.dao.match;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

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

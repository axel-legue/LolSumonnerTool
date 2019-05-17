package com.legue.axel.lolsummonertool.database.dao.match;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.legue.axel.lolsummonertool.database.model.match.ParticipantStat;

import java.util.List;

@Dao
public interface ParticipantStatDao {

    @Query("SELECT * FROM participantStats ORDER BY id")
    LiveData<List<ParticipantStat>> getParticipantStats();

    @Query("SELECT * FROM participantStats WHERE participantKey = :participantKey")
    LiveData<ParticipantStat> getParticipantStat(int participantKey);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllParticipantStats(List<ParticipantStat> participantStats);

    @Query("DELETE FROM participantStats")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateParticipantStat(ParticipantStat participantStat);
}

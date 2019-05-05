package com.legue.axel.lolsummonertool.database.dao.match;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

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

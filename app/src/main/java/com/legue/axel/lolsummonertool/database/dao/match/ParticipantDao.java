package com.legue.axel.lolsummonertool.database.dao.match;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.legue.axel.lolsummonertool.database.model.match.Participant;

import java.util.List;

@Dao
public interface ParticipantDao {

    @Query("SELECT * FROM participants ORDER BY id")
    LiveData<List<Participant>> getParticipants();

    @Query("SELECT * FROM participants WHERE matchId = :matchId")
    LiveData<Participant> getParticipant(long matchId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllParticipants(List<Participant> participants);

    @Query("DELETE FROM participants")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateParticipant(Participant participant);
}

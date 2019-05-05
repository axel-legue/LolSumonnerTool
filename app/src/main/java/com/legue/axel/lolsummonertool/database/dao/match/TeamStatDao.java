package com.legue.axel.lolsummonertool.database.dao.match;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.legue.axel.lolsummonertool.database.model.match.TeamStat;

import java.util.List;
@Dao
public interface TeamStatDao {

    @Query("SELECT * FROM teamStats ORDER BY id")
    LiveData<List<TeamStat>> getTeamStats();

    @Query("SELECT * FROM teamStats WHERE matchId = :matchId")
    LiveData<TeamStat> getTeamStat(long matchId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllTeamStats(List<TeamStat> teamStats);

    @Query("DELETE FROM teamStats")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTeamStat(TeamStat teamStat);
}

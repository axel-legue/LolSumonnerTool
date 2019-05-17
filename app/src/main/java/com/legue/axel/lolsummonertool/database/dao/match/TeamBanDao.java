package com.legue.axel.lolsummonertool.database.dao.match;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.legue.axel.lolsummonertool.database.model.match.TeamBan;

import java.util.List;

@Dao
public interface TeamBanDao {

    @Query("SELECT * FROM teambans ORDER BY pickTurn")
    LiveData<List<TeamBan>> getTeamBans();

    @Query("SELECT * FROM teambans WHERE teamStatId = :teamStatId")
    LiveData<TeamBan> getTeamBan(int teamStatId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllTeamBans(List<TeamBan> teamBans);

    @Query("DELETE FROM teambans")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTeamBan(TeamBan teamBan);
}

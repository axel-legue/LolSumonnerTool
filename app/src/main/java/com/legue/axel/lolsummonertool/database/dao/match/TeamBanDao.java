package com.legue.axel.lolsummonertool.database.dao.match;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

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

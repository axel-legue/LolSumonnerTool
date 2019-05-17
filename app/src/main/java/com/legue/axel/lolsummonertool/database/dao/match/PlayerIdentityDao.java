package com.legue.axel.lolsummonertool.database.dao.match;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.legue.axel.lolsummonertool.database.model.match.PlayerIdentity;

import java.util.List;

@Dao
public interface PlayerIdentityDao {

    @Query("SELECT * FROM playersIdentity ORDER BY summonerName")
    LiveData<List<PlayerIdentity>> getPlayerIdentitys();

    @Query("SELECT * FROM playersIdentity WHERE matchId = :matchId")
    LiveData<PlayerIdentity> getPlayerIdentity(long matchId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllPlayerIdentitys(List<PlayerIdentity> playersIdentity);

    @Query("DELETE FROM playersIdentity")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updatePlayerIdentity(PlayerIdentity playerIdentity);
}

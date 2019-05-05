package com.legue.axel.lolsummonertool.database.dao.match;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

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

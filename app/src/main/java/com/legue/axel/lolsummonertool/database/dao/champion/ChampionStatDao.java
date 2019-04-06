package com.legue.axel.lolsummonertool.database.dao.champion;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.legue.axel.lolsummonertool.database.model.champion.ChampionStats;

import java.util.List;

@Dao
public interface ChampionStatDao {

    @Query("SELECT * FROM champion_stats ORDER BY id")
    LiveData<List<ChampionStats>> getChampionStatss();

    @Query("SELECT * FROM champion_stats WHERE id = :championStatId")
    LiveData<ChampionStats> getChampionStatsById(int championStatId);

    @Query("SELECT * FROM champion_stats WHERE championId = :championKeyId")
    LiveData<ChampionStats> getChampionStatsByChampionKey(int championKeyId);

    @Insert
    void insertChampionStats(ChampionStats championStats);

    @Insert
    void insertAllChampionStats(List<ChampionStats> championStatsList);

    @Delete
    void deleteChampionStats(ChampionStats championStats);

    @Query("DELETE FROM champion_stats")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateChampionStats(ChampionStats championStats);
}

package com.legue.axel.lolsummonertool.database.dao.champion;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllChampionStats(List<ChampionStats> championStatsList);

    @Delete
    void deleteChampionStats(ChampionStats championStats);

    @Query("DELETE FROM champion_stats")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateChampionStats(ChampionStats championStats);
}

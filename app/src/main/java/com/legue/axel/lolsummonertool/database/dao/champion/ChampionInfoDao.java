package com.legue.axel.lolsummonertool.database.dao.champion;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.legue.axel.lolsummonertool.database.model.champion.ChampionInfo;

import java.util.List;

@Dao
public interface ChampionInfoDao {

    @Query("SELECT * FROM champion_infos ORDER BY id")
    LiveData<List<ChampionInfo>> getChampionInfos();

    @Query("SELECT * FROM champion_infos WHERE id = :championInfoId")
    LiveData<ChampionInfo> getChampionInfoById(int championInfoId);

    @Query("SELECT * FROM champion_infos WHERE championId = :championKey")
    LiveData<ChampionInfo> getChampionInfoByChampionKeyId(int championKey);

    @Insert
    void insertChampionInfo(ChampionInfo championInfo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllChampionInfo(List<ChampionInfo> championInfoList);

    @Query("DELETE FROM champion_infos")
    void deleteAll();

    @Delete
    void deleteChampionInfo(ChampionInfo championInfo);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateChampionInfo(ChampionInfo championInfo);
}

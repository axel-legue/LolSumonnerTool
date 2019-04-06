package com.legue.axel.lolsummonertool.database.dao.champion;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

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

    @Insert
    void insertAllChampionInfo(List<ChampionInfo> championInfoList);

    @Query("DELETE FROM champion_infos")
    void deleteAll();

    @Delete
    void deleteChampionInfo(ChampionInfo championInfo);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateChampionInfo(ChampionInfo championInfo);
}

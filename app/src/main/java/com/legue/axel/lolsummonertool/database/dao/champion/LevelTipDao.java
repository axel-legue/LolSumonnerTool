package com.legue.axel.lolsummonertool.database.dao.champion;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.legue.axel.lolsummonertool.database.model.champion.LevelTip;

import java.util.List;

@Dao
public interface LevelTipDao {

    @Query("SELECT * FROM level_tip ORDER BY id")
    LiveData<List<LevelTip>> getLevelTips();

    @Query("SELECT * FROM level_tip WHERE id = :levelTipId")
    LiveData<LevelTip> getLevelTipById(int levelTipId);

    @Insert
    void insertLevelTip(LevelTip levelTip);

    @Insert
    void insertLevelTips(List<LevelTip> levelTips);

    @Query("DELETE FROM level_tip")
    void deleteAll();

    @Delete
    void deleteLevelTip(LevelTip levelTip);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateLevelTip(LevelTip levelTip);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateLevelTips(List<LevelTip> levelTips);
}

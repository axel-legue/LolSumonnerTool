package com.legue.axel.lolsummonertool.database.dao.champion;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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

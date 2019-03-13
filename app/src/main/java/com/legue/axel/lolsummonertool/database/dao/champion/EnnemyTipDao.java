package com.legue.axel.lolsummonertool.database.dao.champion;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.legue.axel.lolsummonertool.database.model.champion.EnnemyTip;

import java.util.List;

@Dao
public interface EnnemyTipDao {

    @Query("SELECT * FROM ennemy_tip ORDER BY id")
    LiveData<List<EnnemyTip>> getEnnemyTips();

    @Query("SELECT * FROM ennemy_tip WHERE id = :ennemyTipId")
    LiveData<EnnemyTip> getEnnemyTipById(int ennemyTipId);

    @Insert
    void insertEnnemyTip(EnnemyTip ennemyTip);

    @Delete
    void deleteEnnemyTip(EnnemyTip ennemyTip);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateEnnemyTip(EnnemyTip ennemyTip);
}

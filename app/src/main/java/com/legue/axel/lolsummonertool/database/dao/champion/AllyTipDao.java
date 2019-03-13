package com.legue.axel.lolsummonertool.database.dao.champion;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.legue.axel.lolsummonertool.database.model.champion.AllyTip;

import java.util.List;

@Dao
public interface AllyTipDao {

    @Query("SELECT * FROM allytips ORDER BY id")
    LiveData<List<AllyTip>> getAllyTips();

    @Query("SELECT * FROM allytips WHERE id = :allyTipId")
    LiveData<AllyTip> getAllyTipById(int allyTipId);

    @Insert
    void insertAllyTip(AllyTip allyTip);

    @Delete
    void deleteAllyTip(AllyTip allyTip);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateAllyTip(AllyTip allyTip);
}

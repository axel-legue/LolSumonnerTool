package com.legue.axel.lolsummonertool.database.dao.champion;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.legue.axel.lolsummonertool.database.model.champion.Champion;

import java.util.List;

@Dao
public interface ChampionDao {

    @Query("SELECT * FROM champions ORDER BY id")
    LiveData<List<Champion>> getChampions();

    @Query("SELECT * FROM champions WHERE id = :championId")
    LiveData<Champion> getChampionById(int championId);

    @Insert
    void insertChampion(Champion champion);

    @Delete
    void deleteChampion(Champion champion);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateChampion(Champion champion);
}

package com.legue.axel.lolsummonertool.database.dao.champion;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.legue.axel.lolsummonertool.database.model.champion.Champion;

import java.util.List;

@Dao
public interface ChampionDao {

    @Query("SELECT * FROM champions ORDER BY name")
    LiveData<List<Champion>> getChampions();

    @Query("SELECT * FROM champions ORDER BY name")
    List<Champion> getChampionsWidget();

    @Query("SELECT * FROM champions WHERE `key` = :championKey")
    LiveData<Champion> getChampionByKey(int championKey);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllChampion(List<Champion> champions);

    @Query("DELETE FROM champions")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateChampion(Champion champion);
}

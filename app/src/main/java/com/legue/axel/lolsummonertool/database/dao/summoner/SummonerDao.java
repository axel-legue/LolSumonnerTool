package com.legue.axel.lolsummonertool.database.dao.summoner;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.legue.axel.lolsummonertool.database.model.summoner.Summoner;

import java.util.List;

@Dao
public interface SummonerDao {


    @Query("SELECT * FROM summoners ORDER BY id")
    LiveData<List<Summoner>> getSummoners();

    @Query("SELECT * FROM summoners WHERE id = :summonerId")
    LiveData<Summoner> getSummonerbyId(String summonerId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSummoner(Summoner summoner);

    @Delete
    void deleteSummoner(Summoner summoner);

    @Query("DELETE FROM summoners")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateSummoner(Summoner Summoner);
}

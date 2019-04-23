package com.legue.axel.lolsummonertool.database.dao.champion;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.legue.axel.lolsummonertool.database.model.champion.Passive;

import java.util.List;

@Dao
public interface PassiveDao {

    @Query("SELECT * FROM passives ORDER BY id")
    LiveData<List<Passive>> getPassives();

    @Query("SELECT * FROM passives WHERE id = :passiveId")
    LiveData<Passive> getPassiveById(int passiveId);

    @Query("SELECT * FROM passives WHERE championId = :championId")
    LiveData<Passive> getPassiveByChampionId(int championId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPassive(Passive passive);

    @Delete
    void deletePassive(Passive passive);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updatePassive(Passive passive);
}

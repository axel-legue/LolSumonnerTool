package com.legue.axel.lolsummonertool.database.dao.champion;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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

package com.legue.axel.lolsummonertool.database.dao.mastery;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.legue.axel.lolsummonertool.database.model.mastery.Mastery;

import java.util.List;

@Dao
public interface MasteryDao {

    @Query("SELECT * FROM masteries ORDER BY id")
    LiveData<List<Mastery>> getMasteries();

    @Query("SELECT * FROM masteries WHERE id = :masteryId")
    LiveData<Mastery> getMasteryById(int masteryId);

    @Insert
    void insertMastery(Mastery mastery);

    @Insert
    void insertAllMasteries(List<Mastery> masteries);

    @Delete
    void deleteMastery(Mastery mastery);

    @Query("DELETE FROM masteries")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMastery(Mastery mastery);
}

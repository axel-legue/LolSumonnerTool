package com.legue.axel.lolsummonertool.database.dao.mastery;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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

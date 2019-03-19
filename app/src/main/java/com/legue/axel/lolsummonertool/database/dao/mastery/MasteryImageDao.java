package com.legue.axel.lolsummonertool.database.dao.mastery;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.legue.axel.lolsummonertool.database.model.mastery.MasteryImage;

import java.util.List;

@Dao
public interface MasteryImageDao {

    @Query("SELECT * FROM mastery_images ORDER BY id")
    LiveData<List<MasteryImage>> getMasteryImages();

    @Query("SELECT * FROM mastery_images WHERE id = :masteryId")
    LiveData<MasteryImage> getMasteryImageById(int masteryId);

    @Query("SELECT * FROM mastery_images WHERE masteryId = :masteryId")
    LiveData<MasteryImage> getMasteryImageByMasteryId(int masteryId);

    @Insert
    void insertMasteryImage(MasteryImage masteryImage);

    @Insert
    void insertAllMasteryImages(List<MasteryImage> masteryImages);

    @Delete
    void deleteMasteryImage(MasteryImage masteryImage);

    @Query("DELETE FROM mastery_images")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMasteryImage(MasteryImage masteryImage);
}

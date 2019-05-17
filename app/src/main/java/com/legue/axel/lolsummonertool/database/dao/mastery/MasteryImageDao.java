package com.legue.axel.lolsummonertool.database.dao.mastery;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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

package com.legue.axel.lolsummonertool.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.legue.axel.lolsummonertool.database.model.RiotImage;

import java.util.List;

@Dao
public interface RiotImageDao {

    @Query("SELECT * FROM images ORDER BY id")
    LiveData<List<RiotImage>> getRiotImages();

    @Query("SELECT * FROM images WHERE id = :riotImageId")
    LiveData<RiotImage> getRiotImageById(int riotImageId);

    @Query("SELECT * FROM images WHERE championId = :championId")
    LiveData<RiotImage> getRiotImageByChampionId(int championId);

    @Insert
    void insertRiotImage(RiotImage riotImage);

    @Insert
    void insertAllRiotImage(List<RiotImage> riotImageList);

    @Delete
    void deleteRiotImage(RiotImage riotImage);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateRiotImage(RiotImage riotImage);
}

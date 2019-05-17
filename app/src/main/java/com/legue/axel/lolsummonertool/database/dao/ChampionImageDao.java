package com.legue.axel.lolsummonertool.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.legue.axel.lolsummonertool.database.model.champion.ChampionImage;

import java.util.List;

@Dao
public interface ChampionImageDao {

    @Query("SELECT * FROM images ORDER BY id")
    LiveData<List<ChampionImage>> getChampionImages();

    @Query("SELECT * FROM images WHERE id = :riotImageId")
    LiveData<ChampionImage> getChampionImageById(int riotImageId);

    @Query("SELECT * FROM images WHERE championId = :championKey")
    LiveData<ChampionImage> getChampionImageByChampionId(int championKey);

    @Query("SELECT * FROM images WHERE championId = :championKey")
    ChampionImage getChampionImageByChampionIdWidget(int championKey);

    @Insert
    void insertChampionImage(ChampionImage championImage);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllChampionImage(List<ChampionImage> championImageList);

    @Delete
    void deleteChampionImage(ChampionImage championImage);

    @Query("DELETE FROM images")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateChampionImage(ChampionImage championImage);
}

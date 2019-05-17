package com.legue.axel.lolsummonertool.database.dao.champion;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.legue.axel.lolsummonertool.database.model.champion.Recommended;

import java.util.List;

@Dao
public interface RecommendedDao {

    @Query("SELECT * FROM recommended ORDER BY id")
    LiveData<List<Recommended>> getRecommendeds();

    @Query("SELECT * FROM recommended WHERE id = :recommendedId")
    LiveData<Recommended> getRecommendedById(int recommendedId);

    @Insert
    void insertRecommended(Recommended recommended);

    @Delete
    void deleteRecommended(Recommended recommended);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateRecommended(Recommended recommended);
}

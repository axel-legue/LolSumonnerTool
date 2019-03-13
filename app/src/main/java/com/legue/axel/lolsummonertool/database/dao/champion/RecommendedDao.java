package com.legue.axel.lolsummonertool.database.dao.champion;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

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

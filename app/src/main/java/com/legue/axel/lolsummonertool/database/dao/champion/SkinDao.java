package com.legue.axel.lolsummonertool.database.dao.champion;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.legue.axel.lolsummonertool.database.model.champion.Skin;

import java.util.List;

@Dao
public interface SkinDao {

    @Query("SELECT * FROM skins ORDER BY id")
    LiveData<List<Skin>> getSkins();

    @Query("SELECT * FROM skins WHERE id = :skinId")
    LiveData<Skin> getSkinById(int skinId);

    @Insert
    void insertSkin(Skin skin);

    @Delete
    void deleteSkin(Skin skin);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateSkin(Skin skin);
}

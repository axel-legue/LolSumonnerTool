package com.legue.axel.lolsummonertool.database.dao.champion;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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

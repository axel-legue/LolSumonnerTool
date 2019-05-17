package com.legue.axel.lolsummonertool.database.dao.champion;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.legue.axel.lolsummonertool.database.model.champion.SpellImage;

import java.util.List;

@Dao
public interface SpellImageDao {


    @Query("SELECT * FROM spell_images ORDER BY id")
    LiveData<List<SpellImage>> getSpellImages();

    @Query("SELECT * FROM spell_images WHERE id = :spellImageId")
    LiveData<SpellImage> getSpellImageById(int spellImageId);

    @Query("SELECT * FROM spell_images WHERE spellId = :spellId")
    LiveData<SpellImage> getSpellImageBySpellId(String spellId);

    @Insert
    void insertSpellImage(SpellImage spellImage);

    @Insert
    void insertSpellImages(List<SpellImage> spellImages);

    @Delete
    void deleteSpellImage(SpellImage spellImage);

    @Query("DELETE FROM spell_images")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateSpellImage(SpellImage spellImage);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateSpellImages(List<SpellImage> spellImages);
}

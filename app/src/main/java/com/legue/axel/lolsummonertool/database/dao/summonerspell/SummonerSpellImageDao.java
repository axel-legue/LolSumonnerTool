package com.legue.axel.lolsummonertool.database.dao.summonerspell;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.legue.axel.lolsummonertool.database.model.summonerspell.SummonerSpellImage;

import java.util.List;

@Dao
public interface SummonerSpellImageDao {

    @Query("SELECT * FROM summoner_spell_images ORDER BY id")
    LiveData<List<SummonerSpellImage>> getSummonerSpellImages();

    @Query("SELECT * FROM summoner_spell_images WHERE id = :summonerSpellImageId")
    LiveData<SummonerSpellImage> getSummonerSpellImageById(int summonerSpellImageId);

    @Query("SELECT * FROM summoner_spell_images WHERE summonerSpellId = :summonerSpellId")
    LiveData<SummonerSpellImage> getSummonerSpellImageBySummonerSpellId(int summonerSpellId);

    @Insert
    void insertSummonerSpell(SummonerSpellImage summonerSpellImage);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllSummonerSpellImages(List<SummonerSpellImage> summonerSpellImages);

    @Delete
    void deleteSummonerSpell(SummonerSpellImage summonerSpellImage);

    @Query("DELETE FROM summoner_spell_images")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateSummonerSpell(SummonerSpellImage summonerSpellImage);
}

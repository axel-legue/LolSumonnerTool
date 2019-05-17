package com.legue.axel.lolsummonertool.database.dao.summonerspell;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.legue.axel.lolsummonertool.database.model.summonerspell.SummonerSpell;

import java.util.List;

@Dao
public interface SummonerSpellDao {

    @Query("SELECT * FROM summoner_spells ORDER BY id")
    LiveData<List<SummonerSpell>> getSummonerSpells();

    @Query("SELECT * FROM summoner_spells WHERE `key` = :summonerSpellId")
    LiveData<SummonerSpell> getSummonerSpellById(int summonerSpellId);

    @Insert
    void insertSummonerSpell(SummonerSpell summonerSpell);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllSummonerSpells(List<SummonerSpell> summonerSpells);

    @Delete
    void deleteSummonerSpell(SummonerSpell summonerSpell);

    @Query("DELETE FROM summoner_spells")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateSummonerSpell(SummonerSpell summonerSpell);
}

package com.legue.axel.lolsummonertool.database.dao.summonerspell;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

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

    @Insert
    void insertAllSummonerSpells(List<SummonerSpell> summonerSpells);

    @Delete
    void deleteSummonerSpell(SummonerSpell summonerSpell);

    @Query("DELETE FROM summoner_spells")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateSummonerSpell(SummonerSpell summonerSpell);
}

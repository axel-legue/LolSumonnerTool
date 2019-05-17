package com.legue.axel.lolsummonertool.database.dao.champion;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.legue.axel.lolsummonertool.database.model.champion.Spell;

import java.util.List;

@Dao
public interface SpellDao {

    @Query("SELECT * FROM spells ORDER BY id")
    LiveData<List<Spell>> getSpells();

    @Query("SELECT * FROM spells WHERE id = :spellId")
    LiveData<Spell> getSpellById(int spellId);

    @Query("SELECT * FROM spells WHERE name = :spellName")
    Spell getSpellByName(String spellName);

    @Query("SELECT * FROM spells WHERE championId = :championId ORDER BY name")
    LiveData<List<Spell>> getChampionSpells(int championId);

    @Insert
    void insertSpell(Spell spell);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSpells(List<Spell> spells);

    @Delete
    void deleteSpell(Spell spell);

    @Delete
    void deleteSpells(List<Spell> spells);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateSpell(Spell spell);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateSpells(List<Spell> spells);

}

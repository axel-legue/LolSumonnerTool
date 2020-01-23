package com.legue.axel.lolsummonertool.database.dao.champion

import androidx.lifecycle.LiveData
import androidx.room.*
import com.legue.axel.lolsummonertool.database.model.champion.Spell

@Dao
interface SpellDao {
    @Query("SELECT * FROM spells ORDER BY id")
    fun getSpells(): LiveData<List<Spell>>

    @Query("SELECT * FROM spells WHERE id = :spellId")
    fun getSpellById(spellId: Int): LiveData<Spell>

    @Query("SELECT * FROM spells WHERE name = :spellName")
    fun getSpellByName(spellName: String): Spell

    @Query("SELECT * FROM spells WHERE championId = :championId ORDER BY name")
    fun getChampionSpells(championId: Int): LiveData<List<Spell>>

    @Insert
    fun insertSpell(spell: Spell)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpells(spells: List<Spell>)

    @Delete
    fun deleteSpell(spell: Spell)

    @Delete
    fun deleteSpells(spells: List<Spell>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateSpell(spell: Spell)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateSpells(spells: List<Spell>)
}
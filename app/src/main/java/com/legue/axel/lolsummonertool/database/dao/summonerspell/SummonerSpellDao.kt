package com.legue.axel.lolsummonertool.database.dao.summonerspell

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

import com.legue.axel.lolsummonertool.database.model.summonerspell.SummonerSpell

@Dao
interface SummonerSpellDao {

    @Query("SELECT * FROM summoner_spells ORDER BY id")
    fun getSummonerSpells(): LiveData<List<SummonerSpell>>

    @Query("SELECT * FROM summoner_spells WHERE `key` = :summonerSpellId")
    fun getSummonerSpellById(summonerSpellId: Int): LiveData<SummonerSpell>

    @Insert
    fun insertSummonerSpell(summonerSpell: SummonerSpell)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllSummonerSpells(summonerSpells: List<SummonerSpell>)

    @Delete
    fun deleteSummonerSpell(summonerSpell: SummonerSpell)

    @Query("DELETE FROM summoner_spells")
    fun deleteAll()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateSummonerSpell(summonerSpell: SummonerSpell)
}

package com.legue.axel.lolsummonertool.database.dao.summonerspell

import androidx.lifecycle.LiveData
import androidx.room.*
import com.legue.axel.lolsummonertool.database.model.summonerspell.SummonerSpellImage

@Dao
interface SummonerSpellImageDao {

    @Query("SELECT * FROM summoner_spell_images ORDER BY id")
    fun getSummonerSpellImages(): LiveData<List<SummonerSpellImage>>

    @Query("SELECT * FROM summoner_spell_images WHERE id = :summonerSpellImageId")
    fun getSummonerSpellImageById(summonerSpellImageId: Int): LiveData<SummonerSpellImage>

    @Query("SELECT * FROM summoner_spell_images WHERE summonerSpellId = :summonerSpellId")
    fun getSummonerSpellImageBySummonerSpellId(summonerSpellId: Int): LiveData<SummonerSpellImage>

    @Insert
    fun insertSummonerSpell(summonerSpellImage: SummonerSpellImage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllSummonerSpellImages(summonerSpellImages: List<SummonerSpellImage>)

    @Delete
    fun deleteSummonerSpell(summonerSpellImage: SummonerSpellImage)

    @Query("DELETE FROM summoner_spell_images")
    fun deleteAll()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateSummonerSpell(summonerSpellImage: SummonerSpellImage)
}

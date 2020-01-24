package com.legue.axel.lolsummonertool.database.dao.summoner

import androidx.lifecycle.LiveData
import androidx.room.*
import com.legue.axel.lolsummonertool.database.model.summoner.Summoner

@Dao
interface SummonerDao {


    @Query("SELECT * FROM summoners ORDER BY id")
    fun getSummoners(): LiveData<List<Summoner>>

    @Query("SELECT * FROM summoners WHERE id = :summonerId")
    fun getSummonerbyId(summonerId: String): LiveData<Summoner>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSummoner(summoner: Summoner)

    @Delete
    fun deleteSummoner(summoner: Summoner)

    @Query("DELETE FROM summoners")
    fun deleteAll()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateSummoner(Summoner: Summoner)
}

package com.legue.axel.lolsummonertool.database.dao.match

import androidx.lifecycle.LiveData
import androidx.room.*
import com.legue.axel.lolsummonertool.database.model.match.Match

@Dao
interface MatchDao {

    @Query("SELECT * FROM matches ORDER BY gameId")
    fun getMatches(): LiveData<List<Match>>

    @Query("SELECT * FROM matches WHERE gameId = :gameId")
    fun getMatche(gameId: Long): LiveData<Match>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMatches(matches: List<Match>)

    @Query("DELETE FROM matches")
    fun deleteAll()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateMatch(match: Match)

}

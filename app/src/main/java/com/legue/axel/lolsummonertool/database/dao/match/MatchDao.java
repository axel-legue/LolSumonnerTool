package com.legue.axel.lolsummonertool.database.dao.match;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.legue.axel.lolsummonertool.database.model.match.Match;

import java.util.List;

@Dao
public interface MatchDao {

    @Query("SELECT * FROM matches ORDER BY gameId")
    LiveData<List<Match>> getMatches();

    @Query("SELECT * FROM matches WHERE gameId = :gameId")
    LiveData<Match> getMatche(long gameId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllMatches(List<Match> matches);

    @Query("DELETE FROM matches")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMatch(Match match);

}

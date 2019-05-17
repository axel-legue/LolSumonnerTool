package com.legue.axel.lolsummonertool.database.dao.champion;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.legue.axel.lolsummonertool.database.model.champion.Var;

import java.util.List;

@Dao
public interface VarDao {

    @Query("SELECT * FROM vars ORDER BY id")
    LiveData<List<Var>> getVars();

    @Query("SELECT * FROM vars WHERE id = :varId")
    LiveData<Var> getVarById(int varId);

    @Insert
    void insertVar(Var var);

    @Delete
    void deleteVar(Var var);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateVar(Var var);

}

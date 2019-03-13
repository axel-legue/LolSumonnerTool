package com.legue.axel.lolsummonertool.database.dao.champion;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

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

package com.legue.axel.lolsummonertool.database.dao.champion

import androidx.lifecycle.LiveData
import androidx.room.*
import com.legue.axel.lolsummonertool.database.model.champion.Var

@Dao
interface VarDao {
    @Query("SELECT * FROM vars ORDER BY id")
    fun getVars(): LiveData<List<Var>>

    @Query("SELECT * FROM vars WHERE id = :varId")
    fun getVarById(varId: Int): LiveData<Var>

    @Insert
    fun insertVar(`var`: Var)

    @Delete
    fun deleteVar(`var`: Var)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateVar(`var`: Var)
}
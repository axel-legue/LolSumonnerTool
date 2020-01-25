package com.legue.axel.lolsummonertool.database.dao.champion

import androidx.lifecycle.LiveData
import androidx.room.*
import com.legue.axel.lolsummonertool.database.model.champion.Block

@Dao
interface BlockDao {
    @Query("SELECT * FROM Blocks ORDER BY id")
    fun getBlocks(): LiveData<List<Block>>

    @Query("SELECT * FROM blocks WHERE id = :blockId")
    fun getBlockById(blockId: Int): LiveData<Block>

    @Insert
    fun insertBlock(block: Block)

    @Delete
    fun deleteBlock(ingredient: Block)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateBlock(block: Block)
}
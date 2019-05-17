package com.legue.axel.lolsummonertool.database.dao.champion;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.legue.axel.lolsummonertool.database.model.champion.Block;

import java.util.List;

@Dao
public interface BlockDao {

    @Query("SELECT * FROM Blocks ORDER BY id")
    LiveData<List<Block>> getBlocks();

    @Query("SELECT * FROM blocks WHERE id = :blockId")
    LiveData<Block> getBlockById(int blockId);

    @Insert
    void insertBlock(Block block);

    @Delete
    void deleteBlock(Block ingredient);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateBlock(Block block);

}

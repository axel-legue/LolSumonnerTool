package com.legue.axel.lolsummonertool.database.dao.champion;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

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

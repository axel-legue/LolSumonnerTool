package com.legue.axel.lolsummonertool.database.dao.champion;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.legue.axel.lolsummonertool.database.model.champion.Tag;

import java.util.List;

@Dao
public interface TagDao {

    @Query("SELECT * FROM tags ORDER BY id")
    LiveData<List<Tag>> getTags();

    @Query("SELECT * FROM tags WHERE id = :tagId")
    LiveData<Tag> getTagById(int tagId);

    @Insert
    void insertTag(Tag tag);

    @Insert
    void insertAllTag(List<Tag> tagList);

    @Delete
    void deleteTag(Tag tag);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTag(Tag tag);

}

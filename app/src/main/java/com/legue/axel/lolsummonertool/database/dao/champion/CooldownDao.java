package com.legue.axel.lolsummonertool.database.dao.champion;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.legue.axel.lolsummonertool.database.model.champion.Cooldown;

import java.util.List;

@Dao
public interface CooldownDao {

    @Query("SELECT * FROM cooldowns ORDER BY id")
    LiveData<List<Cooldown>> getCooldowns();

    @Query("SELECT * FROM cooldowns WHERE id = :cooldownId")
    LiveData<Cooldown> getCooldownById(int cooldownId);

    @Insert
    void insertCooldown(Cooldown cooldown);

    @Delete
    void deleteCooldown(Cooldown cooldown);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateCooldown(Cooldown cooldown);
}

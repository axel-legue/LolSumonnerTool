package com.legue.axel.lolsummonertool.database.dao.champion

import androidx.lifecycle.LiveData
import androidx.room.*
import com.legue.axel.lolsummonertool.database.model.champion.ChampionInfo

@Dao
interface ChampionInfoDao {
    @Query("SELECT * FROM champion_infos ORDER BY id")
    fun getChampionInfos(): LiveData<List<ChampionInfo>>

    @Query("SELECT * FROM champion_infos WHERE id = :championInfoId")
    fun getChampionInfoById(championInfoId: Int): LiveData<ChampionInfo>

    @Query("SELECT * FROM champion_infos WHERE championId = :championKey")
    fun getChampionInfoByChampionKeyId(championKey: Int): LiveData<ChampionInfo>

    @Insert
    fun insertChampionInfo(championInfo: ChampionInfo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllChampionInfo(championInfoList: List<ChampionInfo>)

    @Query("DELETE FROM champion_infos")
    fun deleteAll()

    @Delete
    fun deleteChampionInfo(championInfo: ChampionInfo)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateChampionInfo(championInfo: ChampionInfo)
}
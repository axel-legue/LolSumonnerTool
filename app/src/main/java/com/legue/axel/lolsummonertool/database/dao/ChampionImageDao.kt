package com.legue.axel.lolsummonertool.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.legue.axel.lolsummonertool.database.model.champion.ChampionImage

@Dao
interface ChampionImageDao {

    @Query("SELECT * FROM images ORDER BY id")
    fun getChampionImages(): LiveData<List<ChampionImage>>

    @Query("SELECT * FROM images WHERE id = :riotImageId")
    fun getChampionImageById(riotImageId: Int): LiveData<ChampionImage>

    @Query("SELECT * FROM images WHERE championId = :championKey")
    fun getChampionImageByChampionId(championKey: Int): LiveData<ChampionImage>

    @Query("SELECT * FROM images WHERE championId = :championKey")
    fun getChampionImageByChampionIdWidget(championKey: Int): ChampionImage

    @Insert
    fun insertChampionImage(championImage: ChampionImage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllChampionImage(championImageList: List<ChampionImage>)

    @Delete
    fun deleteChampionImage(championImage: ChampionImage)

    @Query("DELETE FROM images")
    fun deleteAll()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateChampionImage(championImage: ChampionImage)
}

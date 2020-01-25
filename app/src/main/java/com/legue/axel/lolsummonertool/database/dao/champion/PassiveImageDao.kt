package com.legue.axel.lolsummonertool.database.dao.champion

import androidx.lifecycle.LiveData
import com.legue.axel.lolsummonertool.database.model.champion.PassiveImage

//@Dao
@Deprecated("")
interface PassiveImageDao {
    //@Query("SELECT * FROM passive_images ORDER BY id")
    fun getPassivePassiveImages(): LiveData<List<PassiveImage>>

    // @Query("SELECT * FROM passive_images WHERE id = :passiveImageId")
    fun getPassiveImageById(passiveImageId: Int): LiveData<PassiveImage>

    //  @Query("SELECT * FROM passive_images WHERE passiveId = :passiveId")
    fun getPassiveImageByPassiveId(passiveId: Int): LiveData<PassiveImage>

    //   @Insert
    fun insertPassiveImage(passiveImage: PassiveImage)

    //  @Insert
    fun insertAllPassiveImage(passiveImages: List<PassiveImage>)

    //  @Delete
    fun deletePassiveImage(passiveImage: PassiveImage)

    // @Query("DELETE FROM passive_images")
    fun deleteAll()

    //    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updatePassiveImage(passiveImage: PassiveImage)
}
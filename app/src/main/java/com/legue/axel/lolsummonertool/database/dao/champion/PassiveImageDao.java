package com.legue.axel.lolsummonertool.database.dao.champion;

import androidx.lifecycle.LiveData;

import com.legue.axel.lolsummonertool.database.model.champion.PassiveImage;

import java.util.List;

//@Dao
@Deprecated
public interface PassiveImageDao {

    //@Query("SELECT * FROM passive_images ORDER BY id")
    LiveData<List<PassiveImage>> getPassivePassiveImages();

   // @Query("SELECT * FROM passive_images WHERE id = :passiveImageId")
    LiveData<PassiveImage> getPassiveImageById(int passiveImageId);

  //  @Query("SELECT * FROM passive_images WHERE passiveId = :passiveId")
    LiveData<PassiveImage> getPassiveImageByPassiveId(int passiveId);

 //   @Insert
    void insertPassiveImage(PassiveImage passiveImage);

  //  @Insert
    void insertAllPassiveImage(List<PassiveImage> passiveImages);

  //  @Delete
    void deletePassiveImage(PassiveImage passiveImage);

   // @Query("DELETE FROM passive_images")
    void deleteAll();

//    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updatePassiveImage(PassiveImage passiveImage);
}

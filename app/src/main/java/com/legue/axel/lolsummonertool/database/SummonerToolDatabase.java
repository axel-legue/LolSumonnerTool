package com.legue.axel.lolsummonertool.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

import com.legue.axel.lolsummonertool.database.model.champion.Champion;

@Database(entities = {Champion.class}, version = 1, exportSchema = false)
public abstract class SummonerToolDatabase extends RoomDatabase {

    private static final String TAG = SummonerToolDatabase.class.getName();
    private static final Object LOCK = new Object();

    private static final String DATABASE_NAME = "SummonerToolDatabase";
    private static SummonerToolDatabase sInstance;

    public static SummonerToolDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(TAG, "Database creation: ");
                sInstance = Room.databaseBuilder(
                        context.getApplicationContext(),
                        SummonerToolDatabase.class,
                        SummonerToolDatabase.DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }

        return sInstance;
    }

}

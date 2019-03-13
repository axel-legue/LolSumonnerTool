package com.legue.axel.lolsummonertool.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

import com.legue.axel.lolsummonertool.database.model.RiotImage;
import com.legue.axel.lolsummonertool.database.model.champion.AllyTip;
import com.legue.axel.lolsummonertool.database.model.champion.Block;
import com.legue.axel.lolsummonertool.database.model.champion.Champion;
import com.legue.axel.lolsummonertool.database.model.champion.ChampionInfo;
import com.legue.axel.lolsummonertool.database.model.champion.ChampionStats;
import com.legue.axel.lolsummonertool.database.model.champion.Cooldown;
import com.legue.axel.lolsummonertool.database.model.champion.EnnemyTip;
import com.legue.axel.lolsummonertool.database.model.champion.LevelTip;
import com.legue.axel.lolsummonertool.database.model.champion.Passive;
import com.legue.axel.lolsummonertool.database.model.champion.Recommended;
import com.legue.axel.lolsummonertool.database.model.champion.Skin;
import com.legue.axel.lolsummonertool.database.model.champion.Spell;
import com.legue.axel.lolsummonertool.database.model.champion.Tag;
import com.legue.axel.lolsummonertool.database.model.champion.Var;

@Database(
        entities = {
                AllyTip.class,
                Block.class,
                Champion.class,
                ChampionInfo.class,
                ChampionStats.class,
                Cooldown.class,
                EnnemyTip.class,
                LevelTip.class,
                Passive.class,
                Recommended.class,
                Skin.class,
                Spell.class,
                Tag.class,
                Var.class,
                RiotImage.class
        },
        version = 1,
        exportSchema = false)
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

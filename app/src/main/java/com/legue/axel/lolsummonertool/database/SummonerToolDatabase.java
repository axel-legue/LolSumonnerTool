package com.legue.axel.lolsummonertool.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

import com.legue.axel.lolsummonertool.database.dao.ChampionImageDao;
import com.legue.axel.lolsummonertool.database.dao.champion.AllyTipDao;
import com.legue.axel.lolsummonertool.database.dao.champion.BlockDao;
import com.legue.axel.lolsummonertool.database.dao.champion.ChampionDao;
import com.legue.axel.lolsummonertool.database.dao.champion.ChampionInfoDao;
import com.legue.axel.lolsummonertool.database.dao.champion.ChampionStatDao;
import com.legue.axel.lolsummonertool.database.dao.champion.CooldownDao;
import com.legue.axel.lolsummonertool.database.dao.champion.EnnemyTipDao;
import com.legue.axel.lolsummonertool.database.dao.champion.LevelTipDao;
import com.legue.axel.lolsummonertool.database.dao.champion.PassiveDao;
import com.legue.axel.lolsummonertool.database.dao.champion.RecommendedDao;
import com.legue.axel.lolsummonertool.database.dao.champion.SkinDao;
import com.legue.axel.lolsummonertool.database.dao.champion.SpellDao;
import com.legue.axel.lolsummonertool.database.dao.champion.TagDao;
import com.legue.axel.lolsummonertool.database.dao.champion.VarDao;
import com.legue.axel.lolsummonertool.database.dao.item.ItemDao;
import com.legue.axel.lolsummonertool.database.dao.item.ItemEffectDao;
import com.legue.axel.lolsummonertool.database.dao.item.ItemGoldDao;
import com.legue.axel.lolsummonertool.database.dao.item.ItemImageDao;
import com.legue.axel.lolsummonertool.database.dao.item.ItemMapDao;
import com.legue.axel.lolsummonertool.database.dao.item.ItemStatDao;
import com.legue.axel.lolsummonertool.database.dao.item.ItemTagDao;
import com.legue.axel.lolsummonertool.database.dao.mastery.MasteryDao;
import com.legue.axel.lolsummonertool.database.dao.mastery.MasteryImageDao;
import com.legue.axel.lolsummonertool.database.model.champion.AllyTip;
import com.legue.axel.lolsummonertool.database.model.champion.Block;
import com.legue.axel.lolsummonertool.database.model.champion.Champion;
import com.legue.axel.lolsummonertool.database.model.champion.ChampionImage;
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
import com.legue.axel.lolsummonertool.database.model.item.Item;
import com.legue.axel.lolsummonertool.database.model.item.ItemEffect;
import com.legue.axel.lolsummonertool.database.model.item.ItemGold;
import com.legue.axel.lolsummonertool.database.model.item.ItemImage;
import com.legue.axel.lolsummonertool.database.model.item.ItemMap;
import com.legue.axel.lolsummonertool.database.model.item.ItemStat;
import com.legue.axel.lolsummonertool.database.model.item.ItemTag;
import com.legue.axel.lolsummonertool.database.model.mastery.Mastery;
import com.legue.axel.lolsummonertool.database.model.mastery.MasteryImage;

@Database(
        entities = {AllyTip.class, Block.class, Champion.class, ChampionInfo.class,
                ChampionStats.class, Cooldown.class, EnnemyTip.class, LevelTip.class, Passive.class,
                Recommended.class, Skin.class, Spell.class, Tag.class, Var.class,
                ChampionImage.class, Item.class, ItemEffect.class, ItemGold.class, ItemImage.class,
                ItemMap.class, ItemStat.class, ItemTag.class, Mastery.class, MasteryImage.class
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

    public abstract AllyTipDao allyTipDao();

    public abstract BlockDao blockDao();

    public abstract ChampionDao championDao();

    public abstract ChampionInfoDao championInfoDao();

    public abstract ChampionStatDao championStatDao();

    public abstract CooldownDao cooldownDao();

    public abstract EnnemyTipDao ennemyTipDao();

    public abstract LevelTipDao levelTipDao();

    public abstract PassiveDao passiveDao();

    public abstract RecommendedDao recommendedDao();

    public abstract SkinDao skinDao();

    public abstract SpellDao spellDao();

    public abstract TagDao tagDao();

    public abstract VarDao varDao();

    public abstract ChampionImageDao championImageDao();

    public abstract ItemDao itemDao();

    public abstract ItemEffectDao itemEffectDao();

    public abstract ItemGoldDao itemGoldDao();

    public abstract ItemImageDao itemImageDao();

    public abstract ItemMapDao itemMapDao();

    public abstract ItemStatDao itemStatDao();

    public abstract ItemTagDao itemTagDao();

    public abstract MasteryDao masteryDao();

    public abstract MasteryImageDao masteryImageDao();

}

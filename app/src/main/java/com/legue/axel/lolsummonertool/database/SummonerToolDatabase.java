package com.legue.axel.lolsummonertool.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

import com.legue.axel.lolsummonertool.database.dao.ChampionImageDao;
import com.legue.axel.lolsummonertool.database.dao.champion.BlockDao;
import com.legue.axel.lolsummonertool.database.dao.champion.ChampionDao;
import com.legue.axel.lolsummonertool.database.dao.champion.ChampionInfoDao;
import com.legue.axel.lolsummonertool.database.dao.champion.ChampionStatDao;
import com.legue.axel.lolsummonertool.database.dao.champion.LevelTipDao;
import com.legue.axel.lolsummonertool.database.dao.champion.PassiveDao;
import com.legue.axel.lolsummonertool.database.dao.champion.PassiveImageDao;
import com.legue.axel.lolsummonertool.database.dao.champion.RecommendedDao;
import com.legue.axel.lolsummonertool.database.dao.champion.SkinDao;
import com.legue.axel.lolsummonertool.database.dao.champion.SpellDao;
import com.legue.axel.lolsummonertool.database.dao.champion.SpellImageDao;
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
import com.legue.axel.lolsummonertool.database.dao.summonerspell.SummonerSpellDao;
import com.legue.axel.lolsummonertool.database.dao.summonerspell.SummonerSpellImageDao;
import com.legue.axel.lolsummonertool.database.model.champion.Block;
import com.legue.axel.lolsummonertool.database.model.champion.Champion;
import com.legue.axel.lolsummonertool.database.model.champion.ChampionImage;
import com.legue.axel.lolsummonertool.database.model.champion.ChampionInfo;
import com.legue.axel.lolsummonertool.database.model.champion.ChampionStats;
import com.legue.axel.lolsummonertool.database.model.champion.LevelTip;
import com.legue.axel.lolsummonertool.database.model.champion.Passive;
import com.legue.axel.lolsummonertool.database.model.champion.PassiveImage;
import com.legue.axel.lolsummonertool.database.model.champion.Recommended;
import com.legue.axel.lolsummonertool.database.model.champion.Skin;
import com.legue.axel.lolsummonertool.database.model.champion.Spell;
import com.legue.axel.lolsummonertool.database.model.champion.SpellImage;
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
import com.legue.axel.lolsummonertool.database.model.summonerspell.SummonerSpell;
import com.legue.axel.lolsummonertool.database.model.summonerspell.SummonerSpellImage;

@Database(
        entities = {Block.class, Champion.class, ChampionInfo.class,
                ChampionStats.class, LevelTip.class, Passive.class,
                Recommended.class, Skin.class, Spell.class, Var.class,
                ChampionImage.class, Item.class, ItemEffect.class, ItemGold.class, ItemImage.class,
                ItemMap.class, ItemStat.class, ItemTag.class, Mastery.class, MasteryImage.class,
                SummonerSpell.class, SummonerSpellImage.class, PassiveImage.class, SpellImage.class
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

    public abstract BlockDao blockDao();

    public abstract ChampionDao championDao();

    public abstract ChampionInfoDao championInfoDao();

    public abstract ChampionStatDao championStatDao();

    public abstract PassiveImageDao passiveImageDao();

    public abstract SpellImageDao spellImageDao();

    public abstract LevelTipDao levelTipDao();

    public abstract PassiveDao passiveDao();

    public abstract RecommendedDao recommendedDao();

    public abstract SkinDao skinDao();

    public abstract SpellDao spellDao();

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

    public abstract SummonerSpellDao summonerSpellDao();

    public abstract SummonerSpellImageDao summonerSpellImageDao();


}

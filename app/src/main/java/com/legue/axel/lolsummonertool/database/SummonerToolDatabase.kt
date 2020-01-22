package com.legue.axel.lolsummonertool.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import android.util.Log

import com.legue.axel.lolsummonertool.database.dao.ChampionImageDao
import com.legue.axel.lolsummonertool.database.dao.champion.BlockDao
import com.legue.axel.lolsummonertool.database.dao.champion.ChampionDao
import com.legue.axel.lolsummonertool.database.dao.champion.ChampionInfoDao
import com.legue.axel.lolsummonertool.database.dao.champion.ChampionStatDao
import com.legue.axel.lolsummonertool.database.dao.champion.LevelTipDao
import com.legue.axel.lolsummonertool.database.dao.champion.PassiveDao
import com.legue.axel.lolsummonertool.database.dao.champion.RecommendedDao
import com.legue.axel.lolsummonertool.database.dao.champion.SkinDao
import com.legue.axel.lolsummonertool.database.dao.champion.SpellDao
import com.legue.axel.lolsummonertool.database.dao.champion.SpellImageDao
import com.legue.axel.lolsummonertool.database.dao.champion.VarDao
import com.legue.axel.lolsummonertool.database.dao.item.ItemDao
import com.legue.axel.lolsummonertool.database.dao.item.ItemEffectDao
import com.legue.axel.lolsummonertool.database.dao.item.ItemGoldDao
import com.legue.axel.lolsummonertool.database.dao.item.ItemImageDao
import com.legue.axel.lolsummonertool.database.dao.item.ItemMapDao
import com.legue.axel.lolsummonertool.database.dao.item.ItemStatDao
import com.legue.axel.lolsummonertool.database.dao.item.ItemTagDao
import com.legue.axel.lolsummonertool.database.dao.mastery.MasteryDao
import com.legue.axel.lolsummonertool.database.dao.mastery.MasteryImageDao
import com.legue.axel.lolsummonertool.database.dao.match.MatchDao
import com.legue.axel.lolsummonertool.database.dao.match.ParticipantDao
import com.legue.axel.lolsummonertool.database.dao.match.ParticipantStatDao
import com.legue.axel.lolsummonertool.database.dao.match.ParticipantTimelineDao
import com.legue.axel.lolsummonertool.database.dao.match.PlayerIdentityDao
import com.legue.axel.lolsummonertool.database.dao.match.TeamBanDao
import com.legue.axel.lolsummonertool.database.dao.match.TeamStatDao
import com.legue.axel.lolsummonertool.database.dao.summoner.SummonerDao
import com.legue.axel.lolsummonertool.database.dao.summonerspell.SummonerSpellDao
import com.legue.axel.lolsummonertool.database.dao.summonerspell.SummonerSpellImageDao
import com.legue.axel.lolsummonertool.database.model.champion.Block
import com.legue.axel.lolsummonertool.database.model.champion.Champion
import com.legue.axel.lolsummonertool.database.model.champion.ChampionImage
import com.legue.axel.lolsummonertool.database.model.champion.ChampionInfo
import com.legue.axel.lolsummonertool.database.model.champion.ChampionStats
import com.legue.axel.lolsummonertool.database.model.champion.LevelTip
import com.legue.axel.lolsummonertool.database.model.champion.Passive
import com.legue.axel.lolsummonertool.database.model.champion.Recommended
import com.legue.axel.lolsummonertool.database.model.champion.Skin
import com.legue.axel.lolsummonertool.database.model.champion.Spell
import com.legue.axel.lolsummonertool.database.model.champion.SpellImage
import com.legue.axel.lolsummonertool.database.model.champion.Var
import com.legue.axel.lolsummonertool.database.model.item.Item
import com.legue.axel.lolsummonertool.database.model.item.ItemEffect
import com.legue.axel.lolsummonertool.database.model.item.ItemGold
import com.legue.axel.lolsummonertool.database.model.item.ItemImage
import com.legue.axel.lolsummonertool.database.model.item.ItemMap
import com.legue.axel.lolsummonertool.database.model.item.ItemStat
import com.legue.axel.lolsummonertool.database.model.item.ItemTag
import com.legue.axel.lolsummonertool.database.model.mastery.Mastery
import com.legue.axel.lolsummonertool.database.model.mastery.MasteryImage
import com.legue.axel.lolsummonertool.database.model.match.Match
import com.legue.axel.lolsummonertool.database.model.match.Participant
import com.legue.axel.lolsummonertool.database.model.match.ParticipantStat
import com.legue.axel.lolsummonertool.database.model.match.ParticipantTimeline
import com.legue.axel.lolsummonertool.database.model.match.PlayerIdentity
import com.legue.axel.lolsummonertool.database.model.match.TeamBan
import com.legue.axel.lolsummonertool.database.model.match.TeamStat
import com.legue.axel.lolsummonertool.database.model.summoner.Summoner
import com.legue.axel.lolsummonertool.database.model.summonerspell.SummonerSpell
import com.legue.axel.lolsummonertool.database.model.summonerspell.SummonerSpellImage

@Database(entities = [Block::class, Champion::class, ChampionInfo::class, ChampionStats::class, LevelTip::class, Passive::class, Recommended::class, Skin::class, Spell::class, Var::class, ChampionImage::class, Item::class, ItemEffect::class, ItemGold::class, ItemImage::class, ItemMap::class, ItemStat::class, ItemTag::class, Mastery::class, MasteryImage::class, SummonerSpell::class, SummonerSpellImage::class, SpellImage::class, Summoner::class, Match::class, Participant::class, ParticipantStat::class, ParticipantTimeline::class, PlayerIdentity::class, TeamBan::class, TeamStat::class], version = 1, exportSchema = false)
abstract class SummonerToolDatabase : RoomDatabase() {

    abstract fun blockDao(): BlockDao

    abstract fun championDao(): ChampionDao

    abstract fun championInfoDao(): ChampionInfoDao

    abstract fun championStatDao(): ChampionStatDao

    //    public abstract PassiveImageDao passiveImageDao();

    abstract fun spellImageDao(): SpellImageDao

    abstract fun levelTipDao(): LevelTipDao

    abstract fun passiveDao(): PassiveDao

    abstract fun recommendedDao(): RecommendedDao

    abstract fun skinDao(): SkinDao

    abstract fun spellDao(): SpellDao

    abstract fun varDao(): VarDao

    abstract fun championImageDao(): ChampionImageDao

    abstract fun itemDao(): ItemDao

    abstract fun itemEffectDao(): ItemEffectDao

    abstract fun itemGoldDao(): ItemGoldDao

    abstract fun itemImageDao(): ItemImageDao

    abstract fun itemMapDao(): ItemMapDao

    abstract fun itemStatDao(): ItemStatDao

    abstract fun itemTagDao(): ItemTagDao

    abstract fun masteryDao(): MasteryDao

    abstract fun masteryImageDao(): MasteryImageDao

    abstract fun summonerSpellDao(): SummonerSpellDao

    abstract fun summonerSpellImageDao(): SummonerSpellImageDao

    abstract fun summonerDao(): SummonerDao

    abstract fun matchDao(): MatchDao

    abstract fun participantDao(): ParticipantDao

    abstract fun participantStatDao(): ParticipantStatDao

    abstract fun participantTimelineDao(): ParticipantTimelineDao

    abstract fun playerIdentityDao(): PlayerIdentityDao

    abstract fun teamBanDao(): TeamBanDao

    abstract fun teamStatDao(): TeamStatDao

    companion object {

        private val TAG = SummonerToolDatabase::class.java.name
        private val LOCK = Any()

        private val DATABASE_NAME = "SummonerToolDatabase"
        private var sInstance: SummonerToolDatabase? = null

        fun getInstance(context: Context): SummonerToolDatabase {
            if (sInstance == null) {
                synchronized(LOCK) {
                    Log.d(TAG, "Database creation: ")
                    sInstance = Room.databaseBuilder(
                            context.applicationContext,
                            SummonerToolDatabase::class.java,
                            DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }

            return sInstance!!
        }
    }


}

package com.legue.axel.lolsummonertool.utils;

import android.util.Log;

import com.legue.axel.lolsummonertool.AppExecutors;
import com.legue.axel.lolsummonertool.database.SummonerToolDatabase;
import com.legue.axel.lolsummonertool.database.model.champion.Champion;
import com.legue.axel.lolsummonertool.database.model.champion.ChampionImage;
import com.legue.axel.lolsummonertool.database.model.champion.ChampionInfo;
import com.legue.axel.lolsummonertool.database.model.champion.ChampionStats;
import com.legue.axel.lolsummonertool.database.model.champion.LevelTip;
import com.legue.axel.lolsummonertool.database.model.champion.Passive;
import com.legue.axel.lolsummonertool.database.model.champion.PassiveImage;
import com.legue.axel.lolsummonertool.database.model.champion.Spell;
import com.legue.axel.lolsummonertool.database.model.champion.SpellImage;
import com.legue.axel.lolsummonertool.network.response.champion.ChampionInfoDetailResponse;
import com.legue.axel.lolsummonertool.network.response.champion.ChampionInfoResponse;
import com.legue.axel.lolsummonertool.network.response.champion.SpellResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class ChampionInfoUtils {
    private final static String TAG = ChampionInfoUtils.class.getName();


    private static Champion champion;
    private static ChampionImage image;
    private static ChampionInfo championInfo;
    private static ChampionStats championStats;
    private static Passive championPassive;
    private static PassiveImage passiveImage;
    private static List<LevelTip> spellLevelTips;
    private static List<SpellImage> spellImages;
    private static List<Spell> spells;

    public static void updateChampionResponseInDB(ChampionInfoResponse championInfoResponse, SummonerToolDatabase database) {
        spells = new ArrayList<>();
        spellLevelTips = new ArrayList<>();
        spellImages = new ArrayList<>();

        if (championInfoResponse != null) {
            LinkedHashMap<String, ChampionInfoDetailResponse> championDetailsList = championInfoResponse.getChampionList();

            List<ChampionInfoDetailResponse> championDetailResponseList;
            championDetailResponseList = getChampionsDetails(championDetailsList);

            if (championDetailResponseList.size() == 1) {
                for (ChampionInfoDetailResponse championDetailResponse : championDetailResponseList) {
                    try {
                        extractChampionDetails(championDetailResponse, database);
                    } catch (Exception e) {
                        Log.i(TAG, "Error when parsing JSON a field must be null");
                        e.printStackTrace();
                    }
                }
                AppExecutors.Companion.getInstance().getDiskIO().execute(() -> {
                    try {
                        database.spellImageDao().deleteAll();

                        database.championDao().updateChampion(champion);
                        database.championInfoDao().updateChampionInfo(championInfo);
                        database.championStatDao().updateChampionStats(championStats);
                        database.championImageDao().updateChampionImage(image);
                        database.passiveDao().insertPassive(championPassive);

                        database.spellDao().insertSpells(spells);
                        database.levelTipDao().insertLevelTips(spellLevelTips);
                        database.spellImageDao().insertSpellImages(spellImages);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                });
            }
        }
    }

    private static List<ChampionInfoDetailResponse> getChampionsDetails(HashMap hashMap) {
        List<ChampionInfoDetailResponse> championDetailResponseList = new ArrayList<>();
        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            String key = (String) pair.getKey();
            championDetailResponseList.add((ChampionInfoDetailResponse) pair.getValue());
            it.remove();
        }
        return championDetailResponseList;
    }

    private static void extractChampionDetails(ChampionInfoDetailResponse championDetailResponse, SummonerToolDatabase database) {

        champion = new Champion();
        champion.key = Integer.valueOf(championDetailResponse.getKey());
        champion.id = championDetailResponse.getId();
        champion.name = championDetailResponse.getName();
        champion.lore = championDetailResponse.getLore();
        champion.title = championDetailResponse.getTitle();
        champion.blurb = championDetailResponse.getBlurb();
        List<String> tags = championDetailResponse.getTags();
        if (tags != null && tags.size() > 0) {
            champion.tags = tags;
        }

        championInfo = new ChampionInfo();
        championInfo = championDetailResponse.getInfo();
        championInfo.championId = Integer.valueOf(championDetailResponse.getKey());

        image = new ChampionImage();
        image = championDetailResponse.getImage();
        image.championId = Integer.valueOf(championDetailResponse.getKey());

        championStats = new ChampionStats();
        championStats = championDetailResponse.getStats();
        championStats.championId = Integer.valueOf(championDetailResponse.getKey());

        championPassive = new Passive();
        championPassive.description = championDetailResponse.getPassive().getDescription();
        championPassive.name = championDetailResponse.getPassive().getName();
        championPassive.image = championDetailResponse.getPassive().getImage().full;
        championPassive.championId = Integer.valueOf(championDetailResponse.getKey());


        List<SpellResponse> spellResponseList = championDetailResponse.getSpells();
        if (spellResponseList != null && spellResponseList.size() > 0) {
            for (SpellResponse spellResponse : spellResponseList) {

                Spell spell = new Spell();
                spell.id = spellResponse.getId();
                spell.name = spellResponse.getName();
                spell.description = spellResponse.getDescription();
                spell.toolTip = spellResponse.getToolTip();
                spell.maxRank = spellResponse.getMaxRank();
                spell.cooldown = spellResponse.getCooldown();
                spell.cooldownBurn = spellResponse.getCooldownBurn();
                spell.cost = spellResponse.getCost();
                spell.costBurn = spellResponse.getCostBurn();
                spell.costType = spellResponse.getCostType();
                spell.maxammo = spellResponse.getMaxAmmo();
                spell.range = spellResponse.getRange();
                spell.rangeBurn = spellResponse.getRangeBurn();
                spell.championId = Integer.valueOf(championDetailResponse.getKey());
                spells.add(spell);

                //FIXME: see waht to do with level tip
                LevelTip levelTip = new LevelTip(
                        null,
                        null,
                        null,
                        spellResponse.getId()
                );

                spellLevelTips.add(levelTip);

                SpellImage spellImage = new SpellImage(
                        null,
                        spellResponse.getImage().getFull(),
                        spellResponse.getImage().getSprite(),
                        spellResponse.getImage().getGroup(),
                        spellResponse.getImage().getX(),
                        spellResponse.getImage().getY(),
                        spellResponse.getImage().getH(),
                        spellResponse.getImage().getW(),
                        spellResponse.getId()
                );
                spellImages.add(spellImage);

            }
        }
    }

}

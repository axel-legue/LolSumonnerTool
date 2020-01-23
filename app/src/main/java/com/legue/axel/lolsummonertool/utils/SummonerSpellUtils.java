package com.legue.axel.lolsummonertool.utils;

import com.legue.axel.lolsummonertool.AppExecutors;
import com.legue.axel.lolsummonertool.database.SummonerToolDatabase;
import com.legue.axel.lolsummonertool.database.model.summonerspell.SummonerSpell;
import com.legue.axel.lolsummonertool.database.model.summonerspell.SummonerSpellImage;
import com.legue.axel.lolsummonertool.network.response.summonerspell.SummonerSpellDetailsResponse;
import com.legue.axel.lolsummonertool.network.response.summonerspell.SummonerSpellsResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class SummonerSpellUtils {

    private static List<SummonerSpell> summonerSpells;
    private static List<SummonerSpellImage> summonerSpellImages;

    public static void insertSummonerSpellResponseInDB(SummonerSpellsResponse summonerSpellsResponse, SummonerToolDatabase database) {
        summonerSpells = new ArrayList<>();
        summonerSpellImages = new ArrayList<>();


        if (summonerSpellsResponse != null) {
            extractSummonerSpellDetails(summonerSpellsResponse.getSummonerSpellList());

            AppExecutors.Companion.getInstance().getDiskIO().execute(() -> {
                try {
                    database.summonerSpellDao().insertAllSummonerSpells(summonerSpells);
                    database.summonerSpellImageDao().insertAllSummonerSpellImages(summonerSpellImages);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static void extractSummonerSpellDetails(LinkedHashMap<String, SummonerSpellDetailsResponse> responseLinkedHashMap) {

        Iterator it = responseLinkedHashMap.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            String key = (String) pair.getKey();
            extractMastery((SummonerSpellDetailsResponse) pair.getValue());
            it.remove();
        }
    }

    private static void extractMastery(SummonerSpellDetailsResponse value) {

        SummonerSpell summonerSpell = new SummonerSpell();
        summonerSpell.id = value.getId();
        summonerSpell.key = value.getKey();
        summonerSpell.name = value.getName();
        summonerSpell.description = value.getDescription();
        summonerSpell.tooltip = value.getTooltip();
        summonerSpell.maxrank = value.getMaxrank();
        summonerSpell.cooldown = value.getCooldown();
        summonerSpell.cooldownBurn = value.getCooldownBurn();
        summonerSpell.cost = value.getCost();
        summonerSpell.costBurn = value.getCostBurn();
        summonerSpell.effectBurn = value.getEffectBurn();
        summonerSpell.summonerLevel = value.getSummonerLevel();
        summonerSpell.modes = value.getModes();
        summonerSpell.costType = value.getCostType();
        summonerSpell.maxammo = value.getMaxammo();
        summonerSpell.range = value.getRange();
        summonerSpell.rangeBurn = value.getRangeBurn();
        summonerSpell.resource = value.getResource();

        SummonerSpellImage image = new SummonerSpellImage();
        image.full = value.getImage().full;
        image.group = value.getImage().group;
        image.sprite = value.getImage().sprite;
        image.x = value.getImage().x;
        image.y = value.getImage().y;
        image.w = value.getImage().w;
        image.h = value.getImage().h;
        image.summonerSpellId = value.getKey();


        if (!summonerSpell.name.contains("Disabled")) {
            summonerSpells.add(summonerSpell);
            summonerSpellImages.add(image);
        }

    }

}

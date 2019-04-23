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
            extractSummonerSpellDetails(summonerSpellsResponse.summonerSpellList);

            AppExecutors.getInstance().getDiskIO().execute(() -> {
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
        summonerSpell.id = value.id;
        summonerSpell.key = value.key;
        summonerSpell.name = value.name;
        summonerSpell.description = value.description;
        summonerSpell.tooltip = value.tooltip;
        summonerSpell.maxrank = value.maxrank;
        summonerSpell.cooldown = value.cooldown;
        summonerSpell.cooldownBurn = value.cooldownBurn;
        summonerSpell.cost = value.cost;
        summonerSpell.costBurn = value.costBurn;
        summonerSpell.effectBurn = value.effectBurn;
        summonerSpell.summonerLevel = value.summonerLevel;
        summonerSpell.modes = value.modes;
        summonerSpell.costType = value.costType;
        summonerSpell.maxammo = value.maxammo;
        summonerSpell.range = value.range;
        summonerSpell.rangeBurn = value.rangeBurn;
        summonerSpell.resource = value.resource;

        SummonerSpellImage image = new SummonerSpellImage();
        image.full = value.image.full;
        image.group = value.image.group;
        image.sprite = value.image.sprite;
        image.x = value.image.x;
        image.y = value.image.y;
        image.w = value.image.w;
        image.h = value.image.h;
        image.summonerSpellId = value.key;


        if (!summonerSpell.name.contains("Disabled")) {
            summonerSpells.add(summonerSpell);
            summonerSpellImages.add(image);
        }

    }

}

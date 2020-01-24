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

        SummonerSpell summonerSpell = new SummonerSpell(
                value.getId(),
                value.getKey(),
                value.getName(),
                value.getDescription(),
                value.getTooltip(),
                value.getMaxrank(),
                value.getCooldownBurn(),
                value.getCostBurn(),
                value.getSummonerLevel(),
                value.getCostType(),
                value.getMaxammo(),
                value.getRangeBurn(),
                value.getResource(),
                value.getCooldown(),
                value.getEffectBurn(),
                value.getCost(),
                value.getRange(),
                value.getModes()
        );

        SummonerSpellImage image = new SummonerSpellImage(
                null,
                value.getImage().getFull(),
                value.getImage().getSprite(),
                value.getImage().getGroup(),
                value.getImage().getX(),
                value.getImage().getY(),
                value.getImage().getW(),
                value.getImage().getH(),
                value.getKey()
        );

        if (!summonerSpell.getName().contains("Disabled")) {
            summonerSpells.add(summonerSpell);
            summonerSpellImages.add(image);
        }

    }

}

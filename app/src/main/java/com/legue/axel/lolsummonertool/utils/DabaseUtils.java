package com.legue.axel.lolsummonertool.utils;

import android.util.Log;

import com.legue.axel.lolsummonertool.AppExecutors;
import com.legue.axel.lolsummonertool.database.SummonerToolDatabase;
import com.legue.axel.lolsummonertool.database.model.RiotImage;
import com.legue.axel.lolsummonertool.database.model.champion.Champion;
import com.legue.axel.lolsummonertool.database.model.champion.ChampionInfo;
import com.legue.axel.lolsummonertool.database.model.champion.ChampionStats;
import com.legue.axel.lolsummonertool.database.model.champion.Tag;
import com.legue.axel.lolsummonertool.network.ChampionDetailResponse;
import com.legue.axel.lolsummonertool.network.ChampionsResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class DabaseUtils {

    private static List<Champion> champions;
    private static List<RiotImage> images;
    private static List<ChampionInfo> championInfos;
    private static List<ChampionStats> championStatsList;
    private static List<Tag> tagList;

    public static void insertChampionResponseInDB(ChampionsResponse championsResponse, SummonerToolDatabase database) {

        champions = new ArrayList<>();
        images = new ArrayList<>();
        championInfos = new ArrayList<>();
        championStatsList = new ArrayList<>();
        tagList = new ArrayList<>();


        if (championsResponse != null) {
            LinkedHashMap<String, ChampionDetailResponse> championDetailsList = championsResponse.getChampionList();

            List<ChampionDetailResponse> championDetailResponseList;
            championDetailResponseList = getChampionsDetails(championDetailsList);

            if (championDetailResponseList.size() > 0) {
                for (ChampionDetailResponse championDetailResponse : championDetailResponseList) {
                    extractChampionDetails(championDetailResponse, database);
                }
                AppExecutors.getInstance().getDiskIO().execute(() -> {
                    try {
                        database.championDao().insertAllChampion(champions);
                        database.championInfoDao().insertAllChampionInfo(championInfos);
                        database.riotImageDao().insertAllRiotImage(images);
                        database.championStatDao().insertAllChampionStats(championStatsList);
                        database.tagDao().insertAllTag(tagList);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                });
            }
        }
    }

    private static List<ChampionDetailResponse> getChampionsDetails(HashMap hashMap) {
        List<ChampionDetailResponse> championDetailResponseList = new ArrayList<>();
        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            String key = (String) pair.getKey();
            championDetailResponseList.add((ChampionDetailResponse) pair.getValue());
            it.remove();
        }
        return championDetailResponseList;
    }

    private static void extractChampionDetails(ChampionDetailResponse championDetailResponse, SummonerToolDatabase database) {

        Champion champion = new Champion();
        champion.key = Integer.valueOf(championDetailResponse.getKey());
        champion.id = championDetailResponse.getId();
        champion.name = championDetailResponse.getName();
        champion.title = championDetailResponse.getTitle();
        champion.lore = championDetailResponse.getLore();
        champion.blurb = championDetailResponse.getBlurb();
        champion.partype = championDetailResponse.getPartype();
        champions.add(champion);


        ChampionInfo championInfo = championDetailResponse.getInfo();
        championInfo.championId = Integer.valueOf(championDetailResponse.getKey());
        championInfos.add(championInfo);


        RiotImage riotImage = championDetailResponse.getImage();
        riotImage.championId = Integer.valueOf(championDetailResponse.getKey());
        images.add(riotImage);

        ChampionStats championStats = championDetailResponse.getStats();
        championStats.championId = Integer.valueOf(championDetailResponse.getKey());
        championStatsList.add(championStats);


        List<String> tags = championDetailResponse.getTags();
        if (tags != null && tags.size() > 0) {

            for (String string : tags) {
                Tag tag = new Tag();
                tag.tag = string;
                tag.championId = Integer.valueOf(championDetailResponse.getKey());
                tagList.add(tag);
            }
        }

    }
}

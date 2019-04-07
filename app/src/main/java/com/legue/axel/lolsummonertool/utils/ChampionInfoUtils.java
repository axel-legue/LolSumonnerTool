package com.legue.axel.lolsummonertool.utils;

import com.legue.axel.lolsummonertool.AppExecutors;
import com.legue.axel.lolsummonertool.database.SummonerToolDatabase;
import com.legue.axel.lolsummonertool.database.model.champion.Champion;
import com.legue.axel.lolsummonertool.database.model.champion.ChampionImage;
import com.legue.axel.lolsummonertool.database.model.champion.ChampionInfo;
import com.legue.axel.lolsummonertool.database.model.champion.ChampionStats;
import com.legue.axel.lolsummonertool.network.response.champion.ChampionInfoDetailResponse;
import com.legue.axel.lolsummonertool.network.response.champion.ChampionInfoResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class ChampionInfoUtils {


    private static Champion champion;
    private static ChampionImage image;
    private static ChampionInfo championInfo;
    private static ChampionStats championStats;

    public static void updateChampionResponseInDB(ChampionInfoResponse championInfoResponse, SummonerToolDatabase database) {

        if (championInfoResponse != null) {
            LinkedHashMap<String, ChampionInfoDetailResponse> championDetailsList = championInfoResponse.getChampionList();

            List<ChampionInfoDetailResponse> championDetailResponseList;
            championDetailResponseList = getChampionsDetails(championDetailsList);

            if (championDetailResponseList.size() == 1) {
                for (ChampionInfoDetailResponse championDetailResponse : championDetailResponseList) {
                    extractChampionDetails(championDetailResponse, database);
                }
                AppExecutors.getInstance().getDiskIO().execute(() -> {
                    try {
                        database.championDao().updateChampion(champion);
                        database.championInfoDao().updateChampionInfo(championInfo);
                        database.championImageDao().updateChampionImage(image);
                        database.championStatDao().updateChampionStats(championStats);
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

    }

}

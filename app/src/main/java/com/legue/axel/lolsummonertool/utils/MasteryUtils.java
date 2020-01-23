package com.legue.axel.lolsummonertool.utils;

import com.legue.axel.lolsummonertool.AppExecutors;
import com.legue.axel.lolsummonertool.database.SummonerToolDatabase;
import com.legue.axel.lolsummonertool.database.model.mastery.Mastery;
import com.legue.axel.lolsummonertool.database.model.mastery.MasteryImage;
import com.legue.axel.lolsummonertool.network.response.mastery.MasteryDetailResponse;
import com.legue.axel.lolsummonertool.network.response.mastery.MasteryResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class MasteryUtils {

    private static List<Mastery> masteries;
    private static List<MasteryImage> masteryImages;

    public static void insertMasteryResponseInDB(MasteryResponse masteryResponse, SummonerToolDatabase database) {
        masteries = new ArrayList<>();
        masteryImages = new ArrayList<>();


        if (masteryResponse != null) {
            extractMasteryDetails(masteryResponse.getMasteryDetailResponse());

            AppExecutors.Companion.getInstance().getDiskIO().execute(() -> {
                try {
                    //TODO : find a way to avoid this delete every time
                    database.masteryDao().deleteAll();
                    database.masteryImageDao().deleteAll();

                    database.masteryDao().insertAllMasteries(masteries);
                    database.masteryImageDao().insertAllMasteryImages(masteryImages);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
        }
    }

    private static void extractMasteryDetails(LinkedHashMap<String, MasteryDetailResponse> responseLinkedHashMap) {

        Iterator it = responseLinkedHashMap.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            String key = (String) pair.getKey();
            extractMastery((MasteryDetailResponse) pair.getValue());
            it.remove();
        }
    }

    private static void extractMastery(MasteryDetailResponse masteryDetailResponse) {
        Mastery mastery = new Mastery();
        mastery.id = masteryDetailResponse.getId();
        mastery.description = masteryDetailResponse.getDescription();
        mastery.name = masteryDetailResponse.getName();
        mastery.prereq = masteryDetailResponse.getPrereq();
        mastery.ranks = masteryDetailResponse.getRanks();
        masteries.add(mastery);

        MasteryImage masteryImage = new MasteryImage();
        masteryImage.full = masteryDetailResponse.getImage().full;
        masteryImage.group = masteryDetailResponse.getImage().group;
        masteryImage.sprite = masteryDetailResponse.getImage().sprite;
        masteryImage.x = masteryDetailResponse.getImage().x;
        masteryImage.y = masteryDetailResponse.getImage().y;
        masteryImage.h = masteryDetailResponse.getImage().h;
        masteryImage.w = masteryDetailResponse.getImage().w;
        masteryImage.masteryId = masteryDetailResponse.getId();
        masteryImages.add(masteryImage);


    }
}

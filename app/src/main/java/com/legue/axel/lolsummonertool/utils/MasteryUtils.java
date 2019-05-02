package com.legue.axel.lolsummonertool.utils;

import com.legue.axel.lolsummonertool.AppExecutors;
import com.legue.axel.lolsummonertool.database.SummonerToolDatabase;
import com.legue.axel.lolsummonertool.database.model.mastery.Mastery;
import com.legue.axel.lolsummonertool.database.model.mastery.MasteryImage;
import com.legue.axel.lolsummonertool.network.response.mastery.MasteryResponse;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MasteryUtils {

    private static List<Mastery> masteries;
    private static List<MasteryImage> masteryImages;

    public static void insertMasteryResponseInDB(MasteryResponse masteryResponse, SummonerToolDatabase database) {
        masteries = new ArrayList<>();
        masteryImages = new ArrayList<>();


        if (masteryResponse != null) {
//            extractMasteryDetails(masteryResponse);

            AppExecutors.getInstance().getDiskIO().execute(() -> {
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

    private static void extractMasteryDetails( LinkedHashMap<String, Object> responseLinkedHashMap) {

//        Iterator it = responseLinkedHashMap.entrySet().iterator();
//        while (it.hasNext()) {
//            HashMap.Entry pair = (HashMap.Entry) it.next();
//            String key = (String) pair.getKey();
//            extractMastery((MasteryDetailResponse) pair.getValue());
//            it.remove();
//        }
    }

    private static void extractMastery(Object masteryDetailResponse) {
//        Mastery mastery = new Mastery();
//        mastery.id = masteryDetailResponse.id;
//        mastery.description = masteryDetailResponse.description;
//        mastery.name = masteryDetailResponse.name;
//        mastery.prereq = masteryDetailResponse.prereq;
//        mastery.ranks = masteryDetailResponse.ranks;
//        masteries.add(mastery);
//
//        MasteryImage masteryImage = new MasteryImage();
//        masteryImage.full = masteryDetailResponse.image.full;
//        masteryImage.group = masteryDetailResponse.image.group;
//        masteryImage.sprite = masteryDetailResponse.image.sprite;
//        masteryImage.x = masteryDetailResponse.image.x;
//        masteryImage.y = masteryDetailResponse.image.y;
//        masteryImage.h = masteryDetailResponse.image.h;
//        masteryImage.w = masteryDetailResponse.image.w;
//        masteryImage.masteryId = masteryDetailResponse.id;
//        masteryImages.add(masteryImage);


    }
}

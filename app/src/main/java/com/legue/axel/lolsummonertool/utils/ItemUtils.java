package com.legue.axel.lolsummonertool.utils;

import com.legue.axel.lolsummonertool.AppExecutors;
import com.legue.axel.lolsummonertool.database.SummonerToolDatabase;
import com.legue.axel.lolsummonertool.database.model.champion.Champion;
import com.legue.axel.lolsummonertool.database.model.item.Item;
import com.legue.axel.lolsummonertool.database.model.item.ItemEffect;
import com.legue.axel.lolsummonertool.database.model.item.ItemGold;
import com.legue.axel.lolsummonertool.database.model.item.ItemImage;
import com.legue.axel.lolsummonertool.database.model.item.ItemMap;
import com.legue.axel.lolsummonertool.database.model.item.ItemStat;
import com.legue.axel.lolsummonertool.database.model.item.ItemTag;
import com.legue.axel.lolsummonertool.network.ChampionDetailResponse;
import com.legue.axel.lolsummonertool.network.ItemDetailResponse;
import com.legue.axel.lolsummonertool.network.ItemsResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class ItemUtils {

    private static List<Item> items;
    private static List<ItemEffect> itemEffects;
    private static List<ItemGold> itemGolds;
    private static List<ItemImage> itemImages;
    private static List<ItemMap> itemMaps;
    private static List<ItemStat> itemStats;
    private static List<ItemTag> itemTags;

    public static void insertItemResponseInDB(ItemsResponse itemsResponse, SummonerToolDatabase database) {
        items = new ArrayList<>();
        itemEffects = new ArrayList<>();
        itemGolds = new ArrayList<>();
        itemImages = new ArrayList<>();
        itemMaps = new ArrayList<>();
        itemStats = new ArrayList<>();
        itemTags = new ArrayList<>();

        if (itemsResponse != null) {
            extractItemDetails(itemsResponse.itemList);

            AppExecutors.getInstance().getDiskIO().execute(() -> {
                try {
                    //TODO : find a way to avoid this delete every time
                    database.itemDao().deleteAll();
                    database.itemEffectDao().deleteAll();
                    database.itemGoldDao().deleteAll();
                    database.itemImageDao().deleteAll();
                    database.itemMapDao().deleteAll();
                    database.itemStatDao().deleteAll();
                    database.itemTagDao().deleteAll();

                    database.itemDao().insertAllItem(items);
                    database.itemEffectDao().insertAllItemEffect(itemEffects);
                    database.itemGoldDao().insertAllItemGold(itemGolds);
                    database.itemImageDao().insertAllItemImage(itemImages);
                    database.itemMapDao().insertAllItemMap(itemMaps);
                    database.itemStatDao().insertAllItemStat(itemStats);
                    database.itemTagDao().insertAllItemTag(itemTags);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
        }
    }

    private static void extractItemDetails(LinkedHashMap<String, ItemDetailResponse> itemDetailResponseList) {

        Iterator it = itemDetailResponseList.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            String key = (String) pair.getKey();
            extractItem(key, (ItemDetailResponse) pair.getValue());
            it.remove();
        }
    }

    private static void extractItem(String key, ItemDetailResponse itemDetailResponse) {
        Item item = new Item(Integer.valueOf(key), itemDetailResponse.name, itemDetailResponse.description, itemDetailResponse.colloq, itemDetailResponse.plaintext, itemDetailResponse.depth);
        items.add(item);

        ItemGold itemGold = new ItemGold();
        itemGold.base = itemDetailResponse.gold.base;
        itemGold.purchasable = itemDetailResponse.gold.purchasable;
        itemGold.sell = itemDetailResponse.gold.sell;
        itemGold.total = itemDetailResponse.gold.total;
        itemGold.itemId = Integer.valueOf(key);
        itemGolds.add(itemGold);

        ItemImage itemImage = new ItemImage();
        itemImage.full = itemDetailResponse.itemImage.full;
        itemImage.group = itemDetailResponse.itemImage.group;
        itemImage.sprite = itemDetailResponse.itemImage.sprite;
        itemImage.x = itemDetailResponse.itemImage.x;
        itemImage.y = itemDetailResponse.itemImage.y;
        itemImage.h = itemDetailResponse.itemImage.h;
        itemImage.w = itemDetailResponse.itemImage.w;
        itemImage.itemId = Integer.valueOf(key);
        itemImages.add(itemImage);

        if (itemDetailResponse.tags != null && itemDetailResponse.tags.size() > 0) {
            for (String string : itemDetailResponse.tags) {
                ItemTag itemTag = new ItemTag();
                itemTag.tag = string;
                itemTag.itemId = Integer.valueOf(key);
                itemTags.add(itemTag);
            }
        }


        HashMap<String, String> effectResponse = itemDetailResponse.effect;
        Iterator it = null;
        if (effectResponse != null) {
            it = effectResponse.entrySet().iterator();
            while (it.hasNext()) {
                HashMap.Entry pair = (HashMap.Entry) it.next();
                ItemEffect itemEffect = new ItemEffect();
                itemEffect.key = (String) pair.getKey();
                itemEffect.value = (String) pair.getValue();
                itemEffect.itemId = Integer.valueOf(key);
                itemEffects.add(itemEffect);
                it.remove();
            }
        }

        HashMap<String, Float> statResponse = itemDetailResponse.stats;
        if (statResponse != null) {
            it = statResponse.entrySet().iterator();
            while (it.hasNext()) {
                HashMap.Entry pair = (HashMap.Entry) it.next();
                ItemStat itemStat = new ItemStat();
                itemStat.key = (String) pair.getKey();
                itemStat.value = (Float) pair.getValue();
                itemStat.itemId = Integer.valueOf(key);
                itemStats.add(itemStat);
                it.remove();
            }
        }

        HashMap<String, Boolean> mapResponse = itemDetailResponse.maps;
        if (mapResponse != null) {
            it = mapResponse.entrySet().iterator();
            while (it.hasNext()) {
                HashMap.Entry pair = (HashMap.Entry) it.next();
                ItemMap itemMap = new ItemMap();
                itemMap.key = (String) pair.getKey();
                itemMap.value = (Boolean) pair.getValue();
                itemMap.itemId = Integer.valueOf(key);
                itemMaps.add(itemMap);
                it.remove();
            }
        }


    }
}

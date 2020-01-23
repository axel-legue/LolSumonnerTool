package com.legue.axel.lolsummonertool.utils;

import com.legue.axel.lolsummonertool.AppExecutors;
import com.legue.axel.lolsummonertool.database.SummonerToolDatabase;
import com.legue.axel.lolsummonertool.database.model.item.Item;
import com.legue.axel.lolsummonertool.database.model.item.ItemEffect;
import com.legue.axel.lolsummonertool.database.model.item.ItemGold;
import com.legue.axel.lolsummonertool.database.model.item.ItemImage;
import com.legue.axel.lolsummonertool.database.model.item.ItemMap;
import com.legue.axel.lolsummonertool.database.model.item.ItemStat;
import com.legue.axel.lolsummonertool.database.model.item.ItemTag;
import com.legue.axel.lolsummonertool.network.response.item.ItemDetailResponse;
import com.legue.axel.lolsummonertool.network.response.item.ItemsResponse;

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
            extractItemDetails(itemsResponse.getItemList());

            AppExecutors.Companion.getInstance().getDiskIO().execute(() -> {
                try {
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
        Item item = new Item();
        item.id = Integer.valueOf(key);
        if (itemDetailResponse.getName() != null) {
            item.name = itemDetailResponse.getName();
        }
        if (itemDetailResponse.getDescription() != null) {
            item.description = itemDetailResponse.getDescription();
        }
        if (itemDetailResponse.getColloq() != null) {
            item.colloq = itemDetailResponse.getColloq();
        }
        if (itemDetailResponse.getPlaintext() != null) {
            item.plaintext = itemDetailResponse.getPlaintext();
        }

        item.depth = itemDetailResponse.getDepth();

        if (itemDetailResponse.getFrom() != null) {
            item.from = itemDetailResponse.getFrom();
        }
        if (itemDetailResponse.getInto() != null) {
            item.into = itemDetailResponse.getInto();
        }
        items.add(item);


        ItemGold itemGold = new ItemGold();

        if (itemDetailResponse.getGold() != null) {
            itemGold.base = itemDetailResponse.getGold().base;
            itemGold.purchasable = itemDetailResponse.getGold().purchasable;
            itemGold.sell = itemDetailResponse.getGold().sell;
            itemGold.total = itemDetailResponse.getGold().total;
            itemGold.itemId = Integer.valueOf(key);
            itemGolds.add(itemGold);
        }


        ItemImage itemImage = new ItemImage();
        if (itemDetailResponse.getItemImage() != null) {
            itemImage.full = itemDetailResponse.getItemImage().full;
            itemImage.group = itemDetailResponse.getItemImage().group;
            itemImage.sprite = itemDetailResponse.getItemImage().sprite;
            itemImage.x = itemDetailResponse.getItemImage().x;
            itemImage.y = itemDetailResponse.getItemImage().y;
            itemImage.h = itemDetailResponse.getItemImage().h;
            itemImage.w = itemDetailResponse.getItemImage().w;
            itemImage.itemId = Integer.valueOf(key);
            itemImages.add(itemImage);
        }


        if (itemDetailResponse.getTags() != null && itemDetailResponse.getTags().size() > 0) {
            for (String string : itemDetailResponse.getTags()) {
                ItemTag itemTag = new ItemTag();
                itemTag.tag = string;
                itemTag.itemId = Integer.valueOf(key);
                itemTags.add(itemTag);
            }
        }

        if (itemDetailResponse.getEffect() != null) {
            HashMap<String, String> effectResponse = itemDetailResponse.getEffect();

            Iterator it;
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

        if (itemDetailResponse.getStats() != null) {
            HashMap<String, Float> statResponse = itemDetailResponse.getStats();
            Iterator it;
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

        if (itemDetailResponse.getMaps() != null) {
            HashMap<String, Boolean> mapResponse = itemDetailResponse.getMaps();
            Iterator it;
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

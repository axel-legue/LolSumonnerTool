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
        Item item = new Item(
                Integer.valueOf(key),
                itemDetailResponse.getName(),
                itemDetailResponse.getDescription(),
                itemDetailResponse.getColloq(),
                itemDetailResponse.getPlaintext(),
                itemDetailResponse.getDepth(),
                itemDetailResponse.getFrom(),
                itemDetailResponse.getInto()
        );
        items.add(item);


        if (itemDetailResponse.getGold() != null) {
            ItemGold itemGold = new ItemGold(
                    null,
                    itemDetailResponse.getGold().getBase(),
                    itemDetailResponse.getGold().getTotal(),
                    itemDetailResponse.getGold().getSell(),
                    itemDetailResponse.getGold().getPurchasable(),
                    Integer.valueOf(key)
            );
            itemGolds.add(itemGold);
        }

        if (itemDetailResponse.getItemImage() != null) {
            ItemImage itemImage = new ItemImage(
                    null,
                    itemDetailResponse.getItemImage().getFull(),
                    itemDetailResponse.getItemImage().getGroup(),
                    itemDetailResponse.getItemImage().getSprite(),
                    itemDetailResponse.getItemImage().getX(),
                    itemDetailResponse.getItemImage().getY(),
                    itemDetailResponse.getItemImage().getH(),
                    itemDetailResponse.getItemImage().getW(),
                    Integer.valueOf(key)
            );
            itemImages.add(itemImage);
        }


        if (itemDetailResponse.getTags() != null && itemDetailResponse.getTags().size() > 0) {
            for (String string : itemDetailResponse.getTags()) {
                ItemTag itemTag = new ItemTag(
                        null,
                        string,
                        Integer.valueOf(key)
                );
                itemTags.add(itemTag);
            }
        }

        if (itemDetailResponse.getEffect() != null) {
            HashMap<String, String> effectResponse = itemDetailResponse.getEffect();

            Iterator it;
            it = effectResponse.entrySet().iterator();
            while (it.hasNext()) {
                HashMap.Entry pair = (HashMap.Entry) it.next();
                ItemEffect itemEffect = new ItemEffect(
                        null,
                        (String) pair.getKey(),
                        (String) pair.getValue(),
                        Integer.valueOf(key)
                );

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
                ItemStat itemStat = new ItemStat(
                        null,
                        (String) pair.getKey(),
                        (Float) pair.getValue(),
                        Integer.valueOf(key)
                );
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
                ItemMap itemMap = new ItemMap(
                        null,
                        pair.getKey().toString(),
                        (Boolean) pair.getValue(),
                        Integer.valueOf(key)
                );
                itemMaps.add(itemMap);
                it.remove();
            }
        }

    }
}

package com.legue.axel.lolsummonertool.database.model;

import com.legue.axel.lolsummonertool.database.model.item.ItemMap;
import com.legue.axel.lolsummonertool.database.model.item.ItemStats;

import java.util.List;

public class Basic {
    private String name;
    private Rune rune;
    private Gold gold;
    private String group;
    private String description;
    private String colloq;
    private String plaintext;
    private boolean consumed;
    private int stacks;
    private int depths;
    private boolean consumeOnFull;
    private List<Integer> from;
    private List<Integer> into;
    private int specialRecipe;
    private boolean inStore;
    private boolean hideFromALl;
    private String requiredChampion;
    private ItemStats itemStats;
    private List<String> tags;
    private ItemMap maps;

    public Basic() {
    }

    public Basic(String name, Rune rune, Gold gold, String group, String description, String colloq, String plaintext, boolean consumed, int stacks, int depths, boolean consumeOnFull, List<Integer> from, List<Integer> into, int specialRecipe, boolean inStore, boolean hideFromALl, String requiredChampion, ItemStats itemStats, List<String> tags, ItemMap maps) {
        this.name = name;
        this.rune = rune;
        this.gold = gold;
        this.group = group;
        this.description = description;
        this.colloq = colloq;
        this.plaintext = plaintext;
        this.consumed = consumed;
        this.stacks = stacks;
        this.depths = depths;
        this.consumeOnFull = consumeOnFull;
        this.from = from;
        this.into = into;
        this.specialRecipe = specialRecipe;
        this.inStore = inStore;
        this.hideFromALl = hideFromALl;
        this.requiredChampion = requiredChampion;
        this.itemStats = itemStats;
        this.tags = tags;
        this.maps = maps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rune getRune() {
        return rune;
    }

    public void setRune(Rune rune) {
        this.rune = rune;
    }

    public Gold getGold() {
        return gold;
    }

    public void setGold(Gold gold) {
        this.gold = gold;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColloq() {
        return colloq;
    }

    public void setColloq(String colloq) {
        this.colloq = colloq;
    }

    public String getPlaintext() {
        return plaintext;
    }

    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }

    public boolean isConsumed() {
        return consumed;
    }

    public void setConsumed(boolean consumed) {
        this.consumed = consumed;
    }

    public int getStacks() {
        return stacks;
    }

    public void setStacks(int stacks) {
        this.stacks = stacks;
    }

    public int getDepths() {
        return depths;
    }

    public void setDepths(int depths) {
        this.depths = depths;
    }

    public boolean isConsumeOnFull() {
        return consumeOnFull;
    }

    public void setConsumeOnFull(boolean consumeOnFull) {
        this.consumeOnFull = consumeOnFull;
    }

    public List<Integer> getFrom() {
        return from;
    }

    public void setFrom(List<Integer> from) {
        this.from = from;
    }

    public List<Integer> getInto() {
        return into;
    }

    public void setInto(List<Integer> into) {
        this.into = into;
    }

    public int getSpecialRecipe() {
        return specialRecipe;
    }

    public void setSpecialRecipe(int specialRecipe) {
        this.specialRecipe = specialRecipe;
    }

    public boolean isInStore() {
        return inStore;
    }

    public void setInStore(boolean inStore) {
        this.inStore = inStore;
    }

    public boolean isHideFromALl() {
        return hideFromALl;
    }

    public void setHideFromALl(boolean hideFromALl) {
        this.hideFromALl = hideFromALl;
    }

    public String getRequiredChampion() {
        return requiredChampion;
    }

    public void setRequiredChampion(String requiredChampion) {
        this.requiredChampion = requiredChampion;
    }

    public ItemStats getItemStats() {
        return itemStats;
    }

    public void setItemStats(ItemStats itemStats) {
        this.itemStats = itemStats;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public ItemMap getMaps() {
        return maps;
    }

    public void setMaps(ItemMap maps) {
        this.maps = maps;
    }
}

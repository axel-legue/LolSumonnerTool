package com.legue.axel.lolsummonertool.database.model;

import com.legue.axel.lolsummonertool.database.model.item.ItemGold;


import java.util.List;

public class Basic {
    public String name;
    public Rune rune;
    public ItemGold itemGold;
    public String group;
    public String description;
    public String colloq;
    public String plaintext;
    public boolean consumed;
    public int stacks;
    public int depths;
    public boolean consumeOnFull;
    public List<Integer> from;
    public List<Integer> into;
    public int specialRecipe;
    public boolean inStore;
    public boolean hideFromALl;
    public String requiredChampion;

    public List<String> tags;


    public Basic() {
    }

    public Basic(String name, Rune rune, ItemGold itemGold, String group, String description, String colloq, String plaintext, boolean consumed, int stacks, int depths, boolean consumeOnFull, List<Integer> from, List<Integer> into, int specialRecipe, boolean inStore, boolean hideFromALl, String requiredChampion, List<String> tags) {
        this.name = name;
        this.rune = rune;
        this.itemGold = itemGold;
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
        this.tags = tags;
    }

}

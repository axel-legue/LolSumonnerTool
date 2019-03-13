package com.legue.axel.lolsummonertool.database.model.item;

import java.util.List;

public class ItemTree {
    public String header;
    public List<String> tags;

    public ItemTree() {
    }

    public ItemTree(String header, List<String> tags) {
        this.header = header;
        this.tags = tags;
    }

}

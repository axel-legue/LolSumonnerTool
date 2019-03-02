package com.legue.axel.lolsummonertool.database.model.item;

import java.util.List;

public class ItemTree {
    private String header;
    private List<String> tags;

    public ItemTree() {
    }

    public ItemTree(String header, List<String> tags) {
        this.header = header;
        this.tags = tags;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}

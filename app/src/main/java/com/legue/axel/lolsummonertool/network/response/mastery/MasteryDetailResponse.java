package com.legue.axel.lolsummonertool.network.response.mastery;

import com.legue.axel.lolsummonertool.database.model.mastery.MasteryImage;

import java.util.List;

public class MasteryDetailResponse {

    public int id;
    public String name;
    public List<String> description;
    public MasteryImage image;
    public int ranks;
    public String prereq;

    public MasteryDetailResponse() {
    }

    public MasteryDetailResponse(int id, String name, List<String> description,
                                 MasteryImage image, int ranks, String prereq) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.ranks = ranks;
        this.prereq = prereq;
    }
}

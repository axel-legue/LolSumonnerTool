package com.legue.axel.lolsummonertool.network.response.champion;

import com.legue.axel.lolsummonertool.database.model.champion.PassiveImage;

public class PassiveResponse {

    public String name;
    public String description;
    public PassiveImage image;

    public PassiveResponse() {
    }

    public PassiveResponse(String name, String description, PassiveImage image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }
}

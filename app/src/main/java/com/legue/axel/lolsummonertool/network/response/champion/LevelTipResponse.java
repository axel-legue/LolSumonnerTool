package com.legue.axel.lolsummonertool.network.response.champion;

import java.util.List;

public class LevelTipResponse {
    private List<String> label;
    private List<String> effect;

    public LevelTipResponse() {
    }

    public LevelTipResponse(List<String> label, List<String> effect) {
        this.label = label;
        this.effect = effect;
    }

    public List<String> getLabel() {
        return label;
    }

    public void setLabel(List<String> label) {
        this.label = label;
    }

    public List<String> getEffect() {
        return effect;
    }

    public void setEffect(List<String> effect) {
        this.effect = effect;
    }
}

package com.legue.axel.lolsummonertool.network.response.champion;

import com.legue.axel.lolsummonertool.database.model.champion.SpellImage;
import com.legue.axel.lolsummonertool.database.model.champion.Var;

import java.util.List;

public class SpellResponse {

    private String id;
    private String name;
    private String description;
    private String toolTip;
    private LevelTipResponse leveltip;
    private int maxRank;
    private List<Integer> cooldown;
    private String cooldownBurn;
    private List<Integer> cost;
    private String costBurn;
    private List<List<Integer>> effect;
    private List<String> effectBurn;
    private List<Var> vars;
    private String costType;
    private String maxammo;
    private List<Integer> range;
    private String rangeBurn;
    private String resource;
    private SpellImage image;

    public SpellResponse() {
    }

    public SpellResponse(String id, String name, String description, String toolTip, LevelTipResponse leveltip, int maxRank, List<Integer> cooldown, String cooldownBurn, List<Integer> cost, String costBurn, List<List<Integer>> effect, List<String> effectBurn, List<Var> vars, String costType, String maxammo, List<Integer> range, String rangeBurn, String resource, SpellImage image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.toolTip = toolTip;
        this.leveltip = leveltip;
        this.maxRank = maxRank;
        this.cooldown = cooldown;
        this.cooldownBurn = cooldownBurn;
        this.cost = cost;
        this.costBurn = costBurn;
        this.effect = effect;
        this.effectBurn = effectBurn;
        this.vars = vars;
        this.costType = costType;
        this.maxammo = maxammo;
        this.range = range;
        this.rangeBurn = rangeBurn;
        this.resource = resource;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getToolTip() {
        return toolTip;
    }

    public void setToolTip(String toolTip) {
        this.toolTip = toolTip;
    }

    public LevelTipResponse getLeveltip() {
        return leveltip;
    }

    public void setLeveltip(LevelTipResponse leveltip) {
        this.leveltip = leveltip;
    }

    public int getMaxRank() {
        return maxRank;
    }

    public void setMaxRank(int maxRank) {
        this.maxRank = maxRank;
    }

    public List<Integer> getCooldown() {
        return cooldown;
    }

    public void setCooldown(List<Integer> cooldown) {
        this.cooldown = cooldown;
    }

    public String getCooldownBurn() {
        return cooldownBurn;
    }

    public void setCooldownBurn(String cooldownBurn) {
        this.cooldownBurn = cooldownBurn;
    }

    public List<Integer> getCost() {
        return cost;
    }

    public void setCost(List<Integer> cost) {
        this.cost = cost;
    }

    public String getCostBurn() {
        return costBurn;
    }

    public void setCostBurn(String costBurn) {
        this.costBurn = costBurn;
    }

    public List<List<Integer>> getEffect() {
        return effect;
    }

    public void setEffect(List<List<Integer>> effect) {
        this.effect = effect;
    }

    public List<String> getEffectBurn() {
        return effectBurn;
    }

    public void setEffectBurn(List<String> effectBurn) {
        this.effectBurn = effectBurn;
    }

    public List<Var> getVars() {
        return vars;
    }

    public void setVars(List<Var> vars) {
        this.vars = vars;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public String getMaxammo() {
        return maxammo;
    }

    public void setMaxammo(String maxammo) {
        this.maxammo = maxammo;
    }

    public List<Integer> getRange() {
        return range;
    }

    public void setRange(List<Integer> range) {
        this.range = range;
    }

    public String getRangeBurn() {
        return rangeBurn;
    }

    public void setRangeBurn(String rangeBurn) {
        this.rangeBurn = rangeBurn;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public SpellImage getImage() {
        return image;
    }

    public void setImage(SpellImage image) {
        this.image = image;
    }
}

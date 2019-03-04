package com.legue.axel.lolsummonertool.database.model.champion;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "champions_infos")
public class ChampionInfo {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int attack;
    private int defense;
    private int magic;
    private int difficulty;

    @Ignore
    public ChampionInfo() {
    }

    public ChampionInfo(int id, int attack, int defense, int magic, int difficulty) {
        this.id = id;
        this.attack = attack;
        this.defense = defense;
        this.magic = magic;
        this.difficulty = difficulty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getMagic() {
        return magic;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}

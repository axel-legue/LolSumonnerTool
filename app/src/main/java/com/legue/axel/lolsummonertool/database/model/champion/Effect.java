package com.legue.axel.lolsummonertool.database.model.champion;

import java.util.List;

//TODO work on it
public class Effect {
    //TODO : transform with TypeConverters
    List<Integer> integerList;

    public Effect() {
    }

    public Effect(List<Integer> integerList) {
        this.integerList = integerList;
    }


    public List<Integer> getIntegerList() {
        return integerList;
    }

}

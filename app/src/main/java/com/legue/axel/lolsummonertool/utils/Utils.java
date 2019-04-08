package com.legue.axel.lolsummonertool.utils;

import java.util.List;

public class Utils {

    public static String converFloatListToString(List<Float> floatList) {
        String costString = "";
        StringBuilder sb = new StringBuilder(costString);
        for (int i = 0; i < floatList.size(); i++) {
            if (i != floatList.size() - 1) {
                sb.append(Math.round(floatList.get(i)));
                sb.append(" / ");
            }else{
                sb.append(Math.round(floatList.get(i)));
            }
        }
        return sb.toString();
    }
}

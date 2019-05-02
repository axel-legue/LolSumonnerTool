package com.legue.axel.lolsummonertool.utils;

import android.content.Context;
import android.util.DisplayMetrics;

import java.util.List;

public class Utils {

    public static String converFloatListToString(List<Float> floatList) {
        String costString = "";
        StringBuilder sb = new StringBuilder(costString);
        for (int i = 0; i < floatList.size(); i++) {
            if (i != floatList.size() - 1) {
                sb.append(Math.round(floatList.get(i)));
                sb.append(" / ");
            } else {
                sb.append(Math.round(floatList.get(i)));
            }
        }
        return sb.toString();
    }


    /**
     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A integer value to represent px equivalent to dp depending on device density
     * @see <a href="https://stackoverflow.com/questions/4605527/converting-pixels-to-dp">StackOverFlow</a>
     * This method converts dp unit to equivalent pixels, depending on device density.
     */
    public static int convertDpToPixel(int dp, Context context) {
        return Math.round(dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    /**
     * * @see <a href="https://stackoverflow.com/questions/4605527/converting-pixels-to-dp">StackOverFlow</a>
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A integer value to represent dp equivalent to px value
     */
    public static int convertPixelsToDp(int px, Context context) {
        return Math.round(px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}

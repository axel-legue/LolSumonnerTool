package com.legue.axel.lolsummonertool.utils

import android.content.Context
import android.util.DisplayMetrics

object Utils {

    fun convertFloatListToString(floatList: List<Float>): String {
        val costString = ""
        val sb = StringBuilder(costString)
        for (i in floatList.indices) {
            if (i != floatList.size - 1) {
                sb.append(Math.round(floatList[i]))
                sb.append(" / ")
            } else {
                sb.append(Math.round(floatList[i]))
            }
        }
        return sb.toString()
    }


    /**
     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A integer value to represent px equivalent to dp depending on device density
     * @see [StackOverFlow](https://stackoverflow.com/questions/4605527/converting-pixels-to-dp)
     * This method converts dp unit to equivalent pixels, depending on device density.
     */
    fun convertDpToPixel(dp: Int, context: Context): Int {
        return Math.round(dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT))
    }

    /**
     * * @see [StackOverFlow](https://stackoverflow.com/questions/4605527/converting-pixels-to-dp)
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A integer value to represent dp equivalent to px value
     */
    fun convertPixelsToDp(px: Int, context: Context): Int {
        return Math.round(px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT))
    }
}

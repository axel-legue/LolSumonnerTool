package com.legue.axel.lolsummonertool.utils;

import android.net.Uri;

import com.legue.axel.lolsummonertool.retrofit.Constants;

public class ImageUtils {

    public static Uri BuildChampionIconUrl(String endPointUrl) {
        return Uri.parse(Constants.API_DRAGON_BASE_CDN + Constants.API_CHAMPION_VERSION + "/" + Constants.API_TYPE_IMAGE + "/" + Constants.API_TYPE_CHAMPION + "/" + endPointUrl);
    }
}

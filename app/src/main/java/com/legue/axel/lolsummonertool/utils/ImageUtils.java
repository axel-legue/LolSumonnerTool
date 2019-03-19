package com.legue.axel.lolsummonertool.utils;

import android.net.Uri;

import com.legue.axel.lolsummonertool.retrofit.RetrofitConstants;

public class ImageUtils {

    public static Uri BuildChampionIconUrl(String endPointUrl) {
        return Uri.parse(RetrofitConstants.API_DRAGON_BASE_CDN + RetrofitConstants.API_CHAMPION_VERSION + "/" + RetrofitConstants.API_TYPE_IMAGE + "/" + RetrofitConstants.API_TYPE_CHAMPION + "/" + endPointUrl);
    }

    public static Uri BuildItemIconUrl(String endPointUrl) {
        return Uri.parse(RetrofitConstants.API_DRAGON_BASE_CDN + RetrofitConstants.API_CHAMPION_VERSION + "/" + RetrofitConstants.API_TYPE_IMAGE + "/" + RetrofitConstants.API_TYPE_ITEM + "/" + endPointUrl);
    }

    public static Uri BuildMasteryIconUrl(String endPointUrl) {
        return Uri.parse(RetrofitConstants.API_DRAGON_BASE_CDN + RetrofitConstants.API_MASTERY_VERSION + "/" + RetrofitConstants.API_TYPE_IMAGE + "/" + RetrofitConstants.API_TYPE_MASTERY + "/" + endPointUrl);
    }
}

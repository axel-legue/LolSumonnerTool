package com.legue.axel.lolsummonertool.utils;

import android.net.Uri;

import com.legue.axel.lolsummonertool.network.retrofit.RetrofitConstants;

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
    public static Uri BuildSummonerSpellIconUrl(String endPointUrl) {
        return Uri.parse(RetrofitConstants.API_DRAGON_BASE_CDN + RetrofitConstants.API_SUMMONER_VERSION + "/" + RetrofitConstants.API_TYPE_IMAGE + "/" + RetrofitConstants.API_TYPE_SPELL + "/" + endPointUrl);
    }

    public static Uri BuildSpellIconUrl(String endPointUrl) {
        return Uri.parse(RetrofitConstants.API_DRAGON_BASE_CDN + RetrofitConstants.API_SPELL_VERSION + "/" + RetrofitConstants.API_TYPE_IMAGE + "/" + RetrofitConstants.API_TYPE_SPELL + "/" + endPointUrl);
    }

    public static Uri BuildPassiveIconUrl(String endPointUrl) {
        return Uri.parse(RetrofitConstants.API_DRAGON_BASE_CDN + RetrofitConstants.API_SPELL_VERSION + "/" + RetrofitConstants.API_TYPE_IMAGE + "/" + RetrofitConstants.API_TYPE_PASSIVE + "/" + endPointUrl);
    }


}

package com.legue.axel.lolsummonertool.utils

import android.net.Uri

import com.legue.axel.lolsummonertool.network.retrofit.RetrofitConstants

object ImageUtils {

    fun buildChampionIconUrl(endPointUrl: String): Uri {
        return Uri.parse(RetrofitConstants.API_DRAGON_BASE_CDN + RetrofitConstants.API_CHAMPION_VERSION + "/" + RetrofitConstants.API_TYPE_IMAGE + "/" + RetrofitConstants.API_TYPE_CHAMPION + "/" + endPointUrl)
    }

    fun buildItemIconUrl(endPointUrl: String): Uri {
        return Uri.parse(RetrofitConstants.API_DRAGON_BASE_CDN + RetrofitConstants.API_CHAMPION_VERSION + "/" + RetrofitConstants.API_TYPE_IMAGE + "/" + RetrofitConstants.API_TYPE_ITEM + "/" + endPointUrl)
    }

    fun buildMasteryIconUrl(endPointUrl: String): Uri {
        return Uri.parse(RetrofitConstants.API_DRAGON_BASE_CDN + RetrofitConstants.API_MASTERY_VERSION + "/" + RetrofitConstants.API_TYPE_IMAGE + "/" + RetrofitConstants.API_TYPE_MASTERY + "/" + endPointUrl)
    }

    fun buildSummonerSpellIconUrl(endPointUrl: String): Uri {
        return Uri.parse(RetrofitConstants.API_DRAGON_BASE_CDN + RetrofitConstants.API_SUMMONER_VERSION + "/" + RetrofitConstants.API_TYPE_IMAGE + "/" + RetrofitConstants.API_TYPE_SPELL + "/" + endPointUrl)
    }

    fun buildSpellIconUrl(endPointUrl: String): Uri {
        return Uri.parse(RetrofitConstants.API_DRAGON_BASE_CDN + RetrofitConstants.API_SPELL_VERSION + "/" + RetrofitConstants.API_TYPE_IMAGE + "/" + RetrofitConstants.API_TYPE_SPELL + "/" + endPointUrl)
    }

    fun buildPassiveIconUrl(endPointUrl: String): Uri {
        return Uri.parse(RetrofitConstants.API_DRAGON_BASE_CDN + RetrofitConstants.API_SPELL_VERSION + "/" + RetrofitConstants.API_TYPE_IMAGE + "/" + RetrofitConstants.API_TYPE_PASSIVE + "/" + endPointUrl)
    }


}

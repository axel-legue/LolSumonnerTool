package com.legue.axel.lolsummonertool

import android.app.Application
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitManager


class SuperApplication : Application() {

    companion object {
        var instance: SuperApplication? = null
    }

    var retrofitManager: RetrofitManager? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        retrofitManager = RetrofitManager()
    }


}

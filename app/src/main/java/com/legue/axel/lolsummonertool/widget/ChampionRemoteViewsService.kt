package com.legue.axel.lolsummonertool.widget

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.legue.axel.lolsummonertool.Constants
import com.legue.axel.lolsummonertool.R
import com.legue.axel.lolsummonertool.database.SummonerToolDatabase
import com.legue.axel.lolsummonertool.database.model.champion.Champion
import java.util.*

class ChampionRemoteViewsService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        return ChampionRemoteViewFactory(this.applicationContext)
    }
}

internal class ChampionRemoteViewFactory(private val mContext: Context) : RemoteViewsService.RemoteViewsFactory {
    private val TAG = ChampionRemoteViewFactory::class.java.name
    private var randomChampionToDisplay: MutableList<Champion>? = null

    init {
        Log.i(TAG, "ChampionRemoteViewFactory: ")
    }

    override fun onCreate() {
        Log.i(TAG, "onCreate: ")
    }

    override fun onDataSetChanged() {
        Log.i(TAG, "onDataSetChanged: ")
        val appWidgetManager = AppWidgetManager.getInstance(mContext)
        val componentName = ComponentName(mContext.packageName, ChampionWidget::class.java.name)

        val championList = SummonerToolDatabase.getInstance(mContext).championDao().championsWidget()
        val random = Random()
        val numberOfChampionToDisplay = 10
        randomChampionToDisplay = ArrayList()

        for (i in 0 until numberOfChampionToDisplay) {
            val randomIndex = random.nextInt(championList.size)
            randomChampionToDisplay!!.add(championList[randomIndex])
            Log.i(TAG, "randomChampionToDisplay size : " + randomChampionToDisplay!!.size)
        }
    }

    override fun onDestroy() {

    }

    override fun getCount(): Int {
        Log.i(TAG, "getCount: " + randomChampionToDisplay!!.size)
        return randomChampionToDisplay!!.size
    }

    override fun getViewAt(position: Int): RemoteViews {
        val champion = randomChampionToDisplay!![position]
        Log.i(TAG, "getViewAt: champion name" + champion.name)

        val views = RemoteViews(mContext.packageName, R.layout.champion_widget_item)

        if (champion.name != null && !TextUtils.isEmpty(champion.name)) {
            views.setTextViewText(R.id.tv_champion_name, champion.name)
        }

        if (champion.tags != null && champion.tags!!.isNotEmpty()) {
            val builder = StringBuilder()
            for (i in champion.tags!!.indices) {
                if (i == champion.tags!!.size - 1) {
                    builder.append(champion.tags!![i])
                } else {
                    builder.append(champion.tags!![i]).append(" - ")
                }
            }


            views.setTextViewText(R.id.tv_champion_title, builder.toString())
        }

        //        ChampionImage championImage = SummonerToolDatabase
        //                .getInstance(mContext)
        //                .championImageDao()
        //                .getChampionImageByChampionIdWidget(champion.key);
        //
        //        if (championImage != null) {
        //            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(componentName);
        //            AppWidgetTarget target = new AppWidgetTarget(mContext, R.id.iv_champion_item, views, appWidgetIds);
        //            displayImage(championImage, target);
        //        }


        val extras = Bundle()
        extras.putInt(Constants.WIKI_CHAMPION_KEY, champion.key)
        extras.putString(Constants.WIKI_CHAMPION_ID, champion.id)

        val fillIntent = Intent()
        fillIntent.putExtras(extras)
        views.setOnClickFillInIntent(R.id.container, fillIntent)

        return views
    }

    override fun getLoadingView(): RemoteViews? {
        return null
    }

    override fun getViewTypeCount(): Int {
        return 1
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    //    private void displayImage(ChampionImage championImage, AppWidgetTarget target) {
    //        Glide.with(mContext)
    //                .asBitmap()
    //                .skipMemoryCache(true)
    //                .diskCacheStrategy(DiskCacheStrategy.NONE)
    //                .circleCrop()
    //                .load(ImageUtils.buildChampionIconUrl(championImage.full))
    //                .into(target);
    //
    //    }
}

package com.legue.axel.lolsummonertool.widget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.legue.axel.lolsummonertool.Constants;
import com.legue.axel.lolsummonertool.R;
import com.legue.axel.lolsummonertool.database.SummonerToolDatabase;
import com.legue.axel.lolsummonertool.database.model.champion.Champion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChampionRemoteViewsService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ChampionRemoteViewFactory(this.getApplicationContext());
    }
}

class ChampionRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {
    private final String TAG = ChampionRemoteViewFactory.class.getName();
    private Context mContext;
    private List<Champion> randomChampionToDisplay;

    public ChampionRemoteViewFactory(Context mContext) {
        Log.i(TAG, "ChampionRemoteViewFactory: ");
        this.mContext = mContext;
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate: ");
    }

    @Override
    public void onDataSetChanged() {
        Log.i(TAG, "onDataSetChanged: ");
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(mContext);
        ComponentName componentName = new ComponentName(mContext.getPackageName(), ChampionWidget.class.getName());

        List<Champion> championList = SummonerToolDatabase.getInstance(mContext).championDao().getChampionsWidget();
        Random random = new Random();
        int numberOfChampionToDisplay = 10;
        randomChampionToDisplay = new ArrayList<>();

        for (int i = 0; i < numberOfChampionToDisplay; i++) {
            int randomIndex = random.nextInt(championList.size());
            randomChampionToDisplay.add(championList.get(randomIndex));
            Log.i(TAG, "randomChampionToDisplay size : " + randomChampionToDisplay.size());
        }
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        Log.i(TAG, "getCount: " + randomChampionToDisplay.size());
        return randomChampionToDisplay.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        Champion champion = randomChampionToDisplay.get(position);
        Log.i(TAG, "getViewAt: champion name" + champion.name);

        RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.champion_widget_item);

        if (champion.name != null && !TextUtils.isEmpty(champion.name)) {
            views.setTextViewText(R.id.tv_champion_name, champion.name);
        }

        if (champion.tags != null && champion.tags.size() > 0) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < champion.tags.size(); i++) {
                if (i == champion.tags.size() - 1) {
                    builder.append(champion.tags.get(i));
                } else {
                    builder.append(champion.tags.get(i)).append(" - ");
                }
            }


            views.setTextViewText(R.id.tv_champion_title, builder.toString());
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


        Bundle extras = new Bundle();
        extras.putInt(Constants.WIKI_CHAMPION_KEY, champion.key);
        extras.putString(Constants.WIKI_CHAMPION_ID, champion.id);

        Intent fillIntent = new Intent();
        fillIntent.putExtras(extras);
        views.setOnClickFillInIntent(R.id.container, fillIntent);

        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

//    private void displayImage(ChampionImage championImage, AppWidgetTarget target) {
//        Glide.with(mContext)
//                .asBitmap()
//                .skipMemoryCache(true)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .circleCrop()
//                .load(ImageUtils.BuildChampionIconUrl(championImage.full))
//                .into(target);
//
//    }
}

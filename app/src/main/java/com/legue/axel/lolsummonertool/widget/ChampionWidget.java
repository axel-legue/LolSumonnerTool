package com.legue.axel.lolsummonertool.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.legue.axel.lolsummonertool.Constants;
import com.legue.axel.lolsummonertool.R;
import com.legue.axel.lolsummonertool.wiki.activity.WikiChampionInformations;

/**
 * Implementation of App Widget functionality.
 */
public class ChampionWidget extends AppWidgetProvider {

    private final static String TAG = ChampionWidget.class.getName();

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {


        // Construct the RemoteViews object
        RemoteViews views = getChampionListRemoteView(context, appWidgetManager, appWidgetId);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.lv_champion_widget);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    private static RemoteViews getChampionListRemoteView(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.champion_widget);


        Intent intent = new Intent(context, ChampionRemoteViewsService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        views.setRemoteAdapter(R.id.lv_champion_widget, intent);

        Intent appIntent = new Intent(context, WikiChampionInformations.class);
        PendingIntent appPendingIntent = PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.lv_champion_widget, appPendingIntent);

        views.setEmptyView(R.id.lv_champion_widget, R.id.tv_empty_view);

        return views;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        String action = intent.getAction();
        if (action != null && action.equalsIgnoreCase(Constants.ACTION_UPDATE_WIDGET)) {
            int ids[] = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);
            onUpdate(context, AppWidgetManager.getInstance(context), ids);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}
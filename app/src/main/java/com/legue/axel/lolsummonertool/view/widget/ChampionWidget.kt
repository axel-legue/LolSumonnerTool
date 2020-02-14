package com.legue.axel.lolsummonertool.view.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

import com.legue.axel.lolsummonertool.Constants
import com.legue.axel.lolsummonertool.R
import com.legue.axel.lolsummonertool.view.wiki.activity.WikiChampionInformations

/**
 * Implementation of App Widget functionality.
 */
class ChampionWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        val action = intent.action
        if (action != null && action.equals(Constants.ACTION_UPDATE_WIDGET, ignoreCase = true)) {
            val ids = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS)
            onUpdate(context, AppWidgetManager.getInstance(context), ids!!)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object {

        private val TAG = ChampionWidget::class.java.name

        internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager,
                                     appWidgetId: Int) {


            // Construct the RemoteViews object
            val views = getChampionListRemoteView(context, appWidgetManager, appWidgetId)

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.lv_champion_widget)
        }

        private fun getChampionListRemoteView(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int): RemoteViews {
            val views = RemoteViews(context.packageName, R.layout.champion_widget)


            val intent = Intent(context, ChampionRemoteViewsService::class.java)
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            views.setRemoteAdapter(R.id.lv_champion_widget, intent)

            val appIntent = Intent(context, WikiChampionInformations::class.java)
            val appPendingIntent = PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT)
            views.setPendingIntentTemplate(R.id.lv_champion_widget, appPendingIntent)

            views.setEmptyView(R.id.lv_champion_widget, R.id.tv_empty_view)

            return views
        }
    }
}
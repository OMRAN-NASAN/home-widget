package com.example.homewidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

class HomeWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            val views = RemoteViews(context.packageName, R.layout.widget_home)
            views.setOnClickPendingIntent(R.id.btnTv, actionPending(context, Config.TV_POWER, 1))
            views.setOnClickPendingIntent(R.id.btnLamp, actionPending(context, Config.LAMP_TOGGLE, 2))
            views.setOnClickPendingIntent(R.id.btnLed, actionPending(context, Config.LED_TOGGLE, 3))
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    private fun actionPending(context: Context, url: String, requestCode: Int): PendingIntent {
        val intent = Intent(context, ActionActivity::class.java).apply {
            putExtra(ActionActivity.EXTRA_URL, url)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        return PendingIntent.getActivity(
            context,
            requestCode,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
    }
}

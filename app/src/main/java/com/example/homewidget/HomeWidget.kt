package com.example.homewidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews

// الأساس المشترك لكل الويدجتات الثلاثة (تلفزيون، LED، مصباح)
// كل ويدجيت يحدد جهازه عبر deviceId فقط
abstract class HomeWidget : AppWidgetProvider() {

    abstract val deviceId: String

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            val views = RemoteViews(context.packageName, R.layout.widget_home)
            views.setTextViewText(R.id.title, Config.titleOf(deviceId))

            // الأزرار تُبنى من KeysService
            // الـ data يجعل مصدر الأزرار منفصلاً لكل ويدجيت
            val serviceIntent = Intent(context, KeysService::class.java).apply {
                putExtra(KeysService.EXTRA_DEVICE, deviceId)
                data = Uri.parse("remote://$deviceId/$appWidgetId")
            }
            views.setRemoteAdapter(R.id.keyGrid, serviceIntent)

            // عند الضغط على أي زر: بث داخلي يرسل الأمر في الخلفية، بدون فتح التطبيق
            val clickIntent = Intent(context, KeyReceiver::class.java)
                .addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
            val clickTemplate = PendingIntent.getBroadcast(
                context,
                0,
                clickIntent,
                PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
            views.setPendingIntentTemplate(R.id.keyGrid, clickTemplate)

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}

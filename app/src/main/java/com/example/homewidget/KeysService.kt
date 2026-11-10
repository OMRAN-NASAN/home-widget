package com.example.homewidget

import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService

// خدمة تعطي الشبكة داخل الويدجيت قائمة الأزرار
class KeysService : RemoteViewsService() {

    override fun onGetViewFactory(intent: Intent): RemoteViewsService.RemoteViewsFactory {
        val deviceId = intent.getStringExtra(EXTRA_DEVICE) ?: "tv"
        return KeysFactory(applicationContext, deviceId)
    }

    companion object {
        const val EXTRA_DEVICE = "device"
    }
}

// تبني كل زر كعنصر منفصل داخل الشبكة
class KeysFactory(
    private val context: Context,
    private val deviceId: String
) : RemoteViewsService.RemoteViewsFactory {

    private val keys: List<RemoteKey> = Config.keysOf(deviceId)

    override fun onCreate() {}

    override fun onDataSetChanged() {}

    override fun onDestroy() {}

    override fun getCount(): Int = keys.size

    override fun getViewAt(position: Int): RemoteViews {
        val key = keys[position]
        val views = RemoteViews(context.packageName, R.layout.widget_key)
        views.setTextViewText(R.id.keyText, key.label)

        // الضغط على الزر يرسل رابطه إلى KeyReceiver
        val fillIn = Intent().putExtra(KeyReceiver.EXTRA_URL, Config.urlOf(deviceId, key))
        views.setOnClickFillInIntent(R.id.keyText, fillIn)
        return views
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(position: Int): Long = position.toLong()

    override fun hasStableIds(): Boolean = true
}

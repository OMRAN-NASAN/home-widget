package com.example.homewidget

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import java.net.HttpURLConnection
import java.net.URL

// يستقبل ضغطة الزر ويرسل أمر الأشعة في الخلفية، بدون فتح أي شاشة
// النجاح صامت، ولا تظهر رسالة إلا عند الفشل
class KeyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val url = intent.getStringExtra(EXTRA_URL) ?: return
        val appContext = context.applicationContext
        val pending = goAsync()

        Thread {
            try {
                val failure = sendCommand(url)
                if (failure != null) {
                    val message = "فشل الإرسال: $failure"
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show()
                    }
                }
            } finally {
                pending.finish()
            }
        }.start()
    }

    // يرجع null عند النجاح، أو سبب الفشل
    private fun sendCommand(url: String): String? {
        return try {
            val conn = URL(url).openConnection() as HttpURLConnection
            conn.connectTimeout = 3000
            conn.readTimeout = 3000
            val code = conn.responseCode
            conn.disconnect()
            if (code == HttpURLConnection.HTTP_OK) null else "HTTP $code"
        } catch (e: Exception) {
            e.javaClass.simpleName
        }
    }

    companion object {
        const val EXTRA_URL = "url"
    }
}

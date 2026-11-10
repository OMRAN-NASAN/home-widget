package com.example.homewidget

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import java.net.HttpURLConnection
import java.net.URL

// نافذة شفافة تفتح عند الضغط على الزر، ترسل الأمر، وتعرض النتيجة ثم تُغلق
class ActionActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val url = intent.getStringExtra(EXTRA_URL)
        if (url == null) {
            Toast.makeText(this, "لا يوجد رابط", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        Thread {
            val message = try {
                val conn = URL(url).openConnection() as HttpURLConnection
                conn.connectTimeout = 4000
                conn.readTimeout = 4000
                val code = conn.responseCode
                conn.disconnect()
                "تم الإرسال (HTTP $code)"
            } catch (e: Exception) {
                "فشل الإرسال: ${e.javaClass.simpleName}"
            }
            runOnUiThread {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                finish()
            }
        }.start()
    }

    companion object {
        const val EXTRA_URL = "url"
    }
}

package com.example.homewidget

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val info = TextView(this).apply {
            text = "ثلاث ويدجتات: ريموت التلفزيون، ريموت LED، ريموت المصباح.\n\nلإضافتها: اضغط مطولاً على الشاشة الرئيسية ← Widgets ← اختر الريموت.\n\nبعد كل تثبيت افتح التطبيق مرة واحدة."
            textSize = 18f
            setPadding(48, 120, 48, 48)
        }
        setContentView(info)
    }
}

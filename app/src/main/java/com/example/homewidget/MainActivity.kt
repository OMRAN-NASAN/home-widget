package com.example.homewidget

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val info = TextView(this).apply {
            text = "أضف الـ Widget من الشاشة الرئيسية: اضغط مطولاً ← Widgets ← Home Widget"
            textSize = 18f
            setPadding(48, 120, 48, 48)
        }
        setContentView(info)
    }
}

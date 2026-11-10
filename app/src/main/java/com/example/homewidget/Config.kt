package com.example.homewidget

// نفس الروابط اللي تستخدمها صفحة التحكم
// أسماء .local تتحدث تلقائياً إذا تغيّر عنوان IP للجهاز
object Config {
    const val TV_POWER = "http://ir-tv.local/send?code=0xE0E06798"
    const val LAMP_TOGGLE = "http://ir-lamp.local/send?code=0xFF807F"
    const val LED_TOGGLE = "http://ir-tv.local/send?code=0xFF02FD"
}

package com.example.homewidget

// الروابط مأخوذة من صفحة التحكم
// استخدمنا عناوين IP بدل أسماء .local لأنها ما تُحل دايماً في تطبيقات أندرويد
object Config {
    const val TV_POWER = "http://192.168.178.26/send?code=0xE0E06798"
    const val LAMP_TOGGLE = "http://192.168.178.33/send?code=0xFF807F"
    const val LED_TOGGLE = "http://192.168.178.26/send?code=0xFF02FD"
}

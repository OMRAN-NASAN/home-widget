package com.example.homewidget

// زر واحد في الريموت: الاسم اللي يظهر على الزر + كود الأشعة
data class RemoteKey(val label: String, val code: String)

object Config {
    // نفس عناوين .local اللي تستخدمها صفحة التحكم
    // أسماء .local تتحدث تلقائياً إذا تغيّر عنوان IP للجهاز
    private const val TV_HOST = "ir-tv.local"
    private const val LAMP_HOST = "ir-lamp.local"

    // المصباح على جهاز منفصل، والتلفزيون وشريط LED على نفس الجهاز
    fun hostOf(deviceId: String): String =
        if (deviceId == "lamp") LAMP_HOST else TV_HOST

    fun urlOf(deviceId: String, key: RemoteKey): String =
        "http://${hostOf(deviceId)}/send?code=${key.code}"

    fun titleOf(deviceId: String): String = when (deviceId) {
        "led" -> "ريموت LED"
        "lamp" -> "ريموت المصباح"
        else -> "ريموت التلفزيون"
    }

    fun keysOf(deviceId: String): List<RemoteKey> = when (deviceId) {
        "led" -> ledKeys
        "lamp" -> lampKeys
        else -> tvKeys
    }

    // ---------- التلفزيون (21 زر) ----------
    private val tvKeys = listOf(
        RemoteKey("Power", "0xE0E06798"),
        RemoteKey("123", "0xE0E04BB4"),
        RemoteKey("Color", "0xE0E0738C"),
        RemoteKey("▲", "0xE0E006F9"),
        RemoteKey("▼", "0xE0E08679"),
        RemoteKey("▶", "0xE0E046B9"),
        RemoteKey("◀", "0xE0E0A659"),
        RemoteKey("OK", "0xE0E016E9"),
        RemoteKey("Back", "0xE0E01AE5"),
        RemoteKey("Home", "0xE0E09E61"),
        RemoteKey("Play/Stop", "0xE0E09D62"),
        RemoteKey("Vol+", "0xE0E0E01F"),
        RemoteKey("Vol", "0xE0E0F00F"),
        RemoteKey("Vol-", "0xE0E0D02F"),
        RemoteKey("CH+", "0xE0E048B7"),
        RemoteKey("CH", "0xE0E0F20D"),
        RemoteKey("CH-", "0xE0E008F7"),
        RemoteKey("TV Plus", "0xE0E02DD2"),
        RemoteKey("Netflix", "0xE0E0CF30"),
        RemoteKey("Prime", "0xE0E02FD0"),
        RemoteKey("Disney", "0xE0E0FB04")
    )

    // ---------- شريط LED (44 زر) ----------
    private val ledKeys = listOf(
        RemoteKey("Power", "0xFF02FD"),
        RemoteKey("Play", "0xFF827D"),
        RemoteKey("Bright-", "0xFFBA45"),
        RemoteKey("Bright+", "0xFF3AC5"),
        RemoteKey("White", "0xFF22DD"),
        RemoteKey("Blue", "0xFFA25D"),
        RemoteKey("Green", "0xFF9A65"),
        RemoteKey("Red", "0xFF1AE5"),
        RemoteKey("Music1", "0xFF12ED"),
        RemoteKey("B1", "0xFF926D"),
        RemoteKey("G1", "0xFFAA55"),
        RemoteKey("R1", "0xFF2AD5"),
        RemoteKey("Music2", "0xFF32CD"),
        RemoteKey("B2", "0xFFB24D"),
        RemoteKey("G2", "0xFF8A75"),
        RemoteKey("R2", "0xFF0AF5"),
        RemoteKey("Music3", "0xFFF807"),
        RemoteKey("B3", "0xFF7887"),
        RemoteKey("G3", "0xFFB847"),
        RemoteKey("R3", "0xFF38C7"),
        RemoteKey("Music4", "0xFFD827"),
        RemoteKey("B4", "0xFF58A7"),
        RemoteKey("G4", "0xFF9867"),
        RemoteKey("R4", "0xFF18E7"),
        RemoteKey("Auto", "0xFFE817"),
        RemoteKey("Bup", "0xFF6897"),
        RemoteKey("Gup", "0xFFA857"),
        RemoteKey("Rup", "0xFF28D7"),
        RemoteKey("Slow", "0xFFC837"),
        RemoteKey("Bdown", "0xFF48B7"),
        RemoteKey("Gdown", "0xFF8877"),
        RemoteKey("Rdown", "0xFF08F7"),
        RemoteKey("Quick", "0xFFF00F"),
        RemoteKey("DIY3", "0xFF708F"),
        RemoteKey("DIY2", "0xFFB04F"),
        RemoteKey("DIY1", "0xFF30CF"),
        RemoteKey("Flash", "0xFFD02F"),
        RemoteKey("DIY6", "0xFF50AF"),
        RemoteKey("DIY5", "0xFF906F"),
        RemoteKey("DIY4", "0xFF10EF"),
        RemoteKey("Fade7", "0xFFE01F"),
        RemoteKey("Fade3", "0xFF609F"),
        RemoteKey("Jump7", "0xFFA05F"),
        RemoteKey("Jump3", "0xFF20DF")
    )

    // ---------- المصباح (تشغيل / إطفاء) ----------
    private val lampKeys = listOf(
        RemoteKey("ON", "0xFF807F"),
        RemoteKey("OFF", "0xFF906F")
    )
}

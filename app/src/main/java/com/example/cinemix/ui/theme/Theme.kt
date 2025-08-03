package com.example.cinemix.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/**
 * لوحة الألوان للمظهر الداكن، بناءً على الألوان المحددة في Color.kt.
 */
private val DarkColorScheme = darkColorScheme(
    primary = AccentPrimary,
    secondary = AccentPrimary,
    background = BgPrimary,
    surface = BgSecondary,
    onPrimary = BgPrimary,
    onSecondary = BgPrimary,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    error = Color(0xFFCF6679),
    onError = Color(0xFF000000)
)

/**
 * لوحة ألوان للمظهر الفاتح (اختياري، كخيار مستقبلي).
 */
private val LightColorScheme = lightColorScheme(
    primary = AccentPrimary,
    secondary = AccentPrimary,
    background = Color(0xFFFFFFFF),
    surface = Color(0xFFF0F2F5),
    onPrimary = BgPrimary,
    onSecondary = BgPrimary,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F)
)

/**
 * المظهر الرئيسي للتطبيق (App Theme).
 * يقوم بتطبيق لوحة الألوان، أنماط النصوص، والأشكال على واجهة المستخدم.
 *
 * @param darkTheme هل يجب استخدام المظهر الداكن. افتراضياً، يتبع إعدادات النظام.
 */
@Composable
fun CineMixTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        // التطبيق مصمم ليكون داكناً بشكل أساسي
        else -> DarkColorScheme
    }

    // للتحكم في لون شريط الحالة (Status Bar)
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

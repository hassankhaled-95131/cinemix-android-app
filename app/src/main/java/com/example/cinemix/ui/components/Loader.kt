package com.example.cinemix.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.cinemix.ui.theme.AccentPrimary

/**
 * مكون Composable لعرض مؤشر تحميل دائري في وسط الشاشة.
 * يُستخدم للإشارة إلى أن التطبيق يقوم بجلب البيانات في الخلفية.
 */
@Composable
fun Loader() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = AccentPrimary)
    }
}

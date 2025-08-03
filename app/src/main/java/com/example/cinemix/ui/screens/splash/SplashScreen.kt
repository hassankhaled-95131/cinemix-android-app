package com.example.cinemix.ui.screens.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cinemix.ui.components.Loader

/**
 * شاشة البداية (Splash Screen) للتطبيق.
 * تعرض مؤشر تحميل أثناء التحقق من حالة مصادقة المستخدم.
 */
@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    val isLoggedIn by viewModel.isLoggedIn.collectAsStateWithLifecycle()

    // LaunchedEffect سيتم تشغيله مرة واحدة عندما تتغير قيمة isLoggedIn من null
    LaunchedEffect(isLoggedIn) {
        isLoggedIn?.let {
            if (it) {
                onNavigateToHome()
            } else {
                onNavigateToLogin()
            }
        }
    }

    // واجهة المستخدم بسيطة جداً، مجرد مؤشر تحميل
    Box(modifier = Modifier.fillMaxSize()) {
        Loader()
    }
}

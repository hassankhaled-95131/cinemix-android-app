package com.example.cinemix.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemix.domain.use_case.IsUserLoggedInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * ViewModel الخاص بشاشة البداية (Splash Screen).
 * مسؤول عن التحقق من حالة مصادقة المستخدم لتحديد الوجهة التالية.
 */
@HiltViewModel
class SplashViewModel @Inject constructor(
    private val isUserLoggedInUseCase: IsUserLoggedInUseCase
) : ViewModel() {

    private val _isLoggedIn = MutableStateFlow<Boolean?>(null)
    val isLoggedIn = _isLoggedIn.asStateFlow()

    init {
        checkLoginStatus()
    }

    private fun checkLoginStatus() {
        // استدعاء حالة الاستخدام للتحقق من حالة تسجيل الدخول.
        isUserLoggedInUseCase().onEach { loggedIn ->
            _isLoggedIn.value = loggedIn
        }.launchIn(viewModelScope)
    }
}

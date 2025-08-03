package com.example.cinemix.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemix.domain.use_case.LogoutUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel الخاص بشاشة الملف الشخصي.
 * مسؤول عن إدارة منطق وحالة الملف الشخصي، وأهمها عملية تسجيل الخروج.
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val logoutUserUseCase: LogoutUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileState())
    val uiState = _uiState.asStateFlow()

    /**
     * دالة يتم استدعاؤها عند النقر على زر تسجيل الخروج.
     */
    fun onLogoutClick() {
        viewModelScope.launch {
            logoutUserUseCase()
            _uiState.update { it.copy(logoutCompleted = true) }
        }
    }
}

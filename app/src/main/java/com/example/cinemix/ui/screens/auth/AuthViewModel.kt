package com.example.cinemix.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemix.domain.use_case.LoginUserUseCase
import com.example.cinemix.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * ViewModel الخاص بشاشة المصادقة.
 * مسؤول عن إدارة منطق وحالة تسجيل الدخول.
 */
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthState())
    val uiState = _uiState.asStateFlow()

    /**
     * دالة يتم استدعاؤها عند تغيير نص حقل اسم المستخدم.
     */
    fun onUsernameChange(username: String) {
        _uiState.update { it.copy(username = username, error = null) }
    }

    /**
     * دالة يتم استدعاؤها عند تغيير نص حقل كلمة المرور.
     */
    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password, error = null) }
    }

    /**
     * دالة يتم استدعاؤها عند النقر على زر تسجيل الدخول.
     */
    fun onLoginClick() {
        // استدعاء حالة الاستخدام لتسجيل الدخول.
        loginUserUseCase(
            username = _uiState.value.username,
            password = _uiState.value.password
        ).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            loginSuccess = true
                        )
                    }
                }
                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}

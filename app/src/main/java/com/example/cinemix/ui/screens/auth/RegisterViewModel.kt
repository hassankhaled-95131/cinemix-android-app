package com.example.cinemix.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemix.domain.use_case.RegisterUserUseCase
import com.example.cinemix.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * ViewModel الخاص بشاشة إنشاء حساب جديد.
 * مسؤول عن إدارة منطق وحالة التسجيل.
 */
@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterState())
    val uiState = _uiState.asStateFlow()

    /**
     * دالة يتم استدعاؤها عند تغيير نص حقل اسم المستخدم.
     */
    fun onUsernameChange(username: String) {
        _uiState.update { it.copy(username = username, error = null) }
    }

    /**
     * دالة يتم استدعاؤها عند تغيير نص حقل البريد الإلكتروني.
     */
    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email, error = null) }
    }

    /**
     * دالة يتم استدعاؤها عند تغيير نص حقل كلمة المرور.
     */
    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password, error = null) }
    }

    /**
     * دالة يتم استدعاؤها عند النقر على زر إنشاء الحساب.
     */
    fun onRegisterClick() {
        registerUserUseCase(
            username = _uiState.value.username,
            email = _uiState.value.email,
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
                            registerSuccess = true
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

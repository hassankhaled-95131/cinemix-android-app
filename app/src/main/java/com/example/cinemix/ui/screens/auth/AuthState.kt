package com.example.cinemix.ui.screens.auth

/**
 * يمثل الحالة الحالية لواجهة المستخدم لشاشة تسجيل الدخول (LoginScreen).
 *
 * @property username اسم المستخدم الذي تم إدخاله.
 * @property password كلمة المرور التي تم إدخالها.
 * @property isLoading هل عملية تسجيل الدخول قيد التنفيذ حالياً.
 * @property loginSuccess هل نجحت عملية تسجيل الدخول.
 * @property error رسالة الخطأ في حال فشل تسجيل الدخول.
 */
data class AuthState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val loginSuccess: Boolean = false,
    val error: String? = null
)

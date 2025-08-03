package com.example.cinemix.ui.screens.auth

/**
 * يمثل الحالة الحالية لواجهة المستخدم لشاشة إنشاء حساب جديد (RegisterScreen).
 *
 * @property username اسم المستخدم الذي تم إدخاله.
 * @property email البريد الإلكتروني الذي تم إدخاله.
 * @property password كلمة المرور التي تم إدخالها.
 * @property isLoading هل عملية التسجيل قيد التنفيذ حالياً.
 * @property registerSuccess هل نجحت عملية التسجيل.
 * @property error رسالة الخطأ في حال فشل التسجيل.
 */
data class RegisterState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val registerSuccess: Boolean = false,
    val error: String? = null
)

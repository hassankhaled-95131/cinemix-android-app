package com.example.cinemix.ui.screens.profile

/**
 * يمثل الحالة الحالية لواجهة المستخدم لشاشة الملف الشخصي (ProfileScreen).
 *
 * @property logoutCompleted هل تمت عملية تسجيل الخروج بنجاح.
 */
data class ProfileState(
    val logoutCompleted: Boolean = false
)

package com.example.cinemix.domain.model

/**
 * يمثل نموذج بيانات المستخدم "النظيف" داخل التطبيق.
 * يحتوي على المعلومات الأساسية التي يتم استخدامها في واجهة المستخدم
 * بعد إتمام عملية المصادقة.
 */
data class UserDetails(
    val id: Int,
    val username: String,
    val email: String,
    val favorites: List<Int> // قائمة بمعرفات الأفلام المفضلة
)

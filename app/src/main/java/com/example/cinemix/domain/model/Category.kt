package com.example.cinemix.domain.model

/**
 * يمثل نموذج بيانات "الفئة" النظيف داخل التطبيق.
 * يستخدم لتصنيف الأقسام في القائمة الجانبية وغيرها.
 */
data class Category(
    val id: Int,
    val name: String
)

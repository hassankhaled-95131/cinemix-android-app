package com.example.cinemix.ui.screens.categories

import com.example.cinemix.domain.model.Category

/**
 * يمثل الحالة الحالية لواجهة المستخدم لشاشة الفئات (CategoriesScreen).
 *
 * @property categories قائمة الفئات.
 * @property isLoading هل عملية جلب القائمة قيد التنفيذ حالياً.
 * @property error رسالة الخطأ في حال حدوث مشكلة.
 */
data class CategoriesState(
    val categories: List<Category> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

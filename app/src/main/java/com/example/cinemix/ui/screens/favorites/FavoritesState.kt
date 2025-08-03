package com.example.cinemix.ui.screens.favorites

import com.example.cinemix.domain.model.Movie

/**
 * يمثل الحالة الحالية لواجهة المستخدم لشاشة المفضلة (FavoritesScreen).
 *
 * @property favoriteMovies قائمة الأفلام المفضلة.
 * @property isLoading هل عملية جلب القائمة قيد التنفيذ حالياً.
 * @property error رسالة الخطأ في حال حدوث مشكلة.
 */
data class FavoritesState(
    val favoriteMovies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

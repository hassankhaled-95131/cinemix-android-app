package com.example.cinemix.ui.screens.home

import com.example.cinemix.domain.model.Movie

/**
 * يمثل الحالة الحالية لواجهة المستخدم للشاشة الرئيسية (HomeScreen).
 *
 * @property popularMoviesList قائمة الأفلام الشائعة.
 * @property popularTvShowsList قائمة المسلسلات الشائعة.
 * @property topRatedMoviesList قائمة الأفلام الأعلى تقييماً.
 * @property upcomingMoviesList قائمة الأفلام القادمة.
 * @property isLoading هل الشاشة حالياً في وضع التحميل.
 * @property error رسالة الخطأ في حال حدوث مشكلة.
 */
data class HomeState(
    val popularMoviesList: List<Movie> = emptyList(),
    val popularTvShowsList: List<Movie> = emptyList(),
    val topRatedMoviesList: List<Movie> = emptyList(),
    val upcomingMoviesList: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

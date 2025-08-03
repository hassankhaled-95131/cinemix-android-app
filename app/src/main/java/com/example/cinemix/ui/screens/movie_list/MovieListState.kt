package com.example.cinemix.ui.screens.movie_list

import com.example.cinemix.domain.model.Movie

/**
 * يمثل الحالة الحالية لواجهة المستخدم لشاشة عرض قائمة الأفلام (MovieListScreen).
 *
 * @property movies قائمة الأفلام التي تنتمي للقائمة المحددة.
 * @property isLoading هل عملية التحميل الأولية قيد التنفيذ.
 * @property isLoadingMore هل عملية تحميل صفحة إضافية قيد التنفيذ.
 * @property error رسالة الخطأ في حال حدوث مشكلة.
 * @property page رقم الصفحة الحالية التي سيتم طلبها.
 * @property canLoadMore هل يمكن تحميل المزيد من الصفحات.
 */
data class MovieListState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val error: String? = null,
    val page: Int = 1,
    val canLoadMore: Boolean = true
)

package com.example.cinemix.ui.screens.search

import com.example.cinemix.domain.model.Movie

/**
 * يمثل الحالة الحالية لواجهة المستخدم لشاشة البحث (SearchScreen).
 *
 * @property searchQuery نص البحث الذي أدخله المستخدم.
 * @property searchResults قائمة الأفلام والمسلسلات التي تطابق البحث.
 * @property isLoading هل عملية التحميل الأولية قيد التنفيذ.
 * @property isLoadingMore هل عملية تحميل صفحة إضافية قيد التنفيذ.
 * @property error رسالة الخطأ في حال حدوث مشكلة.
 * @property page رقم الصفحة الحالية التي سيتم طلبها.
 * @property canLoadMore هل يمكن تحميل المزيد من الصفحات.
 */
data class SearchState(
    val searchQuery: String = "",
    val searchResults: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val error: String? = null,
    val page: Int = 1,
    val canLoadMore: Boolean = true
)

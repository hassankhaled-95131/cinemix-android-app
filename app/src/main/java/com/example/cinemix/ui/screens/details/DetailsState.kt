package com.example.cinemix.ui.screens.details

import com.example.cinemix.domain.model.Movie

/**
 * يمثل الحالة الحالية لواجهة المستخدم لشاشة تفاصيل الفيلم (DetailsScreen).
 *
 * @property movie تفاصيل الفيلم الذي يتم عرضه. قد يكون null أثناء التحميل الأولي.
 * @property isLoading هل الشاشة حالياً في وضع التحميل.
 * @property error رسالة الخطأ في حال حدوث مشكلة.
 * @property isFavorite هل الفيلم الحالي موجود في قائمة المفضلة.
 */
data class DetailsState(
    val movie: Movie? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isFavorite: Boolean = false
)

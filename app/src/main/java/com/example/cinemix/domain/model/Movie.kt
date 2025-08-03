package com.example.cinemix.domain.model

/**
 * يمثل نموذج بيانات الفيلم/المسلسل "النظيف" الذي يتم استخدامه في جميع أنحاء التطبيق.
 * هذه الفئة هي مصدر الحقيقة لكيفية تمثيل الفيلم في طبقات الـ UI و الـ Domain،
 * وهي مستقلة عن كيفية جلب البيانات (من API أو قاعدة بيانات محلية).
 */
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val voteAverage: Double,
    val releaseDate: String,
    val mediaType: String, // 'movie' or 'tv'
    val genres: List<String>,
    val runtime: Int? // قد لا يكون متوفراً دائماً
)

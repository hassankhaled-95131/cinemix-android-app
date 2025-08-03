package com.example.cinemix.domain.model

/**
 * يمثل نموذج بيانات "القسم" النظيف داخل التطبيق.
 * كل قسم يحتوي على عنوان ومجموعة من الأفلام المتعلقة به.
 */
data class Section(
    val id: Int,
    val title: String,
    val movies: List<Movie>
)

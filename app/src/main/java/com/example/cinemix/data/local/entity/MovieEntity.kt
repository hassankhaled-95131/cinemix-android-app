package com.example.cinemix.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * يمثل كيان (جدول) الفيلم في قاعدة بيانات Room المحلية.
 * يستخدم لتخزين بيانات الأفلام مؤقتاً على الجهاز (Caching).
 *
 * @param typeTag يُستخدم لتمييز نوع القائمة التي ينتمي إليها الفيلم (e.g., "popular", "trending").
 * هذا يسمح لنا بجلب قوائم مختلفة من نفس الجدول.
 */
@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val voteAverage: Double,
    val releaseDate: String,
    val mediaType: String,
    val typeTag: String // e.g., "popular_movie", "popular_tv"
)

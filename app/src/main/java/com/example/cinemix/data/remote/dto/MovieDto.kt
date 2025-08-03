package com.example.cinemix.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * هذا الملف يحتوي على كائنات نقل البيانات (DTOs) المستخدمة لجلب محتوى الأفلام والفئات.
 */

// --- DTOs for Movies & Sections ---

data class MovieTMDbDto(
    val id: Int,
    val title: String?,
    val name: String?,
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("first_air_date")
    val firstAirDate: String?,
    @SerializedName("media_type")
    val mediaType: String?
)

data class SectionDto(
    val id: Int,
    val title: String,
    val movies: List<MovieTMDbDto>
)

// --- DTOs for Categories / Genres ---

/**
 * يمثل كائن الفئة (Genre) كما هو قادم من TMDB.
 */
data class GenreDto(
    val id: Int,
    val name: String
)

/**
 * يمثل الاستجابة الكاملة لقائمة الفئات من TMDB.
 */
data class GenreListResponseDto(
    val genres: List<GenreDto>
)

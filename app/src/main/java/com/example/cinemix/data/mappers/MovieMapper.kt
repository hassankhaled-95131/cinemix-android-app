package com.example.cinemix.data.mappers

import com.example.cinemix.data.local.entity.MovieEntity
import com.example.cinemix.data.remote.dto.GenreDto
import com.example.cinemix.data.remote.dto.MovieTMDbDto
import com.example.cinemix.domain.model.Category
import com.example.cinemix.domain.model.Movie

/**
 * ملف يحتوي على دوال تحويل (Mapping Functions) لنماذج البيانات.
 * هذه الدوال مسؤولة عن تحويل كائنات البيانات بين طبقات التطبيق المختلفة.
 */

// --- التحويل من DTO (البيانات القادمة من الشبكة) ---

fun MovieTMDbDto.toMovieEntity(typeTag: String): MovieEntity {
    return MovieEntity(
        id = this.id,
        title = this.title ?: this.name ?: "No Title",
        overview = this.overview ?: "No Overview Available.",
        posterPath = this.posterPath ?: "",
        backdropPath = this.backdropPath ?: "",
        voteAverage = this.voteAverage ?: 0.0,
        releaseDate = this.releaseDate ?: this.firstAirDate ?: "N/A",
        mediaType = this.mediaType ?: "movie",
        typeTag = typeTag
    )
}

fun MovieTMDbDto.toMovie(): Movie {
    return Movie(
        id = this.id,
        title = this.title ?: this.name ?: "No Title",
        overview = this.overview ?: "No Overview Available.",
        posterPath = this.posterPath ?: "",
        backdropPath = this.backdropPath ?: "",
        voteAverage = this.voteAverage ?: 0.0,
        releaseDate = this.releaseDate ?: this.firstAirDate ?: "N/A",
        mediaType = this.mediaType ?: "movie",
        genres = emptyList(),
        runtime = null
    )
}

/**
 * تحويل كائن DTO لفئة (Genre) إلى نموذج بيانات نظيف (Domain Model).
 */
fun GenreDto.toCategory(): Category {
    return Category(
        id = this.id,
        name = this.name
    )
}


// --- التحويل من Entity (البيانات القادمة من قاعدة البيانات المحلية) ---

fun MovieEntity.toMovie(): Movie {
    return Movie(
        id = this.id,
        title = this.title,
        overview = this.overview,
        posterPath = this.posterPath,
        backdropPath = this.backdropPath,
        voteAverage = this.voteAverage,
        releaseDate = this.releaseDate,
        mediaType = this.mediaType,
        genres = emptyList(),
        runtime = null
    )
}

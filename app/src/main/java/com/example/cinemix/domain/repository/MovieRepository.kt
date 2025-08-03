package com.example.cinemix.domain.repository

import com.example.cinemix.domain.model.Category
import com.example.cinemix.domain.model.Movie
import com.example.cinemix.util.Resource
import kotlinx.coroutines.flow.Flow

/**
 * الواجهة المحدثة لمستودع بيانات الأفلام.
 */
interface MovieRepository {

    // --- TMDB Movie Lists ---
    fun getPopularMovies(page: Int): Flow<Resource<List<Movie>>>
    fun getPopularTvShows(page: Int): Flow<Resource<List<Movie>>>
    fun getTopRatedMovies(page: Int): Flow<Resource<List<Movie>>>
    fun getUpcomingMovies(page: Int): Flow<Resource<List<Movie>>>
    fun searchMovies(query: String, page: Int): Flow<Resource<List<Movie>>>
    fun getMovieDetails(movieId: Int): Flow<Resource<Movie>>

    // --- User Favorites ---
    fun getFavoriteMovieIds(): Flow<List<Int>>
    suspend fun addFavorite(movieId: Int)
    suspend fun removeFavorite(movieId: Int)

    // --- Categories / Genres ---
    fun getCategories(): Flow<Resource<List<Category>>>
    /**
     * جلب قائمة الأفلام لفئة معينة.
     * @param categoryId معرّف الفئة.
     * @param page رقم الصفحة.
     */
    fun getMoviesByCategory(categoryId: Int, page: Int): Flow<Resource<List<Movie>>>
}

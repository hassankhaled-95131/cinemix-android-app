package com.example.cinemix.data.remote

import com.example.cinemix.data.remote.dto.GenreListResponseDto
import com.example.cinemix.data.remote.dto.MovieTMDbDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * واجهة Retrofit المعدلة التي تحدد نقاط نهاية TMDB API مباشرة.
 */
interface ApiService {

    // --- TMDB API Endpoints ---

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "ar-SA",
        @Query("page") page: Int = 1
    ): Response<TmdbResponseDto>

    @GET("tv/popular")
    suspend fun getPopularTvShows(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "ar-SA",
        @Query("page") page: Int = 1
    ): Response<TmdbResponseDto>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "ar-SA",
        @Query("page") page: Int = 1
    ): Response<TmdbResponseDto>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "ar-SA",
        @Query("page") page: Int = 1
    ): Response<TmdbResponseDto>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "ar-SA",
        @Query("append_to_response") appendToResponse: String = "credits,videos"
    ): Response<MovieTMDbDto>

    @GET("search/multi")
    suspend fun search(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("language") language: String = "ar-SA",
        @Query("page") page: Int = 1
    ): Response<TmdbResponseDto>

    @GET("genre/movie/list")
    suspend fun getMovieGenres(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "ar"
    ): Response<GenreListResponseDto>

    @GET("discover/movie")
    suspend fun getMoviesByCategory(
        @Query("api_key") apiKey: String,
        @Query("with_genres") categoryId: Int,
        @Query("language") language: String = "ar-SA",
        @Query("page") page: Int = 1
    ): Response<TmdbResponseDto>


    /**
     * كائن DTO عام لاستقبال الاستجابات التي تحتوي على قائمة نتائج من TMDB.
     */
    data class TmdbResponseDto(
        val page: Int,
        val results: List<MovieTMDbDto>,
        @SerializedName("total_pages")
        val totalPages: Int,
        @SerializedName("total_results")
        val totalResults: Int
    )
}

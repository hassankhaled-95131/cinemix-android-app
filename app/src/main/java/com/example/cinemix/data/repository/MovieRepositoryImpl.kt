package com.example.cinemix.data.repository

import com.example.cinemix.data.local.MovieDao
import com.example.cinemix.data.local.entity.FavoriteEntity
import com.example.cinemix.data.mappers.toCategory
import com.example.cinemix.data.mappers.toMovie
import com.example.cinemix.data.mappers.toMovieEntity
import com.example.cinemix.data.remote.ApiService
import com.example.cinemix.domain.model.Category
import com.example.cinemix.domain.model.Movie
import com.example.cinemix.domain.repository.MovieRepository
import com.example.cinemix.util.Constants
import com.example.cinemix.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException

/**
 * التنفيذ الفعلي لواجهة MovieRepository.
 * هذه الفئة هي المسؤولة عن تنسيق جلب البيانات من مصادرها المختلفة (الشبكة وقاعدة البيانات المحلية).
 *
 * @param api Service للوصول إلى TMDB API.
 * @param dao DAO للوصول إلى قاعدة بيانات Room المحلية.
 */
class MovieRepositoryImpl(
    private val api: ApiService,
    private val dao: MovieDao
) : MovieRepository {

    private fun getMovieList(
        typeTag: String,
        fetchFromApi: suspend () -> List<com.example.cinemix.data.remote.dto.MovieTMDbDto>
    ): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        val cachedMovies = dao.getMoviesByType(typeTag).map { it.toMovie() }
        emit(Resource.Loading(data = cachedMovies))

        try {
            val remoteMoviesDto = fetchFromApi()
            dao.clearMoviesByType(typeTag)
            dao.insertMovies(remoteMoviesDto.map { it.toMovieEntity(typeTag) })
            val newMovies = dao.getMoviesByType(typeTag).map { it.toMovie() }
            emit(Resource.Success(data = newMovies))
        } catch (e: HttpException) {
            emit(Resource.Error("حدث خطأ غير متوقع!", data = cachedMovies))
        } catch (e: IOException) {
            emit(Resource.Error("لا يمكن الوصول للخادم, يرجى التحقق من اتصالك بالإنترنت.", data = cachedMovies))
        }
    }

    override fun getPopularMovies(page: Int): Flow<Resource<List<Movie>>> {
        return getMovieList("popular_movie") {
            api.getPopularMovies(Constants.TMDB_API_KEY, page = page).body()?.results ?: emptyList()
        }
    }

    override fun getPopularTvShows(page: Int): Flow<Resource<List<Movie>>> {
        return getMovieList("popular_tv") {
            api.getPopularTvShows(Constants.TMDB_API_KEY, page = page).body()?.results ?: emptyList()
        }
    }

    override fun getTopRatedMovies(page: Int): Flow<Resource<List<Movie>>> {
        return getMovieList("top_rated_movie") {
            api.getTopRatedMovies(Constants.TMDB_API_KEY, page = page).body()?.results ?: emptyList()
        }
    }

    override fun getUpcomingMovies(page: Int): Flow<Resource<List<Movie>>> {
        return getMovieList("upcoming_movie") {
            api.getUpcomingMovies(Constants.TMDB_API_KEY, page = page).body()?.results ?: emptyList()
        }
    }

    override fun searchMovies(query: String, page: Int): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val searchResult = api.search(Constants.TMDB_API_KEY, query, page = page).body()
                val movies = searchResult?.results?.map { it.toMovie() } ?: emptyList()
                emit(Resource.Success(movies))
            } catch (e: HttpException) {
                emit(Resource.Error("حدث خطأ غير متوقع!"))
            } catch (e: IOException) {
                emit(Resource.Error("لا يمكن الوصول للخادم, يرجى التحقق من اتصالك بالإنترنت."))
            }
        }
    }

    override fun getMovieDetails(movieId: Int): Flow<Resource<Movie>> {
        return flow {
            emit(Resource.Loading())
            try {
                val movieDetailsDto = api.getMovieDetails(movieId, Constants.TMDB_API_KEY).body()
                if (movieDetailsDto != null) {
                    emit(Resource.Success(movieDetailsDto.toMovie()))
                } else {
                    emit(Resource.Error("لم يتم العثور على الفيلم."))
                }
            } catch (e: HttpException) {
                emit(Resource.Error("حدث خطأ غير متوقع!"))
            } catch (e: IOException) {
                emit(Resource.Error("لا يمكن الوصول للخادم, يرجى التحقق من اتصالك بالإنترنت."))
            }
        }
    }

    // --- Favorites Implementation ---
    override fun getFavoriteMovieIds(): Flow<List<Int>> {
        return dao.getAllFavorites().map { favoriteEntities ->
            favoriteEntities.map { it.movieId }
        }
    }

    override suspend fun addFavorite(movieId: Int) {
        dao.insertFavorite(FavoriteEntity(movieId = movieId))
    }

    override suspend fun removeFavorite(movieId: Int) {
        dao.deleteFavorite(movieId = movieId)
    }

    // --- Categories Implementation ---
    override fun getCategories(): Flow<Resource<List<Category>>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.getMovieGenres(Constants.TMDB_API_KEY)
            if (response.isSuccessful && response.body() != null) {
                val categories = response.body()!!.genres.map { it.toCategory() }
                emit(Resource.Success(categories))
            } else {
                emit(Resource.Error("فشل في تحميل الفئات: ${response.message()}"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error("حدث خطأ غير متوقع!"))
        } catch (e: IOException) {
            emit(Resource.Error("لا يمكن الوصول للخادم, يرجى التحقق من اتصالك بالإنترنت."))
        }
    }

    override fun getMoviesByCategory(categoryId: Int, page: Int): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.getMoviesByCategory(
                apiKey = Constants.TMDB_API_KEY,
                categoryId = categoryId,
                page = page
            )
            if (response.isSuccessful && response.body() != null) {
                val movies = response.body()!!.results.map { it.toMovie() }
                emit(Resource.Success(movies))
            } else {
                emit(Resource.Error("فشل في تحميل الأفلام: ${response.message()}"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error("حدث خطأ غير متوقع!"))
        } catch (e: IOException) {
            emit(Resource.Error("لا يمكن الوصول للخادم, يرجى التحقق من اتصالك بالإنترنت."))
        }
    }
}

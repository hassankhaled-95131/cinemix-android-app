package com.example.cinemix.domain.use_case

import com.example.cinemix.domain.model.Movie
import com.example.cinemix.domain.repository.MovieRepository
import com.example.cinemix.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * حالة استخدام (Use Case) لجلب قائمة الأفلام المفضلة الكاملة.
 *
 * @param movieRepository المستودع لجلب معرفات الأفلام المفضلة وتفاصيل كل فيلم.
 */
class GetFavoritesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    /**
     * عند استدعاء هذه الفئة كدالة، ستقوم بتنفيذ حالة الاستخدام.
     * تجلب معرفات الأفلام المفضلة، ثم تجلب تفاصيل كل فيلم.
     * @return Flow يرسل حالة العملية مع قائمة الأفلام المفضلة.
     */
    operator fun invoke(): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        try {
            // جلب معرفات الأفلام من المستودع الصحيح
            val favoriteIds = movieRepository.getFavoriteMovieIds().first()
            val favoriteMovies = mutableListOf<Movie>()

            // جلب تفاصيل كل فيلم في القائمة
            for (id in favoriteIds) {
                // استخدام .first() لجلب النتيجة الأخيرة من الـ Flow
                val movieResource = movieRepository.getMovieDetails(id).first()
                if (movieResource is Resource.Success) {
                    movieResource.data?.let { favoriteMovies.add(it) }
                } else if (movieResource is Resource.Error) {
                    // إذا فشل جلب فيلم واحد، يمكن تسجيل الخطأ والمتابعة
                    println("Could not fetch details for favorite movie ID: $id")
                }
            }
            emit(Resource.Success(favoriteMovies))

        } catch (e: Exception) {
            emit(Resource.Error("فشل في تحميل قائمة المفضلة: ${e.localizedMessage}"))
        }
    }
}

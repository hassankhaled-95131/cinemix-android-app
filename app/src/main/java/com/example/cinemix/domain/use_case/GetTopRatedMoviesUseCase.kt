package com.example.cinemix.domain.use_case

import com.example.cinemix.domain.model.Movie
import com.example.cinemix.domain.repository.MovieRepository
import com.example.cinemix.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * حالة استخدام (Use Case) لجلب قائمة الأفلام الأعلى تقييماً.
 * تعتمد على واجهة MovieRepository لجلب البيانات.
 *
 * @param repository المستودع الذي سيتم استخدامه لجلب البيانات.
 */
class GetTopRatedMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    /**
     * عند استدعاء هذه الفئة كدالة، ستقوم بتنفيذ حالة الاستخدام.
     * @param page رقم الصفحة المطلوب جلبها من API.
     * @return Flow يرسل حالة العملية مع قائمة الأفلام.
     */
    operator fun invoke(page: Int): Flow<Resource<List<Movie>>> {
        return repository.getTopRatedMovies(page)
    }
}

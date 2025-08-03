package com.example.cinemix.domain.use_case

import com.example.cinemix.domain.model.Movie
import com.example.cinemix.domain.repository.MovieRepository
import com.example.cinemix.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * حالة استخدام (Use Case) لجلب قائمة الأفلام لفئة معينة.
 *
 * @param repository المستودع المسؤول عن جلب البيانات.
 */
class GetMoviesByCategoryUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    /**
     * عند استدعاء هذه الفئة كدالة، ستقوم بتنفيذ حالة الاستخدام.
     * @param categoryId معرّف الفئة المطلوب جلب أفلامها.
     * @return Flow يرسل حالة العملية مع قائمة الأفلام.
     */
    operator fun invoke(categoryId: Int, page: Int): Flow<Resource<List<Movie>>> {
        return repository.getMoviesByCategory(categoryId, page)
    }
}

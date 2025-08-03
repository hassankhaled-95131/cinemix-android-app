package com.example.cinemix.domain.use_case

import com.example.cinemix.domain.model.Movie
import com.example.cinemix.domain.repository.MovieRepository
import com.example.cinemix.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * حالة استخدام (Use Case) لجلب قائمة الأفلام الشائعة.
 * هذه الفئة تحتوي على منطق عمل واحد ومحدد.
 * تعتمد على واجهة MovieRepository لجلب البيانات، مما يفصلها عن تفاصيل تنفيذ جلب البيانات.
 *
 * @param repository المستودع الذي سيتم استخدامه لجلب البيانات.
 */
class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    /**
     * عند استدعاء هذه الفئة كدالة، ستقوم بتنفيذ حالة الاستخدام.
     * @param page رقم الصفحة المطلوب جلبها من API.
     * @return Flow يرسل حالة العملية مع قائمة الأفلام.
     */
    operator fun invoke(page: Int): Flow<Resource<List<Movie>>> {
        return repository.getPopularMovies(page)
    }
}

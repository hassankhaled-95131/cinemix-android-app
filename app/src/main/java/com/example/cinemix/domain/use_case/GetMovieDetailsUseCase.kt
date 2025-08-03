package com.example.cinemix.domain.use_case

import com.example.cinemix.domain.model.Movie
import com.example.cinemix.domain.repository.MovieRepository
import com.example.cinemix.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * حالة استخدام (Use Case) لجلب تفاصيل فيلم أو مسلسل معين.
 * تعتمد على واجهة MovieRepository لجلب البيانات.
 *
 * @param repository المستودع الذي سيتم استخدامه لجلب البيانات.
 */
class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    /**
     * عند استدعاء هذه الفئة كدالة، ستقوم بتنفيذ حالة الاستخدام.
     * @param movieId معرّف الفيلم أو المسلسل المطلوب جلب تفاصيله.
     * @return Flow يرسل حالة العملية مع كائن الفيلم الكامل.
     */
    operator fun invoke(movieId: Int): Flow<Resource<Movie>> {
        return repository.getMovieDetails(movieId)
    }
}

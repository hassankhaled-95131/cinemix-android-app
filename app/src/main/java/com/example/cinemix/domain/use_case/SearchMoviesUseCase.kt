package com.example.cinemix.domain.use_case

import com.example.cinemix.domain.model.Movie
import com.example.cinemix.domain.repository.MovieRepository
import com.example.cinemix.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * حالة استخدام (Use Case) للبحث عن الأفلام والمسلسلات.
 * تعتمد على واجهة MovieRepository لتنفيذ عملية البحث.
 *
 * @param repository المستودع الذي سيتم استخدامه لجلب البيانات.
 */
class SearchMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    /**
     * عند استدعاء هذه الفئة كدالة، ستقوم بتنفيذ حالة الاستخدام.
     * @param query نص البحث الذي أدخله المستخدم.
     * @param page رقم الصفحة المطلوب جلبها من API.
     * @return Flow يرسل حالة العملية مع قائمة بنتائج البحث.
     */
    operator fun invoke(query: String, page: Int): Flow<Resource<List<Movie>>> {
        // يمكن إضافة منطق هنا للتحقق من أن نص البحث ليس فارغاً
        if (query.isBlank()) {
            // يمكنك إرجاع قائمة فارغة أو خطأ حسب متطلبات التطبيق
        }
        return repository.searchMovies(query, page)
    }
}

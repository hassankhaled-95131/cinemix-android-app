package com.example.cinemix.domain.use_case

import com.example.cinemix.domain.model.Category
import com.example.cinemix.domain.repository.MovieRepository
import com.example.cinemix.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * حالة استخدام (Use Case) لجلب قائمة فئات الأفلام.
 *
 * @param repository المستودع المسؤول عن جلب البيانات.
 */
class GetCategoriesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    /**
     * عند استدعاء هذه الفئة كدالة، ستقوم بتنفيذ حالة الاستخدام.
     * @return Flow يرسل حالة العملية مع قائمة الفئات.
     */
    operator fun invoke(): Flow<Resource<List<Category>>> {
        // return repository.getCategories() // سنضيف هذه الدالة لاحقاً إلى المستودع
        // تنفيذ مؤقت
        return kotlinx.coroutines.flow.flow { }
    }
}

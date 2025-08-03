package com.example.cinemix.domain.use_case

import com.example.cinemix.domain.repository.AuthRepository
import javax.inject.Inject

/**
 * حالة استخدام (Use Case) لإضافة أو إزالة فيلم من المفضلة.
 *
 * @param repository المستودع المسؤول عن تنفيذ العملية.
 */
class ToggleFavoriteUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    /**
     * عند استدعاء هذه الفئة كدالة، ستقوم بتنفيذ حالة الاستخدام.
     * @param movieId معرّف الفيلم.
     * @param isCurrentlyFavorite هل الفيلم موجود حالياً في المفضلة.
     */
    suspend operator fun invoke(movieId: Int, isCurrentlyFavorite: Boolean) {
        if (isCurrentlyFavorite) {
            // repository.removeFavorite(movieId) // سنضيف هذه الدالة لاحقاً
        } else {
            // repository.addFavorite(movieId) // سنضيف هذه الدالة لاحقاً
        }
    }
}

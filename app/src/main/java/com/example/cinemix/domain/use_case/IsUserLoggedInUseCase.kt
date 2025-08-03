package com.example.cinemix.domain.use_case

import com.example.cinemix.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * حالة استخدام (Use Case) للتحقق مما إذا كان المستخدم مسجلاً دخوله.
 *
 * @param repository المستودع المسؤول عن إدارة حالة المصادقة.
 */
class IsUserLoggedInUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    /**
     * عند استدعاء هذه الفئة كدالة، ستقوم بتنفيذ حالة الاستخدام.
     * @return Flow يرسل 'true' إذا كان المستخدم مسجلاً دخوله، و 'false' خلاف ذلك.
     */
    operator fun invoke(): Flow<Boolean> {
        return repository.isLoggedIn()
    }
}

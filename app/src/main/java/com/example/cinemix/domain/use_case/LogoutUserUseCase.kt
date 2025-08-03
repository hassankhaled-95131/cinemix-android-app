package com.example.cinemix.domain.use_case

import com.example.cinemix.domain.repository.AuthRepository
import javax.inject.Inject

/**
 * حالة استخدام (Use Case) لتسجيل خروج المستخدم.
 *
 * @param repository المستودع المسؤول عن تنفيذ عملية المصادقة.
 */
class LogoutUserUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    /**
     * عند استدعاء هذه الفئة كدالة، ستقوم بتنفيذ حالة الاستخدام.
     * إنها دالة معلقة (suspend) لأنها تستدعي دالة معلقة أخرى في المستودع.
     */
    suspend operator fun invoke() {
        repository.logout()
    }
}

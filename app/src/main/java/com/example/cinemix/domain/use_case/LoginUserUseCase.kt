package com.example.cinemix.domain.use_case

import com.example.cinemix.domain.repository.AuthRepository
import com.example.cinemix.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * حالة استخدام (Use Case) لتسجيل دخول المستخدم.
 *
 * @param repository المستودع المسؤول عن تنفيذ عملية المصادقة.
 */
class LoginUserUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    /**
     * عند استدعاء هذه الفئة كدالة، ستقوم بتنفيذ حالة الاستخدام.
     * @param username اسم المستخدم.
     * @param password كلمة المرور.
     * @return Flow يرسل حالة عملية تسجيل الدخول.
     */
    operator fun invoke(username: String, password: String): Flow<Resource<Unit>> {
        if (username.isBlank() || password.isBlank()) {
            // يمكنك إضافة تحقق مبدئي هنا إذا أردت
        }
        return repository.login(username, password)
    }
}

package com.example.cinemix.domain.use_case

import com.example.cinemix.domain.repository.AuthRepository
import com.example.cinemix.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * حالة استخدام (Use Case) لتسجيل مستخدم جديد.
 *
 * @param repository المستودع المسؤول عن تنفيذ عملية المصادقة.
 */
class RegisterUserUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    /**
     * عند استدعاء هذه الفئة كدالة، ستقوم بتنفيذ حالة الاستخدام.
     * @param username اسم المستخدم.
     * @param email البريد الإلكتروني.
     * @param password كلمة المرور.
     * @return Flow يرسل حالة عملية التسجيل.
     */
    operator fun invoke(username: String, email: String, password: String): Flow<Resource<Unit>> {
        // يمكنك إضافة تحقق من صحة البيانات هنا (مثل التأكد من أن البريد الإلكتروني صالح)
        if (username.isBlank() || email.isBlank() || password.length < 6) {
            // return flow { emit(Resource.Error("البيانات غير صالحة.")) }
        }
        return repository.register(username, email, password)
    }
}

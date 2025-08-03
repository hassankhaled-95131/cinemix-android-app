package com.example.cinemix.data.repository

import com.example.cinemix.data.local.UserPreferences
import com.example.cinemix.domain.repository.AuthRepository
import com.example.cinemix.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * التنفيذ الفعلي لواجهة AuthRepository.
 * هذه الفئة مسؤولة عن إدارة حالة المصادقة للمستخدم.
 *
 * @param userPreferences للوصول إلى DataStore لحفظ واسترجاع حالة تسجيل الدخول.
 */
class AuthRepositoryImpl(
    private val userPreferences: UserPreferences
) : AuthRepository {

    override fun login(username: String, password: String): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        delay(1000) // محاكاة لطلب الشبكة

        if (username.isNotBlank() && password.isNotBlank()) {
            // في تطبيق حقيقي، ستقوم هنا بإجراء طلب الشبكة
            // وبناءً على الاستجابة، ستقوم بحفظ الحالة.
            userPreferences.saveLoginState(true)
            emit(Resource.Success(Unit))
        } else {
            emit(Resource.Error("اسم المستخدم أو كلمة المرور لا يمكن أن تكون فارغة."))
        }
    }

    override fun register(username: String, email: String, password: String): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        delay(1500) // محاكاة لطلب الشبكة

        if (username.isBlank() || email.isBlank() || password.isBlank()) {
            emit(Resource.Error("يرجى ملء جميع الحقول."))
            return@flow
        }
        if (password.length < 6) {
            emit(Resource.Error("يجب أن تتكون كلمة المرور من 6 أحرف على الأقل."))
            return@flow
        }
        // في تطبيق حقيقي، ستقوم هنا بإرسال البيانات إلى الخادم لإنشاء الحساب.
        // بعد النجاح، ستقوم بتسجيل دخول المستخدم تلقائياً.
        userPreferences.saveLoginState(true)
        emit(Resource.Success(Unit))
    }

    override suspend fun logout() {
        // مسح حالة تسجيل الدخول المحفوظة.
        userPreferences.saveLoginState(false)
    }

    override fun isLoggedIn(): Flow<Boolean> {
        // إرجاع Flow مباشرة من DataStore لمراقبة حالة تسجيل الدخول.
        return userPreferences.isLoggedInFlow
    }
}

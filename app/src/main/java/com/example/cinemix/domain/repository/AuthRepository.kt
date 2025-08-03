package com.example.cinemix.domain.repository

import com.example.cinemix.util.Resource
import kotlinx.coroutines.flow.Flow

/**
 * واجهة (Interface) لمستودع بيانات المصادقة.
 * تحدد العمليات لإدارة جلسة المستخدم.
 */
interface AuthRepository {

    /**
     * محاولة تسجيل دخول مستخدم.
     * @param username اسم المستخدم.
     * @param password كلمة المرور.
     * @return Flow يرسل حالة العملية.
     */
    fun login(username: String, password: String): Flow<Resource<Unit>>

    /**
     * تسجيل حساب مستخدم جديد.
     * @param username اسم المستخدم.
     * @param email البريد الإلكتروني.
     * @param password كلمة المرور.
     * @return Flow يرسل حالة العملية.
     */
    fun register(username: String, email: String, password: String): Flow<Resource<Unit>>

    /**
     * تسجيل خروج المستخدم الحالي.
     */
    suspend fun logout()

    /**
     * التحقق من حالة تسجيل دخول المستخدم عند بدء تشغيل التطبيق.
     * @return Flow يرسل 'true' إذا كان المستخدم مسجلاً دخوله، و 'false' خلاف ذلك.
     */
    fun isLoggedIn(): Flow<Boolean>
}

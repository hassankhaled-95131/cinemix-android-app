package com.example.cinemix.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * هذا الملف يحتوي على كائنات نقل البيانات (DTOs) المستخدمة في عمليات المصادقة.
 * هذه الكائنات تطابق بنية JSON التي يتم إرسالها واستقبالها من الواجهة الخلفية (auth.php).
 */

// 1. كائنات الطلبات (Requests) - البيانات التي يرسلها التطبيق إلى الخادم

data class LoginRequestDto(
    val username: String,
    val password: String
)

data class RegisterRequestDto(
    val username: String,
    val email: String,
    val password: String
)

data class VerifyRequestDto(
    val email: String,
    val code: String
)

data class FavoriteRequestDto(
    @SerializedName("movie_id") // لمطابقة اسم الحقل في PHP
    val movieId: Int
)


// 2. كائنات الاستجابات (Responses) - البيانات التي يستقبلها التطبيق من الخادم

/**
 * استجابة عامة للعمليات التي لا ترجع بيانات محددة (مثل التسجيل، التحقق، إضافة للمفضلة).
 */
data class GenericAuthResponseDto(
    val success: Boolean,
    val message: String,
    val requiresVerification: Boolean? = null, // قد لا تكون موجودة دائماً
    val email: String? = null
)

/**
 * الاستجابة عند نجاح تسجيل الدخول أو التحقق من الحالة.
 */
data class LoginResponseDto(
    val success: Boolean,
    val user: UserDto,
    val favorites: List<Int>
)

/**
 * الاستجابة عند التحقق من حالة تسجيل الدخول.
 */
data class AuthStatusResponseDto(
    val loggedIn: Boolean,
    val user: UserDto?,
    val favorites: List<Int>?
)

/**
 * كائن يمثل بيانات المستخدم كما هي قادمة من الخادم.
 */
data class UserDto(
    val id: Int,
    val username: String
)

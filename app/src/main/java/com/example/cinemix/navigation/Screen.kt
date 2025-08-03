package com.example.cinemix.navigation

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

/**
 * فئة مغلقة (Sealed Class) لتعريف مسارات التنقل (Routes) لجميع الشاشات في التطبيق.
 * هذا يضمن أن جميع المسارات محددة في مكان واحد ويوفر أماناً من الأخطاء الكتابية (Type Safety).
 */
sealed class Screen(val route: String) {
    /**
     * يمثل شاشة البداية (Splash Screen).
     */
    object Splash : Screen("splash_screen")

    /**
     * يمثل شاشة تسجيل الدخول.
     */
    object Login : Screen("login_screen")

    /**
     * يمثل شاشة إنشاء حساب جديد.
     */
    object Register : Screen("register_screen")

    /**
     * يمثل الشاشة الرئيسية.
     */
    object Home : Screen("home_screen")

    /**
     * يمثل شاشة البحث.
     */
    object Search : Screen("search_screen")

    /**
     * يمثل شاشة المفضلة.
     */
    object Favorites : Screen("favorites_screen")

    /**
     * يمثل شاشة الفئات.
     */
    object Categories : Screen("categories_screen")

    /**
     * يمثل شاشة الملف الشخصي.
     */
    object Profile : Screen("profile_screen")

    /**
     * يمثل شاشة عرض قائمة أفلام عامة (مثل: الشائعة، الأعلى تقييماً).
     */
    object MovieList : Screen("movie_list_screen/{listType}/{listTitle}") {
        fun createRoute(listType: String, listTitle: String): String {
            val encodedTitle = URLEncoder.encode(listTitle, StandardCharsets.UTF_8.toString())
            return "movie_list_screen/$listType/$encodedTitle"
        }
    }

    /**
     * يمثل شاشة عرض الأفلام لفئة معينة.
     */
    object CategoryMovies : Screen("category_movies_screen/{categoryId}/{categoryName}") {
        fun createRoute(categoryId: Int, categoryName: String) =
            "category_movies_screen/$categoryId/$categoryName"
    }

    /**
     * يمثل شاشة تفاصيل الفيلم.
     */
    object Details : Screen("details_screen/{movieId}") {
        fun createRoute(movieId: Int) = "details_screen/$movieId"
    }

    /**
     * يمثل شاشة مشغل الفيديو.
     */
    object Player : Screen("player_screen/{movieId}") {
        fun createRoute(movieId: Int) = "player_screen/$movieId"
    }
}

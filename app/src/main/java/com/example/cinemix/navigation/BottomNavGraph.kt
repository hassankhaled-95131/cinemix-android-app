package com.example.cinemix.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cinemix.ui.screens.categories.CategoriesScreen
import com.example.cinemix.ui.screens.favorites.FavoritesScreen
import com.example.cinemix.ui.screens.home.HomeScreen
import com.example.cinemix.ui.screens.profile.ProfileScreen
import com.example.cinemix.ui.screens.search.SearchScreen

/**
 * مخطط تنقل (Navigation Graph) مخصص للشاشات الموجودة في شريط التنقل السفلي.
 *
 * @param mainNavController وحدة التحكم الرئيسية للتطبيق، للانتقال إلى شاشات خارج هذا المخطط (مثل التفاصيل).
 * @param bottomNavController وحدة التحكم الخاصة بالشاشات السفلية فقط.
 */
@Composable
fun BottomNavGraph(
    mainNavController: NavHostController,
    bottomNavController: NavHostController
) {
    NavHost(
        navController = bottomNavController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                onMovieClick = { movieId ->
                    mainNavController.navigate(Screen.Details.createRoute(movieId))
                }
            )
        }
        composable(route = Screen.Search.route) {
            SearchScreen(
                onMovieClick = { movieId ->
                    mainNavController.navigate(Screen.Details.createRoute(movieId))
                }
            )
        }
        composable(route = Screen.Categories.route) {
            CategoriesScreen(
                onCategoryClick = { categoryId, categoryName ->
                    mainNavController.navigate(Screen.CategoryMovies.createRoute(categoryId, categoryName))
                }
            )
        }
        composable(route = Screen.Favorites.route) {
            FavoritesScreen(
                onMovieClick = { movieId ->
                    mainNavController.navigate(Screen.Details.createRoute(movieId))
                }
            )
        }
        composable(route = Screen.Profile.route) {
            ProfileScreen(
                onLogout = {
                    // العودة إلى شاشة تسجيل الدخول ومسح كل الشاشات السابقة
                    mainNavController.navigate(Screen.Login.route) {
                        popUpTo("main_graph") { inclusive = true }
                    }
                }
            )
        }
    }
}

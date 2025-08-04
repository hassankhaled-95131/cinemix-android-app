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
                },
                onNavigate = { route ->
                    mainNavController.navigate(route)
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
                    mainNavController.navigate(Screen.Login.route) {
                        popUpTo("main_graph") { inclusive = true }
                    }
                }
            )
        }
    }
}

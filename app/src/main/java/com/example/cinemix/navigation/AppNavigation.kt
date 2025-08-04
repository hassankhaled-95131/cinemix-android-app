package com.example.cinemix.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cinemix.ui.screens.auth.LoginScreen
import com.example.cinemix.ui.screens.auth.RegisterScreen
import com.example.cinemix.ui.screens.category_movies.CategoryMoviesScreen
import com.example.cinemix.ui.screens.details.DetailsScreen
import com.example.cinemix.ui.screens.main.MainScreen
import com.example.cinemix.ui.screens.movie_list.MovieListScreen
import com.example.cinemix.ui.screens.player.PlayerScreen
import com.example.cinemix.ui.screens.splash.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        enterTransition = { fadeIn(animationSpec = tween(300)) },
        exitTransition = { fadeOut(animationSpec = tween(300)) }
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToHome = {
                    navController.navigate("main_graph") {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(route = Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("main_graph") {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(Screen.Register.route)
                }
            )
        }

        composable(
            route = Screen.Register.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(400)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(400)
                )
            }
        ) {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate("main_graph") {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = "main_graph") {
            MainScreen(mainNavController = navController)
        }

        val slideUpTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Up,
                animationSpec = tween(400)
            )
        }
        val slideDownTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Down,
                animationSpec = tween(400)
            )
        }

        composable(
            route = Screen.MovieList.route,
            arguments = listOf(
                navArgument("listType") { type = NavType.StringType },
                navArgument("listTitle") { type = NavType.StringType }
            ),
            enterTransition = slideUpTransition,
            exitTransition = slideDownTransition
        ) {
            MovieListScreen(
                onMovieClick = { movieId ->
                    navController.navigate(Screen.Details.createRoute(movieId))
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.CategoryMovies.route,
            arguments = listOf(
                navArgument("categoryId") { type = NavType.IntType },
                navArgument("categoryName") { type = NavType.StringType }
            ),
            enterTransition = slideUpTransition,
            exitTransition = slideDownTransition
        ) {
            CategoryMoviesScreen(
                onMovieClick = { movieId ->
                    navController.navigate(Screen.Details.createRoute(movieId))
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument("movieId") { type = NavType.IntType }),
            enterTransition = slideUpTransition,
            exitTransition = slideDownTransition
        ) {
            DetailsScreen(
                onNavigateBack = { navController.popBackStack() },
                onPlayClick = { movieId ->
                    navController.navigate(Screen.Player.createRoute(movieId))
                }
            )
        }

        composable(
            route = Screen.Player.route,
            arguments = listOf(navArgument("movieId") { type = NavType.IntType }),
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = { fadeOut(animationSpec = tween(500)) }
        ) {
            PlayerScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}

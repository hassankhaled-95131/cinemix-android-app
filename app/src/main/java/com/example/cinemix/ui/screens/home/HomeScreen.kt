package com.example.cinemix.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cinemix.navigation.Screen
import com.example.cinemix.ui.components.Loader
import com.example.cinemix.ui.components.SectionCarousel

/**
 * شاشة العرض الرئيسية للتطبيق (Composable).
 * تعرض هذه الشاشة قوائم الأفلام والمسلسلات المختلفة.
 *
 * @param viewModel ViewModel الخاص بالشاشة، يتم حقنه بواسطة Hilt.
 * @param onMovieClick دالة للانتقال إلى شاشة التفاصيل عند النقر على فيلم.
 * @param onNavigate دالة عامة للانتقال إلى أي مسار.
 */
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onMovieClick: (Int) -> Unit,
    onNavigate: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                if (uiState.popularMoviesList.isNotEmpty()) {
                    item {
                        SectionCarousel(
                            title = "الأفلام الشائعة",
                            movies = uiState.popularMoviesList,
                            onMovieClick = onMovieClick,
                            onViewAllClick = {
                                onNavigate(Screen.MovieList.createRoute("popular_movie", "الأفلام الشائعة"))
                            }
                        )
                    }
                }

                if (uiState.topRatedMoviesList.isNotEmpty()) {
                    item {
                        SectionCarousel(
                            title = "الأعلى تقييماً",
                            movies = uiState.topRatedMoviesList,
                            onMovieClick = onMovieClick,
                            onViewAllClick = {
                                onNavigate(Screen.MovieList.createRoute("top_rated_movie", "الأفلام الأعلى تقييماً"))
                            }
                        )
                    }
                }

                if (uiState.upcomingMoviesList.isNotEmpty()) {
                    item {
                        SectionCarousel(
                            title = "قادمة قريباً",
                            movies = uiState.upcomingMoviesList,
                            onMovieClick = onMovieClick,
                            onViewAllClick = {
                                onNavigate(Screen.MovieList.createRoute("upcoming_movie", "الأفلام القادمة قريباً"))
                            }
                        )
                    }
                }

                if (uiState.popularTvShowsList.isNotEmpty()) {
                    item {
                        SectionCarousel(
                            title = "المسلسلات الشائعة",
                            movies = uiState.popularTvShowsList,
                            onMovieClick = onMovieClick,
                            onViewAllClick = {
                                onNavigate(Screen.MovieList.createRoute("popular_tv", "المسلسلات الشائعة"))
                            }
                        )
                    }
                }
            }

            if (uiState.isLoading) {
                Loader()
            }

            if (uiState.error != null) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = uiState.error!!, color = Color.Red)
                }
            }
        }
    }
}

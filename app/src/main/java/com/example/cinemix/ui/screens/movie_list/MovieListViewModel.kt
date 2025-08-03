package com.example.cinemix.ui.screens.movie_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemix.domain.model.Movie
import com.example.cinemix.domain.use_case.GetPopularMoviesUseCase
import com.example.cinemix.domain.use_case.GetPopularTvShowsUseCase
import com.example.cinemix.domain.use_case.GetTopRatedMoviesUseCase
import com.example.cinemix.domain.use_case.GetUpcomingMoviesUseCase
import com.example.cinemix.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * ViewModel الخاص بشاشة عرض قائمة الأفلام.
 * مسؤول عن جلب قائمة الأفلام وإدارة الحالة (MovieListState) مع دعم Pagination.
 */
@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getPopularTvShowsUseCase: GetPopularTvShowsUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieListState())
    val uiState = _uiState.asStateFlow()

    val listTitle: String = savedStateHandle.get<String>("listTitle") ?: "قائمة الأفلام"
    private val listType: String? = savedStateHandle.get<String>("listType")

    init {
        loadNextPage()
    }

    fun loadNextPage() {
        if (_uiState.value.isLoading || !_uiState.value.canLoadMore || _uiState.value.isLoadingMore) {
            return
        }

        val pageToLoad = _uiState.value.page
        val useCaseFlow: Flow<Resource<List<Movie>>>? = when (listType) {
            "popular_movie" -> getPopularMoviesUseCase(pageToLoad)
            "popular_tv" -> getPopularTvShowsUseCase(pageToLoad)
            "top_rated_movie" -> getTopRatedMoviesUseCase(pageToLoad)
            "upcoming_movie" -> getUpcomingMoviesUseCase(pageToLoad)
            else -> null
        }

        useCaseFlow?.onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _uiState.update {
                        if (it.page == 1) it.copy(isLoading = true) else it.copy(isLoadingMore = true)
                    }
                }
                is Resource.Success -> {
                    val newMovies = result.data ?: emptyList()
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isLoadingMore = false,
                            movies = it.movies + newMovies,
                            page = it.page + 1,
                            canLoadMore = newMovies.isNotEmpty()
                        )
                    }
                }
                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isLoadingMore = false,
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }
                }
            }
        }?.launchIn(viewModelScope)
    }
}

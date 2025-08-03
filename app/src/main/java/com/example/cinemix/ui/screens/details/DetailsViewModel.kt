package com.example.cinemix.ui.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemix.domain.repository.MovieRepository
import com.example.cinemix.domain.use_case.GetMovieDetailsUseCase
import com.example.cinemix.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel الخاص بشاشة تفاصيل الفيلم.
 * مسؤول عن جلب بيانات فيلم معين وإدارة الحالة (DetailsState) لواجهة المستخدم،
 * بما في ذلك حالة المفضلة.
 */
@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val movieRepository: MovieRepository, // حقن المستودع مباشرة لإدارة المفضلة
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailsState())
    val uiState = _uiState.asStateFlow()

    private val movieId: Int = savedStateHandle.get<Int>("movieId")!!

    init {
        // جلب تفاصيل الفيلم ومراقبة حالة المفضلة في نفس الوقت
        getMovieDetails(movieId)
        observeFavoriteStatus(movieId)
    }

    private fun getMovieDetails(movieId: Int) {
        getMovieDetailsUseCase(movieId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            movie = result.data,
                            isLoading = false
                        )
                    }
                }
                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    /**
     * تراقب قائمة معرفات الأفلام المفضلة وتحدث حالة الواجهة بناءً عليها.
     */
    private fun observeFavoriteStatus(movieId: Int) {
        movieRepository.getFavoriteMovieIds().onEach { favoriteIds ->
            _uiState.update { it.copy(isFavorite = movieId in favoriteIds) }
        }.launchIn(viewModelScope)
    }

    /**
     * يتم استدعاؤها عند النقر على زر المفضلة.
     */
    fun onToggleFavorite() {
        viewModelScope.launch {
            val isCurrentlyFavorite = _uiState.value.isFavorite
            if (isCurrentlyFavorite) {
                movieRepository.removeFavorite(movieId)
            } else {
                movieRepository.addFavorite(movieId)
            }
        }
    }
}

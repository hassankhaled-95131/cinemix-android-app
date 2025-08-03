package com.example.cinemix.ui.screens.category_movies

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemix.domain.use_case.GetMoviesByCategoryUseCase
import com.example.cinemix.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * ViewModel الخاص بشاشة عرض الأفلام لفئة معينة.
 * مسؤول عن جلب قائمة الأفلام وإدارة الحالة (CategoryMoviesState) مع دعم Pagination.
 */
@HiltViewModel
class CategoryMoviesViewModel @Inject constructor(
    private val getMoviesByCategoryUseCase: GetMoviesByCategoryUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(CategoryMoviesState())
    val uiState = _uiState.asStateFlow()

    val categoryName: String = savedStateHandle.get<String>("categoryName") ?: "الفئة"
    private val categoryId: Int? = savedStateHandle.get<Int>("categoryId")

    init {
        // بدء تحميل الصفحة الأولى من البيانات
        loadNextPage()
    }

    fun loadNextPage() {
        // التأكد من أن categoryId ليس null وأنه يمكن تحميل المزيد وليس هناك عملية تحميل جارية
        categoryId?.let { id ->
            if (_uiState.value.isLoading || !_uiState.value.canLoadMore || _uiState.value.isLoadingMore) {
                return
            }

            getMoviesByCategoryUseCase(id, page = _uiState.value.page).onEach { result ->
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
                                movies = it.movies + newMovies, // إضافة الأفلام الجديدة إلى القائمة الحالية
                                page = it.page + 1,
                                canLoadMore = newMovies.isNotEmpty() // إذا كانت القائمة الجديدة فارغة، لا يمكن تحميل المزيد
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
            }.launchIn(viewModelScope)
        }
    }
}

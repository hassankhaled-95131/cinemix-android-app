package com.example.cinemix.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemix.domain.use_case.SearchMoviesUseCase
import com.example.cinemix.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel الخاص بشاشة البحث.
 * مسؤول عن إدارة منطق وحالة البحث مع دعم Pagination.
 */
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchState())
    val uiState = _uiState.asStateFlow()

    private var searchJob: Job? = null

    /**
     * دالة يتم استدعاؤها عند تغيير نص البحث في واجهة المستخدم.
     * @param query النص الجديد.
     */
    fun onSearchQueryChange(query: String) {
        // إعادة تعيين الحالة عند بدء بحث جديد
        _uiState.update { SearchState(searchQuery = query) }

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L) // Debouncing
            performSearch(isNewSearch = true)
        }
    }

    /**
     * دالة لتحميل الصفحة التالية من نتائج البحث.
     */
    fun loadNextPage() {
        if (_uiState.value.isLoading || !_uiState.value.canLoadMore || _uiState.value.isLoadingMore) {
            return
        }
        performSearch(isNewSearch = false)
    }

    private fun performSearch(isNewSearch: Boolean) {
        val currentState = _uiState.value
        if (currentState.searchQuery.isBlank()) {
            _uiState.update { it.copy(searchResults = emptyList(), error = null) }
            return
        }

        val pageToLoad = if (isNewSearch) 1 else currentState.page

        searchMoviesUseCase(currentState.searchQuery, page = pageToLoad).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _uiState.update {
                        if (isNewSearch) it.copy(isLoading = true) else it.copy(isLoadingMore = true)
                    }
                }
                is Resource.Success -> {
                    val newMovies = result.data ?: emptyList()
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isLoadingMore = false,
                            searchResults = if (isNewSearch) newMovies else it.searchResults + newMovies,
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
        }.launchIn(viewModelScope)
    }
}

package com.sarang.torang.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarang.torang.uistate.FeedMenuUiState
import com.sarang.torang.usecase.IsMyReviewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedMenuViewModel @Inject constructor(
    private val isMyReviewUseCase: IsMyReviewUseCase
) : ViewModel() {
    fun load(reviewId: Int) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isMine = isMyReviewUseCase.invoke(reviewId))
            }
        }
    }

    private val _uiState = MutableStateFlow(FeedMenuUiState())
    val uiState = _uiState.asStateFlow()
}
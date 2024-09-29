package com.sarang.torang.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarang.torang.uistate.ShareDialogUiState
import com.sarang.torang.usecase.GetFollowerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShareViewModel @Inject constructor(
    getFollowerUseCase: GetFollowerUseCase
) : ViewModel()
{
    private val _uiState = MutableStateFlow(ShareDialogUiState(list = ArrayList()))
    val uiState = _uiState.asStateFlow()

    init
    {
        viewModelScope.launch {
            try
            {
                _uiState.update {
                    it.copy(list = getFollowerUseCase.invoke())
                }
            } catch (e: Exception)
            {

            }
        }
    }
}
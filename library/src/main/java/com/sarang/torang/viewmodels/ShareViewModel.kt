package com.sarang.torang.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
    var uiState by mutableStateOf(ShareDialogUiState()); private set

    fun select(userId: Int) {
        uiState = uiState.copy(
            list = uiState.list.map {
                if(it.userId == userId) {
                    it.copy(isSelected = !it.isSelected)
                }
                else {
                    it
                }
            }
        )
    }

    init
    {
        viewModelScope.launch {
            try
            {
                uiState = uiState.copy(getFollowerUseCase.invoke())
            } catch (e: Exception)
            {

            }
        }
    }
}
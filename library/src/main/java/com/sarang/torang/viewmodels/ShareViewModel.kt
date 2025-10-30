package com.sarang.torang.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.graphics.convertTo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarang.torang.uistate.ShareDialogUiState
import com.sarang.torang.usecase.GetCopyLinkUseCase
import com.sarang.torang.usecase.GetFollowerUseCase
import com.sarang.torang.usecase.SendShareUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShareViewModel @Inject constructor(
    getFollowerUseCase: GetFollowerUseCase,
    val sendShareUseCase : SendShareUseCase,
    val copyLinkUseCase : GetCopyLinkUseCase
) : ViewModel()
{
    var uiState by mutableStateOf(ShareDialogUiState()); private set
    private var text : String = ""
    private var reviewId : Int = -1;
    var isSending : Boolean by mutableStateOf(false)
    var errorMessage by mutableStateOf("")

    fun consumeErrorMessage(){
        errorMessage = ""
    }

    fun setReviewId(reviewId : Int){
        this.reviewId = reviewId
    }

    fun select(userId: Int) {
        uiState = uiState.copy(
            list = uiState.list.map {
                if(it.userId == userId) {
                    it.copy(isSelected = !it.isSelected)
                }
                else {
                    it
                }
            },
            filteredList = uiState.filteredList.map {
                if(it.userId == userId) {
                    it.copy(isSelected = !it.isSelected)
                }
                else {
                    it
                }
            }
        )
    }

    fun onSearch(searchText: String) {
        text = searchText
        uiState = uiState.copy(filteredList = uiState.list.filter { it.userName.lowercase().contains(searchText.lowercase()) })
    }

    suspend fun sendShare() {
        isSending = true
        if (reviewId < 0) {
            try {
                sendShareUseCase.invoke(
                    reviewId,
                    uiState.list.filter { isSending }.map { it.userId })
            }catch (e : Exception){
                errorMessage = "${e.message}"
            }
        } else {
            errorMessage = "피드가 선택되지 않았습니다."
        }
        isSending = false
    }

    suspend fun getLink(reviewId: Int): String? {
        isSending = true
        try {
            return copyLinkUseCase.invoke(reviewId)
        } catch (e: Exception) {
            errorMessage = "링크를 저장하는데 실패하였습니다."
            return null
        }finally {
            isSending = false
        }
    }

    fun onClose() {
        text = ""
        reviewId = -1
        uiState = uiState.copy(
            list = uiState.list.map { it.copy(isSelected = false) },
            filteredList = uiState.list.map { it.copy(isSelected = false) }
        )
    }

    init
    {
        viewModelScope.launch {
            try
            {
                val list = getFollowerUseCase.invoke()
                uiState = uiState.copy(
                    list = list,
                    filteredList = list
                )
                text = ""
            } catch (e: Exception)
            {

            }
        }
    }
}
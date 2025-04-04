package com.sarang.torang.compose.bottomsheet.feed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sarang.torang.compose.bottomsheet.feed.components.MyMenu
import com.sarang.torang.compose.bottomsheet.feed.components.ReportMenu
import com.sarang.torang.uistate.FeedMenuUiState
import com.sarang.torang.viewmodels.FeedMenuViewModel

/**
 * 피드 메뉴 bottom sheet
 */
@Composable
fun FeedMenuModalBottomSheet(
    viewModel: FeedMenuViewModel = hiltViewModel(),
    isExpand: Boolean,
    reviewId: Int,
    onReport: () -> Unit,
    onDelete: () -> Unit,
    onEdit: () -> Unit,
    onClose: () -> Unit
) {

    LaunchedEffect(key1 = reviewId, block = {
        viewModel.load(reviewId)
    })

    val uiState by viewModel.uiState.collectAsState()

    _FeedMenuModalBottomSheet(uiState, isExpand, onReport, onDelete, onEdit, onClose)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun _FeedMenuModalBottomSheet(
    uiState: FeedMenuUiState,
    isExpand: Boolean,
    onReport: () -> Unit,
    onDelete: () -> Unit,
    onEdit: () -> Unit,
    onClose: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var showBottomSheet by remember { mutableStateOf(isExpand) }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
                onClose.invoke()
            }, sheetState = sheetState
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (uiState.isMine) {
                    MyMenu(
                        onEdit = onEdit,
                        onDelete = onDelete,
                    )
                } else {
                    ReportMenu(onReport = onReport)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewFeedMenuModalBottomSheet() {
    _FeedMenuModalBottomSheet(/*preview*/
        uiState = FeedMenuUiState(),
        isExpand = true,
        onReport = {},
        onDelete = {},
        onEdit = {},
        onClose = {}
    )
}
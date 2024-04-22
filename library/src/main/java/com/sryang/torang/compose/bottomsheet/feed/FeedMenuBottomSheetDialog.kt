package com.sryang.torang.compose.bottomsheet.feed

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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sryang.torang.viewmodels.FeedMenuViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedMenuBottomSheetDialog(
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
fun PreviewFeedMenuBottomSheetDialog() {
    FeedMenuBottomSheetDialog(/*preview*/
        isExpand = true,
        onReport = {},
        onDelete = {},
        onEdit = {},
        onClose = {},
        reviewId = 117
    )
}
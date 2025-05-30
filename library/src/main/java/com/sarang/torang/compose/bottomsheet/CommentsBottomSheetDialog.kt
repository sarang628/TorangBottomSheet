package com.sarang.torang.compose.bottomsheet

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartiallyModalBottomSheet(
    isExpand: Boolean,
    onClose: () -> Unit,
    commentList: @Composable () -> Unit
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
            },
            sheetState = sheetState
        ) {
            commentList.invoke()
        }
    }
}

@Preview
@Composable
fun PreviewPartiallyModalBottomSheet() {
    PartiallyModalBottomSheet(
        isExpand = true,
        onClose = {}
    ) {

    }
}
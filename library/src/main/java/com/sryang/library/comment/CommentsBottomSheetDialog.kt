package com.sryang.library.comment

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
fun CommentBottomSheetDialog(
    profileImageServerUrl: String,
    profileImageUrl: String,
    list: List<CommentItemUiState>,
    isExpand: Boolean,
    onSelect: (String) -> Unit,
    onClose: () -> Unit,
    onSend: (String) -> Unit,
    name: String
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
            CommentList(
                profileImageUrl = profileImageUrl,
                profileImageServerUrl = profileImageServerUrl,
                list = list,
                onSend = onSend,
                name = name
            )
        }
    }
}
@Preview
@Composable
fun PreviewCommentBottomSheetDialog() {
    CommentBottomSheetDialog(
        isExpand = true,
        profileImageUrl = "1/2023-09-14/10_44_39_302.jpeg",
        onSelect = {},
        onClose = {},
        profileImageServerUrl = "http://sarang628.iptime.org:89/profile_images/",
        onSend = {},
        name = "name",
        list = ArrayList<CommentItemUiState>().apply {
//            add(testCommentItemUiState())
//            add(testCommentItemUiState())
//            add(testCommentItemUiState())
//            add(testCommentItemUiState())
//            add(testCommentItemUiState())
//            add(testCommentItemUiState())
//            add(testCommentItemUiState())
//            add(testCommentItemUiState())
//            add(testCommentItemUiState())
//            add(testCommentItemUiState())
//            add(testCommentItemUiState())
//            add(testCommentItemUiState())
//            add(testCommentItemUiState())
        }
    )
}
package com.sarang.torang.compose.bottomsheet.share

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
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
import com.sarang.torang.compose.bottomsheet.share.components.ItemShareList
import com.sarang.torang.compose.bottomsheet.share.components.ShareSearchBar
import com.sarang.torang.uistate.FeedMenuUiState
import com.sarang.torang.uistate.ShareDialogUiState
import com.sarang.torang.viewmodels.ShareViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShareBottomSheetDialog(
    shareViewModel: ShareViewModel = hiltViewModel(),
    profileServerUrl: String,
    isExpand: Boolean,
    onSelect: (String) -> Unit,
    onClose: () -> Unit,
) {
    val uiState by shareViewModel.uiState.collectAsState()
    _ShareBottomSheetDialog(uiState, profileServerUrl, isExpand, onSelect, onClose)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun _ShareBottomSheetDialog(
    uiState: ShareDialogUiState,
    profileServerUrl: String,
    isExpand: Boolean,
    onSelect: (String) -> Unit,
    onClose: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showBottomSheet by remember { mutableStateOf(isExpand) }

    if (showBottomSheet) {
        ModalBottomSheet(onDismissRequest = {
            showBottomSheet = false
            onClose.invoke()
        }, sheetState = sheetState) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ShareSearchBar()
                Spacer(modifier = Modifier.height(8.dp))
                ItemShareList(list = uiState.list, profileServerUrl)
            }
        }
    }
}

@Preview
@Composable
fun PreviewShareBottomSheetDialog() {
    _ShareBottomSheetDialog(
        uiState = ShareDialogUiState(list = listOf()),
        isExpand = true,
        onSelect = {},
        onClose = {},
        profileServerUrl = "http://sarang628.iptime.org:89/profile_images/"
    )
}
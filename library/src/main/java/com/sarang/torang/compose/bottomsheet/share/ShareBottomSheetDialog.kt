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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sarang.torang.compose.bottomsheet.share.components.ItemShareList
import com.sarang.torang.compose.bottomsheet.share.components.ShareBottomMenus
import com.sarang.torang.compose.bottomsheet.share.components.ShareSearchBar
import com.sarang.torang.data.bottomsheet.Sample
import com.sarang.torang.data.bottomsheet.User
import com.sarang.torang.uistate.ShareDialogUiState
import com.sarang.torang.viewmodels.ShareViewModel


@Composable
fun ShareModalBottomSheet(
    shareViewModel: ShareViewModel = hiltViewModel(),
    isExpand: Boolean,
    onSelect: (String) -> Unit,
    onClose: () -> Unit,
) {
    val uiState by shareViewModel.uiState.collectAsState()
    _ShareModalBottomSheet(uiState, isExpand, onSelect, onClose)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun _ShareModalBottomSheet(
    uiState: ShareDialogUiState,
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
                ItemShareList(list = uiState.list)
                Spacer(modifier = Modifier.height(8.dp))
                ShareBottomMenus()
            }
        }
    }
}

@Preview
@Composable
fun PreviewShareModalBottomSheet() {
    _ShareModalBottomSheet(
        uiState = ShareDialogUiState(
            list = listOf(User.Sample, User.Sample, User.Sample, User.Sample, User.Sample)),
        isExpand = true,
        onSelect = {},
        onClose = {}
    )
}
package com.sarang.torang.compose.bottomsheet.share

import android.content.Context
import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sarang.torang.compose.bottomsheet.share.components.ItemShareList
import com.sarang.torang.compose.bottomsheet.share.components.SendShare
import com.sarang.torang.compose.bottomsheet.share.components.ShareBottomMenus
import com.sarang.torang.compose.bottomsheet.share.components.ShareSearchBar
import com.sarang.torang.data.bottomsheet.Sample
import com.sarang.torang.data.bottomsheet.User
import com.sarang.torang.uistate.ShareDialogUiState
import com.sarang.torang.viewmodels.ShareViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShareModalBottomSheet(
    shareViewModel  : ShareViewModel    = hiltViewModel(),
    reviewId        : Int,
    isExpand        : Boolean           = false,
    onClose         : () -> Unit        = {},
    onAddStory      : () -> Unit        = {},
) {
    val uiState             : ShareDialogUiState    = shareViewModel.uiState
    val sheetState          : SheetState            = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutine           : CoroutineScope        = rememberCoroutineScope()
    val clipboardManager    : ClipboardManager      = LocalClipboardManager.current
    val context             : Context               = LocalContext.current
    _ShareModalBottomSheet(
        uiState     = uiState,
        isExpand    = isExpand,
        onSelect    = { shareViewModel.select(it) },
        onClose     = {
            onClose()
            shareViewModel.onClose()
                      },
        onLinkCopy  = {
            coroutine.launch {
                shareViewModel.getLink(reviewId)?.let {
                    clipboardManager.setText(AnnotatedString(it))
                }
            }
                      },
        onAddStory  = onAddStory,
        onShare     = {
            coroutine.launch {
                shareViewModel.getLink(reviewId)?.let {
                    val sendIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, it)
                    }

                    val shareIntent = Intent.createChooser(sendIntent, null)
                    context.startActivity(shareIntent)
                }
            }
        },
        onValueChange = {
            shareViewModel.onSearch(it)
        },
        onSend = {
            coroutine.launch {
                shareViewModel.sendShare()
                sheetState.hide()
            }
        },
        isSending = shareViewModel.isSending,
        sheetState = sheetState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun _ShareModalBottomSheet(
    uiState         : ShareDialogUiState,
    isExpand        : Boolean           = false,
    isSending       : Boolean           = false,
    onSelect        : (Int) -> Unit     = {},
    onClose         : () -> Unit        = {},
    onLinkCopy      : () -> Unit        = {},
    onAddStory      : () -> Unit        = {},
    onShare         : () -> Unit        = {},
    onSend          : (String) -> Unit  = {},
    onValueChange   : (String) -> Unit  = {},
    sheetState      : SheetState        = rememberModalBottomSheetState(skipPartiallyExpanded = true)
) {
    if (isExpand) {
        ModalBottomSheet(onDismissRequest = {
            onClose.invoke()
        }, sheetState = sheetState) {

            val content : @Composable ()->Unit = {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ShareSearchBar(onValueChange = onValueChange)
                    Spacer(modifier = Modifier.height(8.dp))
                    ItemShareList(list = uiState.filteredList, onClick = onSelect)
                    Spacer(modifier = Modifier.height(8.dp))

                    AnimatedVisibility(
                        visible = uiState.list.none { it.isSelected }
                    ) {
                        ShareBottomMenus(
                            onLinkCopy = onLinkCopy,
                            onAddStory = onAddStory,
                            onShare = onShare
                        )
                    }

                    AnimatedVisibility(
                        visible = uiState.list.any { it.isSelected }
                    ) {
                        SendShare(onSend = onSend)
                    }
                }
            }
            Box {
                content()
                if(isSending)
                    Box(Modifier
                        .matchParentSize()
                        .background(Color(0x55000000))
                    ){
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewShareModalBottomSheet() {
    _ShareModalBottomSheet(
        isExpand = true,
        onClose = {},
        uiState = ShareDialogUiState(
            list = listOf(User.Sample, User.Sample, User.Sample, User.Sample, User.Sample, User.Sample, User.Sample, User.Sample, User.Sample),
            filteredList = listOf(User.Sample, User.Sample, User.Sample, User.Sample, User.Sample, User.Sample, User.Sample, User.Sample, User.Sample),
        )
    )
}
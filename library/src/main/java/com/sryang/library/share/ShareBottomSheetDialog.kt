package com.sryang.library.share

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShareBottomSheetDialog(
    isExpand: Boolean,
    onSelect: (String) -> Unit,
    onClose: () -> Unit,
    list: List<ShareProfile>
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(isExpand) }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
                onClose.invoke()
            },
            sheetState = sheetState
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ShareSearchBar()
                Spacer(modifier = Modifier.height(8.dp))
                ItemShareList(list = list)
            }
        }
    }
}

@Preview
@Composable
fun PreviewShareBottomSheetDialog() {
    ShareBottomSheetDialog(isExpand = true, onSelect = {}, onClose = {}, list = arrayOf(
        ShareProfile(
            profileUrl = "http://sarang628.iptime.org:89/1.png",
            id = "momo",
            name = "모모 (MOMO)",
            date = ""
        ),
        ShareProfile(
            profileUrl = "http://sarang628.iptime.org:89/2.png",
            id = "J",
            name = "jennierbyjane",
            date = ""
        ),
        ShareProfile(
            profileUrl = "http://sarang628.iptime.org:89/3.png",
            id = "lalalalisa_m",
            name = "LISA",
            date = ""
        ),
        ShareProfile(
            profileUrl = "http://sarang628.iptime.org:89/4.png",
            id = "kimkardashian",
            name = "Kim Kardashian",
            date = ""
        ),
        ShareProfile(
            profileUrl = "http://sarang628.iptime.org:89/5.png",
            id = "",
            name = "kevinhart4real",
            date = ""
        ),
        ShareProfile(
            profileUrl = "http://sarang628.iptime.org:89/6.png",
            id = "nickminaj",
            name = "Barbie",
            date = ""
        ),
        ShareProfile(
            profileUrl = "http://sarang628.iptime.org:89/7.png",
            id = "",
            name = "champagnepapi",
            date = ""
        ),
        ShareProfile(
            profileUrl = "http://sarang628.iptime.org:89/8.png",
            id = "youtube",
            name = "google",
            date = ""
        )
    ).toList()
    )
}
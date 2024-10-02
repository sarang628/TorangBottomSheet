package com.sarang.torang.compose.bottomsheet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sarang.torang.compose.bottomsheet.bottomsheetscaffold.TorangBottomSheetScaffold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageSelectBottomSheetScaffold(
    show: Boolean,
    onHidden: () -> Unit,
    imageSelectCompose: @Composable () -> Unit,
    content: @Composable (PaddingValues) -> Unit,
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp

    TorangBottomSheetScaffold(
        scaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = rememberStandardBottomSheetState(
                initialValue = SheetValue.Hidden,
                skipHiddenState = false
            )
        ),
        show = show,
        sheetPeekHeight = screenHeight.dp * 0.7f,
        sheetContent = {
            Box(modifier = Modifier.fillMaxSize())
            {
                imageSelectCompose.invoke()
            }
        },
        content = content,
        onHidden = onHidden,
        expandOption = SheetValue.PartiallyExpanded
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewImageSelectBottomSheetDialog() {
    var show by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()) {
        ImageSelectBottomSheetScaffold(
            show = show,
            onHidden = { show = false },
            imageSelectCompose = {}
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Button(onClick = { show = true }) {
                    Text(text = "show")
                }
            }
        }
    }
}
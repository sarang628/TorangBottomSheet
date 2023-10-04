package com.sryang.library

import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CloseDetectBottomSheetScaffold(
    isExpand: Boolean,
    scaffoldState: BottomSheetScaffoldState,
    onClose: () -> Unit,
    bottomSheetScaffold: @Composable () -> Unit
) {
    var lastState = SheetValue.Hidden

    LaunchedEffect(key1 = "", block = {
        if (isExpand) {
            scaffoldState.bottomSheetState.expand()
        }
    })

    LaunchedEffect(key1 = scaffoldState, block = {
        snapshotFlow { scaffoldState.bottomSheetState.currentValue }.collect {
            if (lastState == SheetValue.Expanded && it == SheetValue.PartiallyExpanded) {
                onClose.invoke()
            }
            lastState = it
        }
    })

    bottomSheetScaffold.invoke()

}
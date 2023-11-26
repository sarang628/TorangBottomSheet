package com.sryang.torang.compose.bottomsheet

import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CloseDetectBottomSheetScaffold(
    isExpand: Boolean,
    scaffoldState: BottomSheetScaffoldState,
    onClose: () -> Unit,
    bottomSheetScaffold: @Composable (Modifier) -> Unit
) {
    var lastState = SheetValue.Hidden
    val coroutineScope = rememberCoroutineScope()
    var state by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = isExpand, block = {
        if (isExpand) {
            scaffoldState.bottomSheetState.expand()
            state = true
        }
    })

    LaunchedEffect(key1 = scaffoldState, block = {
        snapshotFlow { scaffoldState.bottomSheetState.currentValue }.collect {
            if (lastState == SheetValue.Expanded && it == SheetValue.PartiallyExpanded) {
                onClose.invoke()
                state = false
            }
            lastState = it
        }
    })

    bottomSheetScaffold.invoke(
        Modifier
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    Log.d("CloseDetectBottomSheetScaffold", "onTap")
                    coroutineScope.launch {
                        /*if (scaffoldState.bottomSheetState.isVisible) {
                            scaffoldState.bottomSheetState.hide()
                        }*/
                    }
                })
            },
    )

}
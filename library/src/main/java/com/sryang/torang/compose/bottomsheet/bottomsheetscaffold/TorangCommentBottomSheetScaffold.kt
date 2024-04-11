package com.sryang.torang.compose.bottomsheet.bottomsheetscaffold

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/**
 * <a href="https://m3.material.io/components/bottom-sheets/overview" class="external" target="_blank">Material Design standard bottom sheet scaffold</a>.
 *
 * Standard bottom sheets 는 화면의 main UI 영역과 '공존' and '동시에 보여줌' and '서로 영역이 상호작용'.
 * 메인 UI 영역이 스크롤 또는 움직일 때 어떤 '특징' 또는 '보조 내용' 보여줌.
 *
 * ![Bottom sheet image](https://developer.android.com/images/reference/androidx/compose/material3/bottom_sheet.png)
 *
 * 이 컴포넌트는 bottom sheets의 목적에 맞는 화면을 보여주는 material components API를 제공
 *
 * 예제
 * @sample androidx.compose.material3.samples.SimpleBottomSheetScaffoldSample
 *
 * @param sheetContent sheet안에 들어갈 UI
 * @param modifier
 * @param scaffoldState bottom sheet scaffold 의 상태
 * @param sheetPeekHeight 접혔을 때 높이
 * @param sheetMaxWidth 가로 최대 길이. 설정 않으면 최대
 * @param sheetShape bottom sheet 모양
 * @param sheetContainerColor bottom sheet의 배경 색상
 * @param sheetContentColor bottom sheet의 자식 색상.
 * [sheetContainerColor] 과 일치하는 색상이 기본값, or theme에서 설정한 색상이 아니라면, bottom sheet에 설정된 색상과 동일하게 적용.
 * @param sheetTonalElevation bottom sheet의 tonal elevation
 * @param sheetShadowElevation bottom sheet의 shadow elevation
 * @param sheetDragHandle scaffold's bottom sheet를 당기는 visual marker
 * @param sheetSwipeEnabled 사용자 swipe 가능 여부
 * @param topBar 화면의 topBar
 * @param snackbarHost SnackBar 설정 할 수 있는 영역
 * @param containerColor scaffold의 배경색.
 * @param contentColor scaffold 안에 색생을 설정하고 싶을 경우.기본적으로 [containerColor]로 설정, 그렇지 않으면 [LocalContentColor]
 * @param content 안에 내용. 람다에서 받는 [PaddingValues] 루트의 [Modifier.padding] 과 [Modifier.consumeWindowInsets] 에 적용.
 * 만약 [Modifier.verticalScroll] 사용한다면, 스크롤에 적용하기.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TorangCommentBottomSheetScaffold(
    input: @Composable () -> Unit,
    sheetPeekHeight: Dp = BottomSheetDefaults.SheetPeekHeight,
    sheetContainerColor: Color = BottomSheetDefaults.ContainerColor,
    sheetContentColor: Color = contentColorFor(sheetContainerColor),
    containerColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(containerColor),
    topBar: @Composable (() -> Unit)? = null,
    sheetTonalElevation: Dp = BottomSheetDefaults.Elevation,
    sheetShadowElevation: Dp = BottomSheetDefaults.Elevation,
    sheetContent: @Composable ColumnScope.() -> Unit,
    inputHiddenOffset : Dp,
    snackbarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
    scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(skipHiddenState = false)
    ),
    init : Boolean = false,
    content: @Composable (PaddingValues) -> Unit
) {
    val density = LocalDensity.current.density
    var offset by remember { mutableStateOf(0.dp) }

    LaunchedEffect(key1 = scaffoldState) {
        snapshotFlow {
            scaffoldState.bottomSheetState.requireOffset()
        }.collect {
            offset = (it / density).dp
        }
    }

    Box(modifier = Modifier) {
        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetContent = sheetContent,
            sheetPeekHeight = if (init) 0.dp else sheetPeekHeight,
            sheetShape = BottomSheetDefaults.ExpandedShape,
            sheetContainerColor = sheetContainerColor,
            sheetContentColor = sheetContentColor,
            sheetTonalElevation = sheetTonalElevation,
            sheetShadowElevation = sheetShadowElevation,
            sheetDragHandle = { BottomSheetDefaults.DragHandle() },
            sheetSwipeEnabled = true,
            topBar = topBar,
            snackbarHost = snackbarHost,
            containerColor = containerColor,
            contentColor = contentColor,
            content = content,
        )
        Box(
            modifier = Modifier
                .align(
                    Alignment.BottomCenter
                )
                .absoluteOffset(y = if ((LocalConfiguration.current.screenHeightDp.dp - offset) < inputHiddenOffset) inputHiddenOffset - (LocalConfiguration.current.screenHeightDp.dp - offset) else 0.dp)
        ) {
            input.invoke()
        }
    }
}
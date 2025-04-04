package com.sarang.torang.compose.bottomsheet.bottomsheetscaffold

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


/**
 * # Bottom Sheet Scaffold의 custom compose
 *
 * <b>Bottom Sheet Scaffold의 기능을 테스트하며 응용해 만든 custom compose</b>
 *
 * <a href="https://m3.material.io/components/bottom-sheets/overview" class="external" target="_blank">Material Design standard bottom sheet scaffold</a>.
 *
 * material design 의 표준 bottom sheet
 *
 * bottom sheet main UI 영역에 같이 보여주고 상호작용함.
 *
 * 예제
 * @sample androidx.compose.material3.samples.SimpleBottomSheetScaffoldSample
 *
 * @param modifier
 * @param show bottom sheet 보임 여부
 * @param sheetPeekHeight 당겨졌을  때 높이
 * @param sheetContent sheet안에 들어갈 UI
 * @param snackbarHost SnackBar 설정
 * @param onHidden 숨겨졌을 때 이벤트 콜백
 * @param onOffset 시트의 높이 여백
 * @param expandOption 시트 확장 옵션
 * @param content 시트 바깥의 내용
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TorangBottomSheetScaffold(
    modifier: Modifier = Modifier,
    show: Boolean = false,
    sheetPeekHeight: Dp = BottomSheetDefaults.SheetPeekHeight,
    sheetContent: @Composable ColumnScope.() -> Unit,
    snackbarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
    onHidden: (() -> Unit) = {},
    onOffset: (Dp) -> Unit = {},
    expandOption: SheetValue = SheetValue.Expanded,
    content: @Composable (PaddingValues) -> Unit,
) {
    val scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false
        )
    )
    val TAG = "__TorangCommentBottomSheetScaffold"
    val coroutine = rememberCoroutineScope()
    val density = LocalDensity.current.density

    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics
    val screenHeightPx = displayMetrics.heightPixels // 화면 높이 (픽셀 단위)
    val height = screenHeightPx


    var bottomSheetOffset: Float by remember { mutableFloatStateOf(0f) }
    val alpha = ((height - 200) - bottomSheetOffset) / (height * 2)

    val backPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    var backHandlerEnabled by remember { mutableStateOf(true) }


    BackHandler(enabled = backHandlerEnabled) {
        coroutine.launch {
            if (scaffoldState.bottomSheetState.isVisible) {
                scaffoldState.bottomSheetState.hide()
            } else {
                backHandlerEnabled = false // 먼저 상태를 비활성화
            }
        }
    }

    // BackHandler 비활성화 후 시스템 뒤로 가기 호출
    LaunchedEffect(backHandlerEnabled) {
        if (!backHandlerEnabled) {
            backPressedDispatcher?.onBackPressed()
        }
    }

    LaunchedEffect(key1 = show) { // show 변수에 따라 bottom sheet 보임 처리
        if (show && scaffoldState.bottomSheetState.currentValue == SheetValue.Hidden) {
            Log.i(TAG, "call expand")
            if (expandOption == SheetValue.Expanded
            ) {
                scaffoldState.bottomSheetState.expand()
            } else {
                scaffoldState.bottomSheetState.partialExpand()
            }
        } else if (!show && scaffoldState.bottomSheetState.currentValue != SheetValue.Hidden) {
            if (scaffoldState.bottomSheetState.currentValue == SheetValue.Expanded
                || scaffoldState.bottomSheetState.currentValue == SheetValue.PartiallyExpanded
            ) {
                scaffoldState.bottomSheetState.hide()
            }
        } else {
            Log.e(
                TAG,
                "wrong state sheet. currantValue: ${scaffoldState.bottomSheetState.currentValue.name}, show: ${show}"
            )
        }
    }

    LaunchedEffect(key1 = scaffoldState.bottomSheetState.currentValue) { // 숨김 이벤트 감지
        if (scaffoldState.bottomSheetState.currentValue == SheetValue.Expanded)
            snapshotFlow { scaffoldState.bottomSheetState.currentValue }
                .collect {
                    Log.d(TAG, it.name)
                    if (it == SheetValue.Hidden && show) {
                        Log.d(TAG, "onHidden")
                        onHidden.invoke()
                    }
                }
    }

    LaunchedEffect(key1 = scaffoldState.bottomSheetState) { // 바텀 시트의 높이. 딤 처리를 위함
        snapshotFlow { scaffoldState.bottomSheetState.requireOffset() }
            .collect {
                bottomSheetOffset = it
            }
    }

    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        sheetContent = sheetContent,
        sheetPeekHeight = sheetPeekHeight,
        sheetShape = BottomSheetDefaults.ExpandedShape,
        sheetDragHandle = { BottomSheetDefaults.DragHandle() },
        sheetSwipeEnabled = true,
        snackbarHost = snackbarHost,
        content = {
            content.invoke(it)
            if (alpha > 0.1) // 알파값이 0.1 보다 크다면 보여짐
                Box(
                    Modifier
                        .background(Color.Black.copy(alpha = alpha))
                        .fillMaxSize()
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            if (scaffoldState.bottomSheetState.currentValue != SheetValue.Hidden) {
                                coroutine.launch {
                                    scaffoldState.bottomSheetState.hide()
                                }
                            }
                        }
                )
        }
    )

    LaunchedEffect(key1 = show) {
        snapshotFlow {
            scaffoldState.bottomSheetState.requireOffset()
        }.collect {
            onOffset.invoke((it / density).dp)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewTorangBottomSheetScaffold() {
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutine = rememberCoroutineScope()
    val data by remember { mutableStateOf("") }
    var show by remember { mutableStateOf(false) }
    TorangBottomSheetScaffold(/*Preview*/
        modifier = Modifier.fillMaxSize(),
        sheetPeekHeight = 350.dp,
        snackbarHost = { SnackbarHost(hostState = it) },
        show = show,
        onHidden = {
            show = false
        },
        content = {
            Column(
                Modifier
                    .padding(it)
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                Button(onClick = { show = !show }) {
                    Text(text = if (show) "hide" else ("show"))
                }
            }
        },
        expandOption = SheetValue.PartiallyExpanded,
        sheetContent = {
            Column(Modifier.fillMaxHeight()) {
                Text(text = "aaaaa")
                Button(onClick = {
                    coroutine.launch {
                        snackBarHostState.showSnackbar("cccc")
                    }
                }
                ) {
                    Text(text = "$data")
                }
            }
        }
    )
}
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
import androidx.compose.material3.SheetValue
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
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
import com.sarang.torang.util.TorangBottomSheetDebugLog
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
    onMaxBottomSheetHeight: (Dp) -> Unit = {},
    expandOption: SheetValue = SheetValue.Expanded,
    content: @Composable (PaddingValues) -> Unit,
) {
    val scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false
        )
    )

    val TAG = "__TorangBottomSheetScaffold"
    val coroutine = rememberCoroutineScope()
    val density = LocalDensity.current.density

    val context = LocalContext.current
    val offset = 200
    val height = context.resources.displayMetrics.heightPixels - offset // 화면 높이 (픽셀 단위)
    var sheetHeight: Float by remember { mutableFloatStateOf(0f) }
    val alpha = (height - sheetHeight) / (height * 2)
    var maxBottomSheetHeight by remember { mutableStateOf(0.dp) }

    val backPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    var backHandlerEnabled by remember { mutableStateOf(true) }


    BackHandler(enabled = backHandlerEnabled) {
        coroutine.launch {
            if (scaffoldState.bottomSheetState.isVisible) {
                scaffoldState.bottomSheetState.hide()
                TorangBottomSheetDebugLog.d(TAG, "call back. call hide()")
            } else {
                backHandlerEnabled = false // 먼저 상태를 비활성화
            }
        }
    }

    LaunchedEffect(backHandlerEnabled) { // BackHandler 비활성화 후 시스템 뒤로 가기 호출
        if (!backHandlerEnabled) {
            backPressedDispatcher?.onBackPressed()
            TorangBottomSheetDebugLog.d(TAG, "call back. call backPressed()")
        }
    }

    LaunchedEffect(key1 = show) {
        snapshotFlow {
            scaffoldState.bottomSheetState.requireOffset() / density
        }.collect {
            if (maxBottomSheetHeight > 0.dp) {
                onOffset.invoke(maxBottomSheetHeight - it.dp)
            }
        }
    }

    LaunchedEffect(key1 = show) { // show 변수에 따라 bottom sheet 보임 처리
        if (scaffoldState.bottomSheetState.currentValue == SheetValue.Hidden) {
            if (show) {
                TorangBottomSheetDebugLog.d(TAG, "require show. operate")
                if (expandOption == SheetValue.Expanded) {
                    scaffoldState.bottomSheetState.expand()
                } else {
                    scaffoldState.bottomSheetState.partialExpand()
                }
            } else {
                Log.e(TAG, "require show false. but state already Hidden.")
            }
        } else if (!show && scaffoldState.bottomSheetState.currentValue != SheetValue.Hidden) {
            if (scaffoldState.bottomSheetState.currentValue == SheetValue.Expanded
                || scaffoldState.bottomSheetState.currentValue == SheetValue.PartiallyExpanded
            ) {
                scaffoldState.bottomSheetState.hide()
                TorangBottomSheetDebugLog.d(TAG, "show changed: ${show} call hide()")
            }
        } else {
            Log.e(
                TAG,
                """state sheet is wrong. currantValue: ${scaffoldState.bottomSheetState.currentValue.name} show: ${show}""".trimMargin()
            )
        }
    }

    LaunchedEffect(key1 = scaffoldState.bottomSheetState.currentValue) { // 숨김 이벤트 감지
        snapshotFlow { scaffoldState.bottomSheetState.currentValue }
            .collect {
                if (it == SheetValue.Hidden && maxBottomSheetHeight == 0.dp) {
                    maxBottomSheetHeight = scaffoldState.bottomSheetState.requireOffsetDp(density)
                    onMaxBottomSheetHeight.invoke(maxBottomSheetHeight)

                    Log.d(TAG, "detect maxBottomSheetHeight: ${maxBottomSheetHeight}")
                }

                if (it == SheetValue.Hidden && show) {
                    Log.d(TAG, "onHidden")
                    onHidden.invoke()
                } else {
                    TorangBottomSheetDebugLog.d(
                        TAG,
                        """bottom sheeet currentValue changed. currentValue: ${it.name} targetValue: ${scaffoldState.bottomSheetState.targetValue}  show: ${show}""".trimMargin()
                    )
                }
            }
    }

    LaunchedEffect(key1 = scaffoldState.bottomSheetState) { // 바텀 시트의 높이. 딤 처리를 위함
        snapshotFlow { scaffoldState.bottomSheetState.requireOffset() }
            .collect {
                sheetHeight = it
            }
    }

    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        sheetContent = sheetContent,
        sheetPeekHeight = if (sheetPeekHeight <= 0.dp) {
            Log.e(TAG, "sheetPeekHeight is less than or equal to 0. set default value.")
            BottomSheetDefaults.SheetPeekHeight
        } else {
            sheetPeekHeight
        },
        sheetShape = BottomSheetDefaults.ExpandedShape,
        sheetDragHandle = { BottomSheetDefaults.DragHandle() },
        snackbarHost = snackbarHost,
        content = {
            content.invoke(it)
            if (alpha > 0.01) // 알파값이 0.1 보다 크다면 보여짐
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


@OptIn(ExperimentalMaterial3Api::class)
fun androidx.compose.material3.SheetState.requireOffsetDp(density: Float): Dp {
    return (this.requireOffset() / density).dp
}
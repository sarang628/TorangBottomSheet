package com.sarang.torang.compose.bottomsheet.bottomsheetscaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


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
 * @param modifier
 * @param show
 * @param input
 * @param sheetPeekHeight 접혔을 때 높이
 * @param sheetContent sheet안에 들어갈 UI
 * @param inputHiddenOffset
 * @param snackbarHost SnackBar 설정 할 수 있는 영역
 * @param onHidden
 * @param content 안에 내용. 람다에서 받는 [PaddingValues] 루트의 [Modifier.padding] 과 [Modifier.consumeWindowInsets] 에 적용.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputInteractBottomSheetScaffold(
    modifier: Modifier = Modifier,
    show: Boolean = false,
    input: @Composable () -> Unit,
    sheetPeekHeight: Dp = BottomSheetDefaults.SheetPeekHeight,
    sheetContent: @Composable ColumnScope.() -> Unit,
    inputHiddenOffset: Dp,
    snackbarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
    onHidden: (() -> Unit) = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    var offset by remember { mutableStateOf(0.dp) }
    val inputOffset = if ((LocalConfiguration.current.screenHeightDp.dp //화면의 높이에서
                - offset // botton sheet의 높이를 뺀 값이
                ) < inputHiddenOffset // 기준값 보다 작다면
    ) {
        // inputOffset을 내려 input 창을 함께 내린다.
        inputHiddenOffset - (LocalConfiguration.current.screenHeightDp.dp - offset)
    } else 0.dp

    Box(modifier = modifier) {
        TorangBottomSheetScaffold(
            sheetContent = sheetContent,
            sheetPeekHeight = sheetPeekHeight,
            snackbarHost = snackbarHost,
            content = content,
            show = show,
            onHidden = onHidden,
            onOffset = { offset = it }
        )
        if (show)
            Box(
                modifier = Modifier
                    .align(
                        Alignment.BottomCenter
                    )
                    .absoluteOffset(y = inputOffset)
            ) {
                input.invoke()
            }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInputInteractBottomSheetScaffold() {
    InputInteractBottomSheetScaffold(
        modifier = Modifier
            .fillMaxSize(),
        input = {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color.Blue),
                value = "aaa",
                onValueChange = {})
        },
        sheetContent = {
            Box(Modifier.fillMaxSize()) {
                Text("!!")
            }

        },
        inputHiddenOffset = 100.dp,
        show = true
    ) {
        Text("!@#!@#")
    }
}
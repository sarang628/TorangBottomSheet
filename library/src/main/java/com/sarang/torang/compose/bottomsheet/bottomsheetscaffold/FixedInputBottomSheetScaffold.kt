package com.sarang.torang.compose.bottomsheet.bottomsheetscaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


/**
 * @param modifier
 * @param show bottom sheet 표시여부. 주의! 처음 상태를 true로 설정 하지 않기. 오류 발생(수정 해야함)
 * @param input
 * @param sheetPeekHeight 접혔을 때 높이
 * @param sheetContent sheet안에 들어갈 UI
 * @param inputHeight
 * @param snackbarHost SnackBar 설정 할 수 있는 영역
 * @param onHidden
 * @param content 안에 내용. 람다에서 받는 [PaddingValues] 루트의 [Modifier.padding] 과 [Modifier.consumeWindowInsets] 에 적용.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FixedInputBottomSheetScaffold(
    modifier: Modifier = Modifier,
    show: Boolean = false,
    input: @Composable () -> Unit,
    sheetPeekHeightPercent: Int = 50,
    sheetContent: @Composable ColumnScope.() -> Unit,
    inputHeight: Dp,
    snackbarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
    onHidden: (() -> Unit) = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    val dragHandlerHeight = 50.dp // BottomSheet drag handle 높이 (임의로 잡음 50dp)
    var maxBottomSheetHeight by remember { mutableStateOf(0.dp) } // bottom sheet의 최대 높이
    var currentBottomSheetHeight by remember { mutableStateOf(0.dp) } // bottom sheet의 현재 높이
    val criterionHeight =
        inputHeight + dragHandlerHeight // input 영역이 함께 내려가는 기준값 :  input높이 + draghadler 높이

    val inputOffset =
        (criterionHeight - currentBottomSheetHeight).coerceAtLeast(0.dp) //  현재 bottom sheet 높이가 기준값 보다 작다면, inputOffset 값으로 input 창을 함께 내린다.

    Box(modifier = modifier.imePadding()) { // edge to edge 에서 imePadding을 줘야 하단 영역이 적용됨
        TorangBottomSheetScaffold(
            sheetContent = sheetContent,
            sheetPeekHeight = Dp((maxBottomSheetHeight.value * sheetPeekHeightPercent / 100)),
            snackbarHost = snackbarHost,
            content = content,
            show = show,
            onHidden = onHidden,
            onOffset = { currentBottomSheetHeight = it },
            onMaxBottomSheetHeight = { maxBottomSheetHeight = it }
        )
        if (show) // bottom sheet가 보이면 input 영역 보이기
            Box(
                Modifier
                    .align(Alignment.BottomCenter)
                    .absoluteOffset(y = inputOffset)
            ) {
                input()
            }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFixedInputBottomSheetScaffold() {
    var show by remember { mutableStateOf(false) }

    FixedInputBottomSheetScaffold(
        modifier = Modifier
            .fillMaxSize(),
        input = {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                value = "input",
                onValueChange = {})
        },
        sheetPeekHeightPercent = 55,
        sheetContent = {
            Box(Modifier.fillMaxSize()) {
                Text("sheet contents area")
            }
        },
        onHidden = { show = false },
        inputHeight = 55.dp,
        show = show
    ) {
        Column {
            Text("PreviewFixedInputBottomSheetScaffold")
            Button({ show = true }) {
                Text(if (!show) "Show" else "Hide")
            }
        }
    }
}
package com.sryang.torang.compose.bottomsheet.bottomsheetscaffold

import android.view.WindowManager
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue.Hidden
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * <a href="https://m3.material.io/components/bottom-sheets/overview" class="external" target="_blank">Material Design modal bottom sheet</a>.
 *
 * Modal bottom sheet 는 inline menus or simple dialogs 대용 으로 사용한다.
 * 특히 긴 리스트 action item 또는 when 긴 설명을 필요로 하는 item
 * dialog와 같이 앱의 앞에 나타나 다른것들을 모두 비활성화 시킴.(확인 취소 등 어떤 action을 할 때까지)
 *
 * ![Bottom sheet image](https://developer.android.com/images/reference/androidx/compose/material3/bottom_sheet.png)
 *
 * 간단한 예제
 * @sample androidx.compose.material3.samples.ModalBottomSheetSample
 *
 * @param onDismissRequest 사용자가 밖을 선택 또는, [Hidden] 된 후.
 * @param modifier Optional [Modifier]
 * @param sheetState bottom sheet의 state.
 * @param sheetMaxWidth 가로 최대 길이. 설정 않으면 최대
 * @param sheetShape bottom sheet 모양
 * @param sheetContainerColor bottom sheet의 배경 색상
 * @param contentColor scaffold 안에 색생을 설정하고 싶을 경우.기본적으로 [containerColor]로 설정, 그렇지 않으면 [LocalContentColor]
 * @param sheetTonalElevation bottom sheet의 tonal elevation
 * @param scrimColor Color of the scrim that obscures content when the bottom sheet is open.
 * @param sheetDragHandle scaffold's bottom sheet를 당기는 visual marker
 * @param windowInsets window insets to be passed to the bottom sheet window via [PaddingValues]
 * params.
 * @param securePolicy Policy for setting [WindowManager.LayoutParams.FLAG_SECURE] on the bottom
 * sheet's window.
 * @param content The content to be displayed inside the bottom sheet.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TorangModalBottomSheet() {
    Box(modifier = Modifier.fillMaxSize()) {
        ModalBottomSheet(onDismissRequest = { /*TODO*/ }) {
            Text(text = "!!!!!!", modifier = Modifier.fillMaxHeight())
        }
        Button(onClick = { /*TODO*/ }, Modifier.align(Alignment.BottomCenter)) {
            Text(text = "!!!!!!!")
        }
    }
}
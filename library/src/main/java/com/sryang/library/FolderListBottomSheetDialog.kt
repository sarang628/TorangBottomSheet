package com.sryang.library

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FolderListBottomSheetDialog(
    isExpand: Boolean,
    onSelect: (String) -> Unit,
    list: List<String>,
    color: Color = Color(0xFFFFFBE6)
) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()

    LaunchedEffect(key1 = "", block = {
        if (isExpand) {
            Log.d("FolderListBottomSheetDialog", "!!!!")
//            scaffoldState.bottomSheetState.partialExpand()
            scaffoldState.bottomSheetState.expand()
        }
    })


    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        containerColor = color,
        sheetContent = {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyColumn(content = {
                    items(list.size) {
                        Text(
                            text = list[it],
                            modifier = Modifier
                                .height(50.dp)
                                .fillMaxWidth()
                                .clickable {
                                    onSelect.invoke(list[it])
                                }

                        )
                        Text(
                            text = "",
                            Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(color = Color.LightGray)
                        )
                    }
                })
            }
        }) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {

        }
    }

}

@Preview
@Composable
fun test() {
    FolderListBottomSheetDialog(isExpand = true, onSelect = {

    }, list = ArrayList<String>().apply {
        add("a")
        add("b")
        add("c")
        add("d")
    })
}
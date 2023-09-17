package com.sryang.library

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import coil.compose.AsyncImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentBottomSheetDialog(
    isExpand: Boolean,
    onSelect: (String) -> Unit
) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    LaunchedEffect(key1 = "", block = {
        if (isExpand) {
            scaffoldState.bottomSheetState.expand()
        }
    })


    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CommentHelp()
                ItemCommentList()
            }
        }) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {

        }
    }

}

@Preview
@Composable
fun PreviewCommentBottomSheetDialog() {
    CommentBottomSheetDialog(isExpand = true, onSelect = {

    })
}

@Preview
@Composable
fun CommentHelp() {
    Box {
        Text(
            text = "this reel is shared publicly to Facebook. Your interactions can also appear..",
            Modifier.align(
                Alignment.Center
            )
        )
    }
}

@Preview
@Composable
fun ItemCommentList() {
    Box {
        LazyColumn(content = {
            items(100) {
                Column {
                    ItemComment()
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        })
    }
}

@Preview
@Composable
fun ItemComment() {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        AsyncImage(
            model = R.drawable.qr,
            contentDescription = "",
            modifier = Modifier
                .size(50.dp)
        )
        Column(Modifier.weight(1f)) {
            Row {
                Text(text = "nick")
                Text(text = "5w")
            }
            Text(text = "How to insult French and Italian people at the same time")
            Text(text = "reply")
        }
        AsyncImage(
            model = R.drawable.save,
            contentDescription = "",
            modifier = Modifier
                .size(50.dp)
        )
    }
}
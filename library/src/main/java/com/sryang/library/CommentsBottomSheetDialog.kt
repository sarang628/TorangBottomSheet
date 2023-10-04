package com.sryang.library

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
    onSelect: (String) -> Unit,
    onClose: () -> Unit,
    color: Color = Color(0xFFFFFBE6)
) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    CloseDetectBottomSheetScaffold(
        isExpand = isExpand,
        scaffoldState = scaffoldState,
        onClose = onClose,
    ) {
        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetContainerColor = color,
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

}

@Preview
@Composable
fun PreviewCommentBottomSheetDialog() {
    CommentBottomSheetDialog(isExpand = true, onSelect = {}, onClose = {})
}

@Preview
@Composable
fun CommentHelp() {
    Column(Modifier.padding(top = 5.dp, bottom = 15.dp)) {
        Text(
            text = "this reel is shared publicly to Facebook. Your interactions can also appear..",
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "",
            Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(Color.LightGray)
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
            model = "http://sarang628.iptime.org:89/7.png",
            contentDescription = "",
            modifier = Modifier
                .size(40.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Row {
                Text(text = "nick")
                Text(text = "5w")
            }
            Text(text = "How to insult French and Italian people at the same time")
            Text(text = "reply")
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                model = R.drawable.bxv,
                contentDescription = "",
                modifier = Modifier
                    .size(25.dp)
            )
            Text(text = "30")
        }
    }
}
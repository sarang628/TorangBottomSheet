package com.sryang.library

import android.renderscript.ScriptGroup.Input
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentBottomSheetDialog(
    profileImageServerUrl: String,
    list: List<CommentItemUiState>,
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
                    ItemCommentList(profileImageServerUrl = profileImageServerUrl, list = list)
                    Text(text = "", Modifier.height(1.dp).fillMaxWidth().background(Color.LightGray))
                    InputComment()
                }
            }) { innerPadding ->
            Box(Modifier.padding(innerPadding)) {

            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputComment() {
    Row(Modifier.height(50.dp), verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            model = R.drawable.bxv,
            contentDescription = "",
            Modifier
                .size(35.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        OutlinedTextField(
            value = "",
            placeholder = {
                Text(
                    text = "Add a comment for thenaughtyfork",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            },
            onValueChange = {

            },
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            )
        )

    }
}

@Preview
@Composable
fun PreviewCommentBottomSheetDialog() {
    CommentBottomSheetDialog(
        isExpand = true,
        onSelect = {},
        onClose = {},
        profileImageServerUrl = "",
        list = ArrayList<CommentItemUiState>().apply {
            add(testCommentItemUiState())
            add(testCommentItemUiState())
            add(testCommentItemUiState())
            add(testCommentItemUiState())
            add(testCommentItemUiState())
            add(testCommentItemUiState())
            add(testCommentItemUiState())
            add(testCommentItemUiState())
        }
    )
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

@Composable
fun ItemCommentList(profileImageServerUrl: String, list: List<CommentItemUiState>) {
    Box {
        LazyColumn(content = {
            items(list.size) {
                Column {
                    ItemComment(profileImageServerUrl = profileImageServerUrl, uiState = list[it])
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        })
    }
}

@Composable
fun ItemComment(profileImageServerUrl: String, uiState: CommentItemUiState) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        AsyncImage(
            model = profileImageServerUrl + uiState.profileImageUrl,
            contentDescription = "",
            modifier = Modifier
                .size(40.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Row {
                Text(text = uiState.name)
                Text(text = uiState.date)
            }
            Text(text = uiState.comment)
            Text(text = "reply")
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                model = R.drawable.bxv,
                contentDescription = "",
                modifier = Modifier
                    .size(25.dp)
            )
            Text(text = uiState.likeCount.toString())
        }
    }
}

data class CommentItemUiState(
    val userId: Int,
    val profileImageUrl: String,
    val date: String,
    val comment: String,
    val name: String,
    val likeCount: Int
)

fun testCommentItemUiState(): CommentItemUiState {
    return CommentItemUiState(
        userId = 0,
        profileImageUrl = "1",
        date = "2",
        comment = "3",
        name = "4",
        likeCount = 5
    )
}
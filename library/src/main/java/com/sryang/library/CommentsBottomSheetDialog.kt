package com.sryang.library

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentBottomSheetDialog(
    profileImageServerUrl: String,
    profileImageUrl: String,
    list: List<CommentItemUiState>,
    isExpand: Boolean,
    onSelect: (String) -> Unit,
    onClose: () -> Unit,
    color: Color = Color(0xFFFFFBE6),
    onSend: (String) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(isExpand) }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
                onClose.invoke()
            },
            sheetState = sheetState,
            containerColor = color
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Comments", fontWeight = FontWeight.Bold)
                CommentHelp()
                ItemCommentList(profileImageServerUrl = profileImageServerUrl, list = list)
                Text(
                    text = "",
                    Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(Color.LightGray)
                )
                InputComment(
                    profileImageServerUrl = profileImageServerUrl,
                    profileImageUrl = profileImageUrl,
                    onSend = onSend
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputComment(
    profileImageServerUrl: String,
    profileImageUrl: String,
    onSend: (String) -> Unit
) {

    var input by remember { mutableStateOf("") }

    Row(
        Modifier
            .height(50.dp)
            .padding(top = 7.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = profileImageServerUrl + profileImageUrl,
            contentDescription = "",
            Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(8.dp))
        BasicTextField(
            value = input,
            onValueChange = { input = it },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            decorationBox = { innerTextField ->
                Box() {
                    if (input.isEmpty()) {
                        Text(
                            text = "Add a comment for thenaughtyfork",
                            color = Color.Gray
                        )
                    }
                    innerTextField()
                }
            }
        )
        Button(onClick = { onSend.invoke(input) }) {
            Text(text = "send")
        }
    }
}

@Preview
@Composable
fun PreviewCommentBottomSheetDialog() {
    CommentBottomSheetDialog(
        isExpand = true,
        profileImageUrl = "1/2023-09-14/10_44_39_302.jpeg",
        onSelect = {},
        onClose = {},
        profileImageServerUrl = "http://sarang628.iptime.org:89/profile_images/",
        onSend = {},
        list = ArrayList<CommentItemUiState>().apply {
//            add(testCommentItemUiState())
//            add(testCommentItemUiState())
//            add(testCommentItemUiState())
//            add(testCommentItemUiState())
//            add(testCommentItemUiState())
//            add(testCommentItemUiState())
//            add(testCommentItemUiState())
//            add(testCommentItemUiState())
//            add(testCommentItemUiState())
//            add(testCommentItemUiState())
//            add(testCommentItemUiState())
//            add(testCommentItemUiState())
//            add(testCommentItemUiState())
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
    Box(Modifier.heightIn(min = 350.dp)) {
        LazyColumn(content = {
            items(list.size) {
                Column {
                    ItemComment(profileImageServerUrl = profileImageServerUrl, uiState = list[it])
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        })

        if (list.isEmpty()) {
            Column(Modifier.align(Alignment.Center)) {
                Text(text = "No comments yet", fontWeight = FontWeight.Bold, fontSize = 23.sp)

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Start the conversation.")
            }
        }
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
                .clip(CircleShape),
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
        profileImageUrl = "1/2023-09-14/10_44_39_302.jpeg",
        date = "2",
        comment = "3",
        name = "4",
        likeCount = 5
    )
}
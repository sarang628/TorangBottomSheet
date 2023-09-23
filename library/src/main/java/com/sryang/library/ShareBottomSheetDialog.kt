package com.sryang.library

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
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShareBottomSheetDialog(
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
                    .padding(start = 10.dp, end = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ShareSearchBar()
                Spacer(modifier = Modifier.height(8.dp))
                ItemShareList()
            }
        }) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {

        }
    }

}

@Preview
@Composable
fun PreviewShareBottomSheetDialog() {
    ShareBottomSheetDialog(isExpand = true, onSelect = {

    })
}


data class ShareProfile(
    val profileUrl: Any,
    val id: String,
    val name: String,
    val date: String
)

@Preview
@Composable
fun ItemShareList() {

    val list = arrayOf(
        ShareProfile(
            profileUrl = "http://sarang628.iptime.org:89/1.png",
            id = "momo",
            name = "모모 (MOMO)",
            date = ""
        ),
        ShareProfile(
            profileUrl = "http://sarang628.iptime.org:89/2.png",
            id = "J",
            name = "jennierbyjane",
            date = ""
        ),
        ShareProfile(
            profileUrl = "http://sarang628.iptime.org:89/3.png",
            id = "lalalalisa_m",
            name = "LISA",
            date = ""
        ),
        ShareProfile(
            profileUrl = "http://sarang628.iptime.org:89/4.png",
            id = "kimkardashian",
            name = "Kim Kardashian",
            date = ""
        ),
        ShareProfile(
            profileUrl = "http://sarang628.iptime.org:89/5.png",
            id = "",
            name = "kevinhart4real",
            date = ""
        ),
        ShareProfile(
            profileUrl = "http://sarang628.iptime.org:89/6.png",
            id = "nickminaj",
            name = "Barbie",
            date = ""
        ),
        ShareProfile(
            profileUrl = "http://sarang628.iptime.org:89/7.png",
            id = "",
            name = "champagnepapi",
            date = ""
        ),
        ShareProfile(
            profileUrl = "http://sarang628.iptime.org:89/8.png",
            id = "youtube",
            name = "google",
            date = ""
        )
    )

    Box {
        LazyColumn(content = {
            items(list.size) {
                Column {
                    ItemShare(
                        profileUrl = list[it].profileUrl,
                        name = list[it].name,
                        date = list[it].date,
                        id = list[it].id
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        })
    }
}

@Composable
fun ItemShare(profileUrl: Any, id: String, name: String, date: String) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = profileUrl,
            contentDescription = "",
            modifier = Modifier
                .size(50.dp)
        )
        Column(
            Modifier
                .weight(1f)
                .padding(start = 8.dp)
        ) {
            Row {
                Text(text = name)
                Text(text = date)
            }
            Text(text = id, color = Color.Gray, fontSize = 14.sp)
        }
        AsyncImage(
            model = R.drawable.ic_select,
            contentDescription = "",
            modifier = Modifier
                .size(25.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
    }
}
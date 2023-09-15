package com.sryang.library

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedMenuBottomSheetDialog(
    isExpand: Boolean,
    onSelect: (String) -> Unit
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
        sheetContent = {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FeedMenu()
            }
        }) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {

        }
    }

}

@Preview
@Composable
fun test1() {
    FeedMenuBottomSheetDialog(isExpand = true, onSelect = {

    })
}

@Preview
@Composable
fun SaveButton(size: Dp) {
    Box()
    {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.Black)
                .size(size)
                .align(Alignment.Center)
        )

        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.White)
                .size((size - 3.dp))
                .align(Alignment.Center)
        )

        Image(
            painter = painterResource(id = R.drawable.save),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.Center)
                .size(size = size - 15.dp)
        )
    }
}

@Preview
@Composable
fun QRButton(size: Dp) {
    Box()
    {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.Black)
                .size(size)
                .align(Alignment.Center)
        )

        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.White)
                .size(size - 3.dp)
                .align(Alignment.Center)
        )

        Image(
            painter = painterResource(id = R.drawable.qr),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.Center)
                .size(size = size - 15.dp)
        )
    }
}

@Preview
@Composable
fun FeedMenu() {
    val iconSize = 30.dp
    val rowHeight = 50.dp
    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                SaveButton(60.dp)
                Text(text = "Save")
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                QRButton(60.dp)
                Text(text = "QR code")
            }

        }
        Text(
            text = "",
            Modifier
                .background(Color.LightGray)
                .fillMaxWidth()
                .height(1.dp)
        )
        Row(
            modifier = Modifier
                .height(rowHeight)
                .padding(start = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(iconSize),
                painter = painterResource(id = R.drawable.ic_share), contentDescription = ""
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = "We're moving things around!")
                Text(text = "See where to share and link")
            }
        }
        Text(
            text = "",
            Modifier
                .background(Color.LightGray)
                .fillMaxWidth()
                .height(1.dp)
        )
        Row(
            modifier = Modifier
                .height(rowHeight)
                .padding(start = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(iconSize),
                painter = painterResource(id = R.drawable.ic_information),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Why you're seeing this post")
        }
        Row(
            modifier = Modifier
                .height(rowHeight)
                .padding(start = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(iconSize),
                painter = painterResource(id = R.drawable.ic_people), contentDescription = ""
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "About this account")
        }
        Row(
            modifier = Modifier
                .height(rowHeight)
                .padding(start = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(iconSize),
                painter = painterResource(id = R.drawable.ic_report), contentDescription = ""
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Report")
        }
    }
}

@Preview
@Composable
fun PreviewFeedMenuBottomSheetDialog() {
    FeedMenuBottomSheetDialog(isExpand = true, onSelect = {})
}
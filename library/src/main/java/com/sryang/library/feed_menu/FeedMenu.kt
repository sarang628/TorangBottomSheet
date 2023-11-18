package com.sryang.library.feed_menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sryang.library.R

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
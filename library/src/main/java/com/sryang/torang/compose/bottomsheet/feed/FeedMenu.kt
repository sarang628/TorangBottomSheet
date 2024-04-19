package com.sryang.torang.compose.bottomsheet.feed

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sryang.torang.R

@Composable
fun MyMenu(onDelete: () -> Unit, onEdit: () -> Unit) {
    Column(Modifier.fillMaxWidth()) { //SaveAndQRCode()
        FeedMenuRow(R.drawable.ic_information, "Edit", onEdit)
        FeedMenuRow(R.drawable.ic_people, "Delete", onDelete)
    }
}

@Composable
fun ReportMenu(onReport: () -> Unit) {
    Column(Modifier.fillMaxWidth()) {
        FeedMenuRow(R.drawable.ic_report, "Report", onReport)
    }
}

@Preview
@Composable
fun PreviewFeedMenu() {
    MyMenu(onDelete = {}, onEdit = {})
}

@Preview
@Composable
fun PreviewReportMenu() {
    ReportMenu() {

    }
}

@Composable
fun SaveAndQRCode() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp), horizontalArrangement = Arrangement.SpaceAround
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
}

@Composable
fun WeAreMovingThingsAround() {
    val iconSize = 30.dp
    val rowHeight = 50.dp
    Row(
        modifier = Modifier
            .height(rowHeight)
            .padding(start = 10.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(iconSize),
            painter = painterResource(id = R.drawable.ic_share),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = "We're moving things around!")
            Text(text = "See where to share and link")
        }
    }
}

@Composable
fun FeedMenuRow(res: Int, desc: String, onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val iconSize = 30.dp
    val rowHeight = 50.dp
    Row(modifier = Modifier
        .height(rowHeight)
        .fillMaxWidth()
        .clickable(interactionSource = interactionSource, indication = null) {
            onClick.invoke()
        }
        .padding(start = 10.dp), verticalAlignment = Alignment.CenterVertically) {
        Image(
            modifier = Modifier.size(iconSize),
            painter = painterResource(id = res),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = desc)
    }
}
package com.sryang.torang.compose.bottomsheet.feed

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sarang.torang.R

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
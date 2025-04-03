package com.sarang.torang.compose.bottomsheet.share.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sarang.torang.R

@Composable
fun ItemShare(profileUrl: Any, id: Int, name: String, date: String)
{
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(model = profileUrl, contentDescription = "", modifier = Modifier
                .size(40.dp)
                .clip(CircleShape), contentScale = ContentScale.Crop)
        Column(Modifier
                .weight(1f)
                .padding(start = 8.dp)) {
            Row {
                Text(text = name)
                Text(text = date)
            }
            Text(text = "$id", color = Color.Gray, fontSize = 14.sp)
        }
        AsyncImage(model = R.drawable.ic_select, contentDescription = "", modifier = Modifier.size(25.dp))
        Spacer(modifier = Modifier.width(8.dp))
    }
}
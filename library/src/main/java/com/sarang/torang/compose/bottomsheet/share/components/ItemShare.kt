package com.sarang.torang.compose.bottomsheet.share.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sarang.torang.R

@Composable
fun ItemShare(
    profileUrl: Any     = "",
    name:       String  = ""
) {
    Column (
        modifier = Modifier.width(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Box(
            modifier = Modifier.size(80.dp),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = profileUrl,
                contentDescription = "",
                placeholder = painterResource(R.drawable.ic_share),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .matchParentSize()
                    .clip(CircleShape)
            )

            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomEnd) // 👈 Box 내부 정렬
                    .offset(x = (-6).dp, y = (-6).dp) // 👈 경계 안쪽으로 이동
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF8888FF))
                    .padding(4.dp)
            )
        }
        Text(
            text = name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

    }
}

@Preview
@Composable
fun PreviewItemShare(){
    ItemShare(
        name = "torangtorangtorangtorangtorangtorang",
    )
}
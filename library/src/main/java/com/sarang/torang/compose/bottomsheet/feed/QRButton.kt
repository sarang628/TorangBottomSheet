package com.sarang.torang.compose.bottomsheet.feed

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sarang.torang.R

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
fun PreviewQRButton() {
    QRButton(size = 50.dp)
}
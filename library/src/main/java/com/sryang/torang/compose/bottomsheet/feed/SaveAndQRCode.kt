package com.sryang.torang.compose.bottomsheet.feed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
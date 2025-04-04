package com.sarang.torang.compose.bottomsheet.bottomsheetscaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/**
 */
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TorangModalBottomSheet() {
    Box(modifier = Modifier.fillMaxSize()) {
        ModalBottomSheet(onDismissRequest = { /*TODO*/ }) {
            Text(text = "!!!!!!", modifier = Modifier.fillMaxHeight())
        }
        Button(onClick = { /*TODO*/ }, Modifier.align(Alignment.BottomCenter)) {
            Text(text = "!!!!!!!")
        }
    }
}
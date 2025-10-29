package com.sarang.torang.compose.bottomsheet.share.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun SendShare(onSend: (String) -> Unit = {}) {
    var text by remember { mutableStateOf("") }
    Column(Modifier.padding(start = 10.dp, end = 10.dp)) {
        BasicTextField(
            modifier = Modifier.height(50.dp),
            value = text,
            onValueChange = { text = it },
            decorationBox = {
                if(text.isEmpty()) {
                    Text("메시지 쓰기...")
                }else {
                    it()
                }
            })
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = { onSend.invoke(text) },
            shape = RoundedCornerShape(10.dp)
        ) {
            Text("보내기")
        }
    }
}
package com.sryang.library

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ShareSearchBar() {
    Box(
        Modifier
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .height(40.dp)
            .background(
                Color(0xFFEEEEEE)
            )

    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "",
                modifier = Modifier
                    .size(30.dp)
            )

            OutlinedTextField(
                value = "input",
                placeholder = {
                    Text(text = "Write a caption", color = Color.Gray, fontSize = 14.sp)
                },
                onValueChange = {

                },
                modifier = Modifier
                    .height(50.dp)
                    .weight(1f),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                )
            )

            Image(
                painter = painterResource(id = R.drawable.ic_share_add),
                contentDescription = "",
                modifier = Modifier
                    .size(30.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
        }

    }
}
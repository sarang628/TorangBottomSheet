package com.sryang.library.share

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun ItemShareList(list: List<ShareProfile>) {
    Box {
        LazyColumn(content = {
            items(list.size) {
                Column {
                    ItemShare(
                        profileUrl = list[it].profileUrl,
                        name = list[it].name,
                        date = list[it].date,
                        id = list[it].id
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        })
    }
}
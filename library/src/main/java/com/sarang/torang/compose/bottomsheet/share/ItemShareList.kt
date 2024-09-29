package com.sarang.torang.compose.bottomsheet.share

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sarang.torang.data.User

@Composable
fun ItemShareList(list: List<User>, profileServerUrl: String)
{
    Box {
        LazyColumn(content = {
            items(list.size) {
                Column {
                    ItemShare(profileUrl = profileServerUrl + list[it].picture, name = list[it].userName, date = "", id = list[it].userId)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        })
    }
}
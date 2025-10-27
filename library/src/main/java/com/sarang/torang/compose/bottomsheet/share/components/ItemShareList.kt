package com.sarang.torang.compose.bottomsheet.share.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sarang.torang.data.bottomsheet.Sample
import com.sarang.torang.data.bottomsheet.User

@Composable
fun ItemShareList(
    list: List<User> = listOf(),
    onClick : (Int) -> Unit = {}
)
{
    LazyVerticalGrid(
        modifier = Modifier.fillMaxWidth(),
        columns = GridCells.FixedSize(100.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        content = {
        items(list.size) {
            Column {
                ItemShare(
                    profileUrl  = list[it].picture,
                    name        = list[it].userName,
                    onClick     = { onClick(list[it].userId) },
                    isSelected  = list[it].isSelected
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    })
}

@Preview
@Composable
fun PreviewItemShareList(){
    ItemShareList(list = listOf(/*Preview*/
        User.Sample,
        User.Sample,
        User.Sample,
        User.Sample,
        User.Sample,
        User.Sample,
    ))
}
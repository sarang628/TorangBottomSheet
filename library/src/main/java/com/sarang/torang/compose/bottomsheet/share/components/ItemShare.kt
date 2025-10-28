package com.sarang.torang.compose.bottomsheet.share.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sarang.torang.R

@Composable
fun ItemShare(
    profileUrl  : Any           = "",
    name        : String        = "",
    isSelected  : Boolean       = false,
    onClick     : () -> Unit    = {}
) {
    Column (
        modifier = Modifier
            .width(80.dp)
            .clickable(enabled = true,
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Box(
            modifier = Modifier.size(80.dp),
            contentAlignment = Alignment.Center
        ) {
            LocalShareImageLoad.current.invoke(
                ShareImageLoaderData(
                    model = profileUrl,
                    //placeholder = painterResource(R.drawable.ic_share),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .matchParentSize()
                        .clip(CircleShape)
                )
            )
            /*AsyncImage(
                model = profileUrl,
                contentDescription = "",
                placeholder = painterResource(R.drawable.ic_share),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .matchParentSize()
                    .clip(CircleShape)
            )*/

            if(isSelected)
            {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = (-3).dp, y = (-2).dp)
                        .border(width = 1.dp, color = Color.White, shape = CircleShape)
                        .size(20.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF8888FF))
                        .padding(4.dp)
                )
            }
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
    ItemShare(/*Preview*/
        name = "torangtorangtorangtorangtorangtorang",
        isSelected = true
    )
}
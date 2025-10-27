package com.sarang.torang.compose.bottomsheet.share.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun ShareBottomMenus(
    onLinkCopy  : () -> Unit = {},
    onAddStory  : () -> Unit = {},
    onShare     : () -> Unit = {},
) {
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        item {
            ShareBottomMenu(
                text = "링크 복사",
                imageVector = Icons.Outlined.AddCircle,
                onClick = onLinkCopy
            )
            ShareBottomMenu(
                text = "스토리에\n추가",
                imageVector = Icons.Outlined.CheckCircle,
                onClick = onAddStory
            )
            ShareBottomMenu(
                text = "공유하기",
                imageVector = Icons.Outlined.Share,
                onClick = onShare
            )
        }
    }
}

@Composable
fun ShareBottomMenu(
    text        : String        = "",
    onClick     : () -> Unit    = {},
    imageVector : ImageVector
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
                    .width(90.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        enabled = true,
                        onClick = onClick
                    )
    ) {
        Icon(
            modifier = Modifier
                .size(55.dp)
                .clip(CircleShape)
                .background(Color(0xFFEEEEEE))
                .padding(10.dp),
            imageVector = imageVector,
            contentDescription = null
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = text,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun PreviewShareBottomMenu(){
    ShareBottomMenu(
        text            = "링크 복사",
        imageVector     = Icons.Outlined.AddCircle
    )
}
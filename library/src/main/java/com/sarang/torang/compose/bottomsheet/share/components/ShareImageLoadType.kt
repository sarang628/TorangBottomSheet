package com.sarang.torang.compose.bottomsheet.share.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

typealias ShareImageLoadType = @Composable (data: ShareImageLoaderData) -> Unit

data class ShareImageLoaderData(
    val modifier: Modifier = Modifier,
    val model: Any = "",
    val width: Dp? = 30.dp,
    val height: Dp? = 30.dp,
    val contentScale: ContentScale = ContentScale.Fit
)

val LocalShareImageLoad = compositionLocalOf<ShareImageLoadType> {
    @Composable {
        Log.w("__ShareImageLoadType", "image loader isn't set")
    }
}
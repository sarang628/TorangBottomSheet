package com.sarang.torang.compose.bottomsheet.feed.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sarang.torang.R

@Composable
fun ReportMenu(onReport: () -> Unit) {
    Column(Modifier.fillMaxWidth()) {
        FeedMenuRow(R.drawable.ic_report, "Report", onReport)
    }
}

@Preview
@Composable
fun PreviewReportMenu() {
    ReportMenu() {

    }
}

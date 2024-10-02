package com.sarang.torangbottomsheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.samples.apps.sunflower.ui.TorangTheme
import com.sarang.torang.compose.bottomsheet.PreviewCommentBottomSheetDialog
import com.sarang.torang.repository.FeedRepository
import com.sarang.torang.repository.LoginRepository
import com.sarang.torang.compose.bottomsheet.bottomsheetscaffold.TorangCommentBottomSheetScaffold
import com.sarang.torang.compose.bottomsheet.feed.PreviewFeedMenuBottomSheetDialog
import com.sarang.torang.compose.bottomsheet.share.PreviewShareBottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var loginRepository: LoginRepository

    @Inject
    lateinit var feedRepository: FeedRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TorangTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //test()
                    Column(Modifier.verticalScroll(rememberScrollState())) {
//                        FeedRepositoryTest(feedRepository = feedRepository)
//                        PreviewCommentBottomSheetDialog()
//                        PreviewShareBottomSheetDialog()
                        //LoginRepositoryTest(loginRepository = loginRepository)
//                        PreviewFeedMenuBottomSheetDialog()
                        /*FeedMenuBottomSheetDialog(
                            isExpand = true,
                            onReport = {},
                            onDelete = {},
                            onEdit = {},
                            onClose = {},
                            reviewId = 342
                        )*/
                    }
                    TorangCommentBottonSheetScaffoldTest()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TorangCommentBottonSheetScaffoldTest() {
    var show by remember { mutableStateOf(false) }
    TorangCommentBottomSheetScaffold(
        input = { OutlinedTextField(value = "", onValueChange = {}) },
        show = show,
        sheetContent = { Box(Modifier.fillMaxHeight()) },
        sheetPeekHeight = 350.dp,
        inputHiddenOffset = 200.dp,
        onHidden = { show = false },
        content = {
            Column(Modifier.fillMaxHeight().fillMaxWidth()) {
                Text(text = "Torang Bottom Sheet Test")
                Button(onClick = { show = true }) { Text(text = "show") }
            }
        }
    )
}
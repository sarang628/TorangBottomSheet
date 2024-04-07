package com.sryang.torangbottomsheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.samples.apps.sunflower.ui.TorangTheme
import com.sarang.torang.repository.FeedRepository
import com.sarang.torang.repository.LoginRepository
import com.sryang.torang.compose.bottomsheet.bottomsheetscaffold.TorangCommentBottomSheetScaffold
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var loginRepository: LoginRepository

    @Inject
    lateinit var feedRepository: FeedRepository

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TorangTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TorangCommentBottomSheetScaffold(
                        input = { OutlinedTextField(value = "", onValueChange = {}) },
                        topBar = { SmallTopAppBar(title = { Text(text = "Torang") }) },
                        sheetContent = {
                            Box(
                                Modifier
                                    .fillMaxHeight()
                                    .padding(bottom = 60.dp)
                            ) {
                                Column(modifier = Modifier.fillMaxSize().background(Color.LightGray)) {
                                    Text(text = "aaaaa")
                                }
                            }
                        },
                        sheetPeekHeight = 350.dp,
                        inputHiddenOffset = 200.dp
                    ) {
                        Column(
                            Modifier
                                .padding(it)
                                .fillMaxHeight()
                                .fillMaxWidth()
                        ) {
                            Text(text = "Torang Bottom Sheet Test")
                        }
                    }
                    //Column(Modifier.verticalScroll(rememberScrollState())) {
                    //FeedRepositoryTest(feedRepository = feedRepository)
                    //PreviewCommentBottomSheetDialog()
                    //PreviewShareBottomSheetDialog()
                    //PreviewShareBottomSheetDialog()
                    //LoginRepositoryTest(loginRepository = loginRepository)
                    //PreviewFeedMenuBottomSheetDialog()
                    //}
                }
            }
        }
    }
}
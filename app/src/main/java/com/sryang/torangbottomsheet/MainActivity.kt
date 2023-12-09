package com.sryang.torangbottomsheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.google.samples.apps.sunflower.ui.TorangTheme
import com.sryang.torang.compose.bottomsheet.feed.PreviewFeedMenuBottomSheetDialog
import com.sryang.torang.compose.bottomsheet.share.PreviewShareBottomSheetDialog
import com.sryang.torang_repository.repository.FeedRepository
import com.sryang.torang_repository.repository.FeedRepositoryTest
import com.sryang.torang_repository.repository.LoginRepository
import com.sryang.torang_repository.repository.LoginRepositoryTest
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
                    Column(Modifier.verticalScroll(rememberScrollState())) {
                        FeedRepositoryTest(feedRepository = feedRepository)
                        //PreviewCommentBottomSheetDialog()
                        //PreviewShareBottomSheetDialog()
                        //PreviewShareBottomSheetDialog()
                        LoginRepositoryTest(loginRepository = loginRepository)
                        PreviewFeedMenuBottomSheetDialog()
                    }
                }
            }

        }
    }
}
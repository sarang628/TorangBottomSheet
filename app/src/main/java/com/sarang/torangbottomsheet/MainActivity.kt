package com.sarang.torangbottomsheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.samples.apps.sunflower.ui.TorangTheme
import com.sarang.torang.compose.bottomsheet.ImageSelectBottomSheetScaffold
import com.sarang.torang.compose.bottomsheet.PreviewCloseDetectBottomSheetScaffold
import com.sarang.torang.compose.bottomsheet.PreviewCommentBottomSheetDialog
import com.sarang.torang.compose.bottomsheet.PreviewFolderListBottomSheetDialog
import com.sarang.torang.compose.bottomsheet.bottomsheetscaffold.PreviewTorangBottomSheetScaffold
import com.sarang.torang.compose.bottomsheet.bottomsheetscaffold.PreviewTorangCommentBottomSheetScaffold
import com.sarang.torang.compose.bottomsheet.bottomsheetscaffold.TorangCommentBottomSheetScaffold
import com.sarang.torang.compose.bottomsheet.bottomsheetscaffold.TorangModalBottomSheet
import com.sarang.torang.compose.bottomsheet.feed.PreviewFeedMenuBottomSheetDialog
import com.sarang.torang.compose.bottomsheet.practice.ModalBottomSheetPractice1
import com.sarang.torang.compose.bottomsheet.practice.PartialBottomSheet
import com.sarang.torang.compose.bottomsheet.share.PreviewShareBottomSheetDialog
import com.sarang.torang.repository.FeedRepository
import com.sarang.torang.repository.LoginRepository
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
                    TestNavigation()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Preview
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
                Column(
                    Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                ) {
                    Text(text = "Torang Bottom Sheet Test")
                    Button(onClick = { show = true }) { Text(text = "show") }
                }
            }
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewImageSelectBottomSheetDialog() {
        var show by remember { mutableStateOf(false) }
        Box(modifier = Modifier.fillMaxSize()) {
            ImageSelectBottomSheetScaffold(
                show = show,
                onHidden = { show = false },
                imageSelectCompose = {}
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Button(onClick = { show = true }) {
                        Text(text = "ImageSelectBottomSheetScaffold")
                    }
                }
            }
        }
    }

    @Preview
    @Composable
    fun TestNavigation() {
        val navController = rememberNavController()
        Box(Modifier.fillMaxSize())
        {
            NavHost(navController, startDestination = "test") {
                composable("test") {
                    Column {
                        Button({
                            navController.navigate("ModalBottomSheetPractice1")
                        }) {
                            Text("ModalBottomSheetPractice1")
                        }

                        Button({
                            navController.navigate("ImageSelectBottomSheetDialog")
                        }) {
                            Text("ImageSelectBottomSheetDialog")
                        }

                        Button({
                            navController.navigate("PartialBottomSheet")
                        }) {
                            Text("PartialBottomSheet")
                        }

                        Button({
                            navController.navigate("TorangBottomSheetScaffold")
                        }) {
                            Text("TorangBottomSheetScaffold")
                        }

                        Button({
                            navController.navigate("TorangCommentBottomSheetScaffold")
                        }) {
                            Text("TorangCommentBottomSheetScaffold")
                        }

                        Button({
                            navController.navigate("TorangModalBottomSheet")
                        }) {
                            Text("TorangModalBottomSheet")
                        }

                        Button({
                            navController.navigate("FeedMenuBottomSheetDialog")
                        }) {
                            Text("FeedMenuBottomSheetDialog")
                        }

                        Button({
                            navController.navigate("ShareBottomSheetDialog")
                        }) {
                            Text("ShareBottomSheetDialog")
                        }

                        Button({
                            navController.navigate("CloseDetectBottomSheetScaffold")
                        }) {
                            Text("CloseDetectBottomSheetScaffold")
                        }

                        Button({
                            navController.navigate("CommentBottomSheetDialog")
                        }) {
                            Text("CommentBottomSheetDialog")
                        }
                        Button({
                            navController.navigate("FolderListBottomSheetDialog")
                        }) {
                            Text("FolderListBottomSheetDialog")
                        }
                        Button({
                            navController.navigate("ImageSelectBottomSheetScaffold")
                        }) {
                            Text("ImageSelectBottomSheetScaffold")
                        }
                    }

                }
                composable("ModalBottomSheetPractice1") {
                    ModalBottomSheetPractice1()
                }
                composable("ImageSelectBottomSheetDialog") {
                    PreviewImageSelectBottomSheetDialog()
                }
                composable("PartialBottomSheet") {
                    PartialBottomSheet()
                }
                composable("TorangBottomSheetScaffold") {
                    PreviewTorangBottomSheetScaffold()
                }
                composable("TorangCommentBottomSheetScaffold") {
                    PreviewTorangCommentBottomSheetScaffold()
                }
                composable("TorangModalBottomSheet") {
                    TorangModalBottomSheet()
                }
                composable("FeedMenuBottomSheetDialog") {
                    PreviewFeedMenuBottomSheetDialog()
                }
                composable("ShareBottomSheetDialog") {
                    PreviewShareBottomSheetDialog()
                }
                composable("CloseDetectBottomSheetScaffold") {
                    PreviewCloseDetectBottomSheetScaffold()
                }
                composable("CommentBottomSheetDialog") {
                    PreviewCommentBottomSheetDialog()
                }
                composable("FolderListBottomSheetDialog") {
                    PreviewFolderListBottomSheetDialog()
                }
                composable("ImageSelectBottomSheetScaffold") {
                    PreviewImageSelectBottomSheetDialog()
                }

            }
        }
    }
}
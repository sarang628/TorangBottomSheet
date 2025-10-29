package com.sarang.torangbottomsheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocal
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sarang.torang.compose.bottomsheet.PickHeight70PercentBottomSheetScaffold
import com.sarang.torang.compose.bottomsheet.PreviewCloseDetectBottomSheetScaffold
import com.sarang.torang.compose.bottomsheet.PreviewPartiallyModalBottomSheet
import com.sarang.torang.compose.bottomsheet.PreviewSimpleTextListBottomSheetScaffold
import com.sarang.torang.compose.bottomsheet.bottomsheetscaffold.FixedInputBottomSheetScaffold
import com.sarang.torang.compose.bottomsheet.bottomsheetscaffold.PreviewFixedInputBottomSheetScaffold
import com.sarang.torang.compose.bottomsheet.bottomsheetscaffold.PreviewTorangBottomSheetScaffold
import com.sarang.torang.compose.bottomsheet.bottomsheetscaffold.TorangModalBottomSheet
import com.sarang.torang.compose.bottomsheet.feed.PreviewFeedMenuModalBottomSheet
import com.sarang.torang.compose.bottomsheet.practice.ModalBottomSheetPractice1
import com.sarang.torang.compose.bottomsheet.practice.PartialBottomSheet
import com.sarang.torang.compose.bottomsheet.share.PreviewShareModalBottomSheet
import com.sarang.torang.compose.bottomsheet.share.ShareModalBottomSheet
import com.sarang.torang.compose.bottomsheet.share.components.LocalShareImageLoad
import com.sarang.torang.di.bottomsheet_di.CustomShareImageLoader
import com.sarang.torang.repository.FeedRepository
import com.sarang.torang.repository.LoginRepository
import com.sarang.torang.repository.LoginRepositoryTest
import com.sryang.torang.ui.TorangTheme
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
        enableEdgeToEdge()
        setContent {
            TorangTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(Modifier.fillMaxSize()) {
                        TestNavigation(loginRepository = loginRepository)
                    }
                }
            }
        }
    }
}

@Composable
fun TestNavigation(loginRepository : LoginRepository) {
    val navController = rememberNavController()
    Box(Modifier.fillMaxSize())
    {
        NavHost(navController, startDestination = "test") {
            composable("test")                                      { test(navController) }
            composable("LoginRepositoryTest")                       { LoginRepositoryTest(loginRepository = loginRepository) }
            composable("ModalBottomSheetPractice1")                 { ModalBottomSheetPractice1() }
            composable("PickHeight70PercentBottomSheetScaffold")    { PreviewPickHeight70PercentBottomSheetScaffold() }
            composable("PartialBottomSheet")                        { PartialBottomSheet() }
            composable("TorangBottomSheetScaffold")                 { PreviewTorangBottomSheetScaffold() }
            composable("FixedInputBottomSheetScaffold")             { PreviewFixedInputBottomSheetScaffold() }
            composable("TorangModalBottomSheet")                    { TorangModalBottomSheet() }
            composable("FeedMenuModalBottomSheet")                  { PreviewFeedMenuModalBottomSheet() }
            composable("ShareModalBottomSheet")                     {
                CompositionLocalProvider(
                    LocalShareImageLoad provides CustomShareImageLoader
                ) {
                    ShareModalBottomSheet(isExpand = true)
                }
            }
            composable("CloseDetectBottomSheetScaffold")            { PreviewCloseDetectBottomSheetScaffold() }
            composable("PartiallyModalBottomSheet")                 { PreviewPartiallyModalBottomSheet() }
            composable("SimpleTextListBottomSheetScaffold")         { PreviewSimpleTextListBottomSheetScaffold() }
        }
    }
}

@Composable
fun test(navController : NavHostController = rememberNavController()){
    Scaffold {
        Column(Modifier.padding(it)) {
            Text("basic")
            Button({
                navController.navigate("ModalBottomSheetPractice1")
            }) {
                Text("ModalBottomSheet")
            }
            Button({
                navController.navigate("PartialBottomSheet")
            }) {
                Text("PartialBottomSheet")
            }
            HorizontalDivider()
            Text("basic application")
            Button({
                /** 클릭 시 이동 [PreviewTorangBottomSheetScaffold] */
                navController.navigate("TorangBottomSheetScaffold")
            }) {
                Text("TorangBottomSheetScaffold")
            }
            Button({
                navController.navigate("TorangModalBottomSheet")
            }) {
                Text("TorangModalBottomSheet")
            }
            HorizontalDivider()
            Text("torang application")
            Button({
                navController.navigate("PickHeight70PercentBottomSheetScaffold")
            }) {
                Text("PickHeight70PercentBottomSheetScaffold")
            }

            Button({
                /** [PreviewFixedInputBottomSheetScaffold] */
                navController.navigate("FixedInputBottomSheetScaffold")
            }) {
                Text("FixedInputBottomSheetScaffold")
            }

            Button({
                navController.navigate("FeedMenuModalBottomSheet")
            }) {
                Text("FeedMenuModalBottomSheet")
            }

            Button({
                navController.navigate("ShareModalBottomSheet")
            }) {
                Text("ShareModalBottomSheet")
            }

            Button({
                navController.navigate("CloseDetectBottomSheetScaffold")
            }) {
                Text("CloseDetectBottomSheetScaffold")
            }

            Button({
                navController.navigate("PartiallyModalBottomSheet")
            }) {
                Text("PartiallyModalBottomSheet")
            }
            Button({
                navController.navigate("SimpleTextListBottomSheetScaffold")
            }) {
                Text("SimpleTextListBottomSheetScaffold")
            }
            Button({
                navController.navigate("LoginRepositoryTest")
            }) {
                Text("LoginRepositoryTest")
            }


        }
    }
}

@Preview
@Composable
fun InputInteractBottomSheetScaffoldTest() {
    var show by remember { mutableStateOf(false) }
    FixedInputBottomSheetScaffold(
        input = { OutlinedTextField(value = "", onValueChange = {}) },
        show = show,
        sheetContent = { Box(Modifier.fillMaxHeight()) },
        inputHeight = 200.dp,
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
fun PreviewPickHeight70PercentBottomSheetScaffold() {
    var show by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()) {
        PickHeight70PercentBottomSheetScaffold(
            show = show,
            onHidden = { show = false },
            imageSelectCompose = {}
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Button(onClick = { show = true }) {
                    Text(text = "PickHeight70PercentBottomSheetScaffold")
                }
            }
        }
    }
}
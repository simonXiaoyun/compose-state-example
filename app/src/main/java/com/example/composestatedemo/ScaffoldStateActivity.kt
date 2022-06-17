package com.example.composestatedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composestatedemo.ui.theme.ComposeStateDemoTheme
import kotlinx.coroutines.launch

class ScaffoldStateActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeStateDemoTheme {
                val scaffoldState = rememberScaffoldState()
                val coroutineScope = rememberCoroutineScope()
                val isShow = remember {
                    mutableStateOf(false)
                }
                Scaffold(scaffoldState = scaffoldState,
                    drawerContent = {
                        CraneDrawer()
                    },
                    topBar = {
                        TopAppBar(title = {
                            Text(text = "Compose State learn", color = Color.White)
                        })
                    },
                    content = {
                        Column(
                            Modifier
                                .fillMaxHeight()
                                .padding(top = 10.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {

                            Button(onClick = {
                                coroutineScope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar("弹出snackbar")
                                }
                            }) {
                                Text(text = "测试Snackbar")
                            }

                            Button(onClick = {
                               isShow.value = true
                            }) {
                                Text(text = "测试Snackbar2")
                            }

                            Button(onClick = {
                                coroutineScope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            }) {
                                Text(text = "测试drawer")
                            }
                        }
                        if (isShow.value) {
                            LaunchedEffect(scaffoldState.snackbarHostState) {
                                scaffoldState.snackbarHostState.showSnackbar("弹出snackbar2")
                            }
                        }
                    }
                )

            }
        }
    }
}

@Composable
fun Greeting3(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    ComposeStateDemoTheme {
        Greeting3("Android")
    }
}
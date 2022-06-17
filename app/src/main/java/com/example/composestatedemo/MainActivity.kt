package com.example.composestatedemo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composestatedemo.demo.a.StateComposeButton
import com.example.composestatedemo.demo.b.TodoScreenWithStateHoist
import com.example.composestatedemo.demo.e.EditInformation
import com.example.composestatedemo.ui.theme.ComposeStateDemoTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
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
                            Modifier.fillMaxHeight().padding(top = 10.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            StateComposeButton()
                            Button(onClick = {
                                startActivity(Intent(this@MainActivity, TestActivity::class.java))
                            }) {
                                Text(text = "跳转到列表页")
                            }

                            Button(onClick = {
                                startActivity(Intent(this@MainActivity, ScaffoldStateActivity::class.java))
                            }) {
                                Text(text = "跳转TestStatePage")
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
fun CraneDrawer() {
    Column(Modifier.fillMaxWidth()) {
        Text(text = "drawerTest")
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeStateDemoTheme {
        Greeting("Android")
    }
}
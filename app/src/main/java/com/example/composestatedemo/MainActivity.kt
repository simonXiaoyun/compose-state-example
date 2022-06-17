package com.example.composestatedemo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composestatedemo.demo.b.TodoScreenWithStateHoist
import com.example.composestatedemo.ui.theme.ComposeStateDemoTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeStateDemoTheme {
                val scaffoldState = rememberScaffoldState()
                val coroutineScope = rememberCoroutineScope()
                Scaffold(scaffoldState = scaffoldState,
                    drawerContent = {
                        CraneDrawer()
                    },
                    topBar = {
                        TopAppBar(title = {
                            Text(text = "Compose State learn",color = Color.White)
                        })
                    },
                    content = {
                        Column(Modifier.fillMaxHeight(),verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            TodoScreenWithStateHoist(Modifier.fillMaxWidth())
                            Button(onClick = {
                                startActivity(Intent(this@MainActivity,TestActivity::class.java))
                            }) {
                                Text(text = "跳转到列表页")
                            }

                            Button(onClick = {
                                coroutineScope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar("弹出snackbar")
                                }
                            }) {
                                Text(text = "测试Snackbar")
                            }

                            Button(onClick = {
                                coroutineScope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            }) {
                                Text(text = "测试drawer")
                            }
                        }

                    }
                )

            }
        }
    }
}



@Composable
fun CraneDrawer(){
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
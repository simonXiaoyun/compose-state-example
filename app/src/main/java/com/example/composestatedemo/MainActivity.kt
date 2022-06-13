package com.example.composestatedemo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.composestatedemo.demo.a.StateComposeButton
import com.example.composestatedemo.ui.theme.ComposeStateDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeStateDemoTheme {
                Scaffold(topBar = {
                        TopAppBar(title = {
                            Text(text = "Compose State learn",color = Color.White)
                        })
                    },
                    content = {
                        Column(Modifier.fillMaxHeight()) {
                            StateComposeButton()
                            Button(onClick = {
                                startActivity(Intent(this@MainActivity,TestActivity::class.java))
                            }) {
                                Text(text = "跳转到列表页")
                            }
                        }

                    }
                )

            }
        }
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
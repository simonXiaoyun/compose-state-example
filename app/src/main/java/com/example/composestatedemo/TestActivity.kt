package com.example.composestatedemo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.composestatedemo.demo.d.TodoItem
import com.example.composestatedemo.demo.d.TodoScreen
import com.example.composestatedemo.demo.d.TodoScreen4
import com.example.composestatedemo.demo.d.TodoViewModel
import com.example.composestatedemo.ui.theme.ComposeStateDemoTheme
import java.util.*

class TestActivity : ComponentActivity() {
    private val todoViewModel by viewModels<TodoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeStateDemoTheme {
                val scaffoldState = rememberScaffoldState()
                Scaffold(scaffoldState = scaffoldState,
                    topBar = {
                        TopAppBar(title = {
                            Text(text = "测试列表页")
                        })
                    }, content = {
                        TodoActivityScreen(todoViewModel)
                    }, floatingActionButton = {
                        FloatingActionButton(onClick = {
                            todoViewModel.addItem(
                                TodoItem(
                                    UUID.randomUUID().toString(),
                                    "请填写内容",
                                    Icons.Default.Add
                                )
                            )
                            todoViewModel.todoItems.forEach {
                                Log.i("Simon","id:${it.id}--name:${it.name}")
                            }
                        }, backgroundColor = Color.DarkGray, contentColor = Color.White, content = {
                            Icon(imageVector = Icons.Default.Add, contentDescription = "")
                        })
                    })
            }
        }
    }
}

@Composable
private fun TodoActivityScreen(todoViewModel: TodoViewModel) {
    TodoScreen(items = todoViewModel.todoItems)
//    TodoScreen4(items = todoViewModel.todoItems,
//        changItem = { todoItem ->
//            todoViewModel.changItem(todoItem)
//
//        }, deleteItem = {
//            todoViewModel.removeItem(it)
//        })
}

@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ComposeStateDemoTheme {
        Greeting2("Android")
    }
}
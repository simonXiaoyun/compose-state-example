package com.example.composestatedemo.demo.b

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

//有状态的组件，也没有遵循单向数据流
@Composable
fun EditTextCompose() {
    val (text, setText) = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextField(value = text, onValueChange = setText)
    }

}
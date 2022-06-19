package com.example.composestatedemo.demo.b

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun TodoScreen(modifier: Modifier) {
    Row(modifier = modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically) {
        EditTextCompose(modifier = modifier.weight(6f))
        Text(text = "文字个数:0",modifier = modifier.weight(2f),color = Color.Black)
    }
}

@Composable
fun EditTextCompose(modifier: Modifier) {
    val (text, setText) = remember { mutableStateOf("") }
    val text1 = remember { mutableStateOf("") }
    Column(
        modifier = modifier
    ) {
        TextField(value = text1.value, onValueChange = { text1.value = it })
    }

}



/**-------------------**/
@Composable
fun TodoScreenWithStateHoist(modifier: Modifier) {
    Log.i("Simon","222")
    Row(modifier = modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically) {
        val (text, setText) = remember { mutableStateOf("") }
        EditTextComposeWithStateHoist(modifier = modifier.weight(6f),text = text,changText = setText)
        Text(text = "文字个数:${text.length}",modifier = modifier.weight(2f),color = Color.Black)
    }
}

@Composable
fun EditTextComposeWithStateHoist(modifier: Modifier,text: String, changText: (String) -> Unit) {
    Column(
        modifier = modifier
    ) {
        TextField(value = text, onValueChange = changText)
    }

}
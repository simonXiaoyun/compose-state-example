package com.example.composestatedemo.demo.a

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StateComposeButtonNew(){
    var numText = mutableStateOf(1)
//    Log.i("Simon","值${numText.value}")
    Column(modifier = Modifier
        .fillMaxWidth()) {
        Button(
            modifier = Modifier
                .padding(10.dp)
                .width(100.dp)
                .height(100.dp),
            onClick = { numText.value = numText.value + 1 },
        ) {
            Text(text = "数量${numText.value}")
        }
    }
}
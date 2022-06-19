package com.example.composestatedemo.demo.a

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StateComposeButton() {
    var numText = 1
    Button(
        modifier = Modifier
            .padding(10.dp)
            .width(100.dp)
            .height(100.dp),
        onClick = { numText++ },
    ) {
        Text(text = "数量${numText}")
    }
}
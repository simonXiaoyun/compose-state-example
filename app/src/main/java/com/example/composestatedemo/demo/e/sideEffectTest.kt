package com.example.composestatedemo.demo.e

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EditInformation() {
    var name = "Test"
    Column(Modifier.padding(10.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = "odl:$name")
            name = "NewTest"
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = "new:$name")
        }
    }
}

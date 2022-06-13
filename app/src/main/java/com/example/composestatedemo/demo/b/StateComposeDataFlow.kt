package com.example.composestatedemo.demo.b

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StateComposeButtonDataFlow(viewModel: DataFlowViewModel){
    val num:Int by viewModel.num.observeAsState(1)
        Log.i("Simon","值$num")
    Column(modifier = Modifier
        .fillMaxWidth()) {
        Button(modifier = Modifier
            .padding(10.dp)
            .width(100.dp)
            .height(100.dp),onClick = {
            viewModel.changNumText(num+1)},) {
            Text(text = "单向数据流数量$num")
        }

    }

}
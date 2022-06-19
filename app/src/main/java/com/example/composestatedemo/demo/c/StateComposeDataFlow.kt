package com.example.composestatedemo.demo.c

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
fun StateComposeButtonDataFlow(num:Int, numChang:(Int)->Unit) {
    Log.i("Simon", "值$num")
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Button(
            modifier = Modifier
                .padding(10.dp)
                .width(100.dp)
                .height(100.dp),
            onClick = {
                numChang(num)
            },
        ) {
            Text(text = "数量ww$num")
        }
//        addNumButton(modifier = Modifier.fillMaxWidth(),num = num,addNum = viewModel::changNumText)

    }

}

@Composable
fun addNumButton(modifier: Modifier, num: Int, addNum: (Int) -> Unit) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Button(
            modifier = modifier
                .padding(10.dp)
                .width(100.dp)
                .height(100.dp),
            onClick = {
                addNum(num)
            },
        ) {
            Text(text = "单向数据流数量$num")
        }

    }
}
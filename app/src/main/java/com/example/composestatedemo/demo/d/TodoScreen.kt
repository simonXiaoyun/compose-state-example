package com.example.composestatedemo.demo.d

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composestatedemo.ui.theme.ComposeStateDemoTheme
import kotlin.collections.ArrayList


@Composable
fun InputRow(modifier: Modifier) {
    InputTextField(modifier = modifier)

}

@Composable
fun InputTextField(modifier: Modifier) {
    val (text, setText) = remember { mutableStateOf("请填写内容") }
    Row(modifier = modifier.fillMaxWidth()) {
        TextField(value = text, onValueChange = setText)
    }
}

@Composable
fun InputRow2(modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        InputTextField(modifier = modifier.weight(6f))
        Row(
            modifier = modifier
                .weight(2f)
                .padding(12.dp)
        ) {
            Icon(imageVector = Icons.Default.Done, contentDescription = "完成")
            Icon(imageVector = Icons.Default.Close, contentDescription = "清除")
        }

    }

}


@Composable
fun InputRow3(modifier: Modifier) {
    val (text, setText) = remember { mutableStateOf("请填写内容") }
    val (isShow, focusChang) = remember {
        mutableStateOf(false)
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        InputTextField3(
            modifier = modifier.weight(6f),
            text = text,
            setText = setText,
            focusChang = focusChang
        )
        if (isShow) {
            RightOperationUI(modifier = modifier.weight(2f), setText = setText)
        } else {
            Icon(
                modifier = modifier.weight(1f),
                imageVector = Icons.Default.Star,
                contentDescription = "标志"
            )
        }

    }

}

@Composable
fun RightOperationUI(modifier: Modifier, setText: (text: String) -> Unit) {
    val focusManager = LocalFocusManager.current
    Row(
        modifier = modifier
            .padding(8.dp)
    ) {
        Icon(imageVector = Icons.Default.Done, contentDescription = "完成",
            modifier
                .weight(1f)
                .clickable {
                    focusManager.clearFocus()
                })
        Icon(imageVector = Icons.Default.Close, contentDescription = "清除",
            modifier
                .weight(1f)
                .clickable {
                    setText("")
                })
    }
}

@Composable
fun InputTextField3(
    modifier: Modifier,
    text: String,
    setText: (text: String) -> Unit,
    focusChang: (isFocus: Boolean) -> Unit
) {
    Row(modifier = modifier.fillMaxWidth()) {
        TextField(value = text, onValueChange = setText, modifier.onFocusChanged { focusState ->
            focusChang(focusState.isFocused)
        })
    }
}

@Composable
fun RightOperationUI4(
    modifier: Modifier,
    itemId: String,
    text: String,
    setText: (text: String) -> Unit,
    changItem: (todoItem: TodoItem) -> Unit
) {
    val focusManager = LocalFocusManager.current
    Row(
        modifier = modifier
            .padding(8.dp)
    ) {
        Icon(imageVector = Icons.Default.Done, contentDescription = "完成",
            modifier
                .weight(1f)
                .clickable {
                    focusManager.clearFocus()
                    changItem(TodoItem(itemId, text, Icons.Default.Face))
                })
        Icon(imageVector = Icons.Default.Close, contentDescription = "清除",
            modifier
                .weight(1f)
                .clickable {
                    setText("")
                })
    }
}

@Composable
fun InputRow4(
    modifier: Modifier,
    itemId: String,
    itemName: String,
    changItem: (todoItem: TodoItem) -> Unit,
    deleteItem:(itemId: String) -> Unit
) {
    Log.i("Simon","$itemName-子级重组了吗？")
    val (text, setText) = remember { mutableStateOf(itemName) }
    val (isShow, focusChang) = remember {
        mutableStateOf(false)
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        InputTextField3(
            modifier = modifier.weight(6f),
            text = text,
            setText = setText,
            focusChang = focusChang
        )
        if (isShow) {
            RightOperationUI4(
                modifier = modifier.weight(2f),
                itemId = itemId,
                text = text,
                setText = setText,
                changItem = changItem
            )
        } else {
            Icon(
                modifier = modifier
                    .weight(1f)
                    .clickable {
                        deleteItem(itemId)
                        Log.i("Simon", "要删的Id:$itemId")
                    },
                imageVector = Icons.Default.Delete,
                contentDescription = "删除"
            )
        }

    }

}

@Composable
fun TodoScreen(
    items: List<TodoItem>,
) {
    val scrollState = rememberLazyListState()
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn(state = scrollState) {
            items(items.size) {
                InputRow(modifier = Modifier.fillMaxWidth())
            }
        }

    }

}

@Composable
fun TodoScreen4(
    items: List<TodoItem>,
    changItem: (todoItem: TodoItem) -> Unit,
    deleteItem:(itemId: String) -> Unit
) {
    val scrollState = rememberLazyListState()
    Column(modifier = Modifier.fillMaxWidth(),verticalArrangement = Arrangement.spacedBy(10.dp)) {
//        Text(text = "滚动到第${scrollState.firstVisibleItemIndex}个item")
        LazyColumn(state = scrollState) {
            Log.i("Simon","父级重组了吗？")
            items(items.size) {
                InputRow4(
                    modifier = Modifier.fillMaxWidth(),
                    itemId = items[it].id,
                    itemName = items[it].name,
                    changItem = changItem,
                    deleteItem = deleteItem
                )
            }
        }

    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeStateDemoTheme {
        val items = ArrayList<TodoItem>()
        items.apply {
            add(TodoItem("1", "笑语盈盈暗香去", Icons.Default.AddCircle))
            add(TodoItem("2", "看啦来快点快点", Icons.Default.Search))
            add(TodoItem("3", "随俗速度点发", Icons.Default.ThumbUp))
        }
        TodoScreen(items)

    }
}


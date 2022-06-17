package com.example.composestatedemo.demo.useless

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
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
fun InputTextField(
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
fun RightOperationUI(
    modifier: Modifier,
    itemNew: TodoItemNew,
    changItem: () -> Unit
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
                    changItem()
                })
        Icon(imageVector = Icons.Default.Close, contentDescription = "清除",
            modifier
                .weight(1f)
                .clickable {
                    itemNew.name = ""
                })
    }
}

@Composable
fun InputRow(
    modifier: Modifier,
    itemNew: TodoItemNew,
    onStartEdit: (TodoItemNew) -> Unit,
    onEditItemChange: () -> Unit,
    onEditDone: () -> Unit,
    deleteItem: (itemId: String) -> Unit
) {
    val setText: (String) -> Unit = {
        onStartEdit(itemNew.copy(name = it))
    }
    val (isShow, focusChang) = remember {
        mutableStateOf(false)
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        InputTextField(
            modifier = modifier.weight(6f),
            text = itemNew.name,
            setText = setText,
            focusChang = focusChang
        )
        if (isShow) {
            RightOperationUI(
                modifier = modifier.weight(2f),
                itemNew = itemNew,
                changItem = onEditItemChange
            )
        } else {
            Icon(
                modifier = modifier
                    .weight(1f)
                    .clickable {
                        deleteItem(itemNew.id)
                        Log.i("Simon", "要删的Id:${itemNew.id}")
                    },
                imageVector = Icons.Default.Delete,
                contentDescription = "删除"
            )
        }

    }

}

@Composable
fun TodoScreenNew(
    itemNews: List<TodoItemNew>,
    onStartEdit: (TodoItemNew) -> Unit,
    onEditItemChange: () -> Unit,
    onEditDone: () -> Unit,
    deleteItem: (itemId: String) -> Unit
) {
    val scrollState = rememberLazyListState()
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn(state = scrollState) {
            items(itemNews.size) {
                InputRow(
                    modifier = Modifier.fillMaxWidth(),
                    itemNews[it],
                    onStartEdit = onStartEdit,
                    onEditItemChange = onEditItemChange,
                    onEditDone = onEditDone,
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
        val items = ArrayList<TodoItemNew>()
        items.apply {
            add(TodoItemNew("1", "笑语盈盈暗香去", Icons.Default.AddCircle))
            add(TodoItemNew("2", "看啦来快点快点", Icons.Default.Search))
            add(TodoItemNew("3", "随俗速度点发", Icons.Default.ThumbUp))
        }
        TodoScreenNew(items,{},{},{},{})

    }
}


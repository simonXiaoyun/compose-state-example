package com.example.composestatedemo.learn

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composestatedemo.ui.theme.ComposeStateDemoTheme


@Composable
fun InputRow(text: String,
             onTextChang: (String) -> Unit,
             modifier: Modifier,
             onImeAction: () -> Unit) {
    val (isEditing,editChange) = remember {
        mutableStateOf(true)
    }
    Column(modifier = modifier.fillMaxWidth()) {
        Row(modifier = modifier.fillMaxWidth()) {
            TodoInputText(text = text, setText = onTextChang, modifier = modifier.weight(7f), onImeAction = onImeAction)
            Row(
                modifier = modifier
                    .weight(2f)
                    .align(Alignment.CenterVertically)
            ) {
                if (isEditing) {
                    Icon(imageVector = Icons.Default.Done, contentDescription = "done",modifier=Modifier.clickable {
                        editChange(false)
                    }.padding(start = 4.dp))
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(imageVector = Icons.Default.Close, contentDescription = "close",modifier = Modifier.clickable {
                        onTextChang("")
                    })
                } else {
                    Icon(imageVector = Icons.Default.Star, contentDescription = "tag")
                }
            }

        }
    }

}

@Composable
fun TodoItemEntryInputRow(
    text: String,
    onTextChange: (String) -> Unit,
    icon: ImageVector,
    onIconChange: (ImageVector) -> Unit,
    submit: () -> Unit,
    iconsVisible: Boolean
) {
    Column {
        InputRow(
            text = text,
            onTextChang = onTextChange,
            modifier = Modifier.fillMaxWidth(),
            onImeAction = submit
        )
        if (iconsVisible) {
            AnimatedIconRow(icon, onIconChange, Modifier.padding(top = 8.dp))
        } else {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun TodoItemEntryInputRowParen(text: String, onTextChang: (String) -> kotlin.Unit, onItemComplete: (TodoItem) -> Unit) {
//    val (text, setText) = remember { mutableStateOf("") }
    val (icon, setIcon) = remember {
        mutableStateOf(Icons.Default.Star)
    }
    val iconsVisible = text.isNotBlank()
    val submit = {
        onItemComplete(TodoItem(1, text, icon))
        onTextChang("")
    }

    TodoItemEntryInputRow(
        text = text,
        onTextChange = onTextChang,
        icon = icon,
        onIconChange = setIcon,
        submit = submit,
        iconsVisible = iconsVisible
    )
}

@Composable
fun TodoInputTextField(
    text: String,
    onTextChang: (String) -> Unit,
    modifier: Modifier,
    onImeAction: () -> Unit
) {
//    val (text, setText) = remember { mutableStateOf("initText") }
    TodoInputText(text, onTextChang, modifier, onImeAction)
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TodoInputText(
    text: String,
    setText: (String) -> Unit,
    modifier: Modifier,
    onImeAction: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Row(modifier = modifier.fillMaxWidth()) {
        TextField(
            value = text, onValueChange = setText,
            maxLines = 1,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                onImeAction()
                keyboardController?.hide()
            }),
            modifier = modifier
        )
    }

}

@Composable
fun TodoEditButton(
    onClick: () -> Unit,
    text: String,
    enabled: Boolean,
    modifier: Modifier
) {
    Button(onClick = { onClick() }, enabled = enabled, modifier = modifier) {
        Text(text = text)
    }
}

@Composable
fun TodoItemEntryInput(text: String, onTextChang: (String) -> kotlin.Unit, onItemComplete: (TodoItem) -> Unit) {
//    val (text, setText) = remember { mutableStateOf("") }
    val (icon, setIcon) = remember {
        mutableStateOf(Icons.Default.Star)
    }
    val iconsVisible = text.isNotBlank()
    val submit = {
        onItemComplete(TodoItem(1, text, icon))
        onTextChang("")
    }

    TodoItemEntryInput(
        text = text,
        onTextChange = onTextChang,
        icon = icon,
        onIconChange = setIcon,
        submit = submit,
        iconsVisible = iconsVisible
    )
}

@Composable
fun TodoItemEntryInput(
    text: String,
    onTextChange: (String) -> Unit,
    icon: ImageVector,
    onIconChange: (ImageVector) -> Unit,
    submit: () -> Unit,
    iconsVisible: Boolean
) {
    Column {
        Row(
            Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {
            TodoInputTextField(
                text = text,
                onTextChang = onTextChange,
                Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                onImeAction = submit
            )
            TodoEditButton(
                onClick = submit,
//                {
//                    onItemComplete(
//                        TodoItem(
//                            1,
//                            text,
//                            icon
//                        )
//                    )
//                    setText("")
//                },
                text = "Add",
                text.isNotEmpty(),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        if (iconsVisible) {
            AnimatedIconRow(icon, onIconChange, Modifier.padding(top = 8.dp))
        } else {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun AnimatedIconRow(icon: ImageVector, setIcon: (ImageVector) -> Unit, modifier: Modifier) {
    Row(modifier.fillMaxWidth()) {

        var (selectIndex, setIndex) = remember {
            mutableStateOf(0)
        }
        Icon(
            imageVector = Icons.Default.Star, contentDescription = "无误",

            Modifier.clickable {
                setIcon(Icons.Default.Star)
                setIndex(0)

            },
            tint = LocalContentColor.current.copy(alpha = if (selectIndex == 0) 1f else 0.3f)
        )
        Icon(
            imageVector = Icons.Default.ArrowBack, contentDescription = "无误", Modifier.clickable {
                setIcon(Icons.Default.ArrowBack)
                setIndex(1)
            },
            tint = LocalContentColor.current.copy(alpha = if (selectIndex == 1) 1f else 0.3f)
        )
        Icon(
            imageVector = Icons.Default.FavoriteBorder,
            contentDescription = "无误",
            Modifier.clickable {
                setIcon(Icons.Default.FavoriteBorder)
                setIndex(2)
            },
            tint = LocalContentColor.current.copy(alpha = if (selectIndex == 2) 1f else 0.3f)
        )
        Icon(imageVector = Icons.Default.ThumbUp, contentDescription = "无误", Modifier.clickable {
            setIcon(Icons.Default.ThumbUp)
            setIndex(3)
        }, tint = LocalContentColor.current.copy(alpha = if (selectIndex == 3) 1f else 0.3f))

    }
}


@Composable
fun TodoScreen(
    items: List<TodoItem>,
    onAddItem: (TodoItem) -> Unit,
    onRemoveItem: (TodoItem) -> Unit
) {
    val scrollState = rememberLazyListState()
    val (text, setText) = remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxWidth()) {
        TodoItemEntryInput(text = text,onTextChang = setText) {
            onAddItem(it)
        }
        LazyColumn(state = scrollState) {
            items(items.size) {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(1.dp)
//                        .background(Color.LightGray)
//                ) {
//                    Text(modifier = Modifier.weight(1f), text = "${items[it].name}")
//                    Icon(imageVector = items[it].icon, contentDescription = null)
//                }
                TodoItemEntryInputRowParen(text = items[it].name,onTextChang = setText,{onAddItem})
//                TodoRow(items[it], { onRemoveItem(it) }) //为什么这里添加方法和不添加方法会不同的效果
                //不添加方法时，list只会一个个更新，添加了方法就会全部刷新

            }
        }
//        Row(modifier = Modifier.fillMaxWidth()) {
//            Button(onClick = {
//                onAddItem(
//                    TodoItem(
//                        items.size - 1,
//                        "Test${items.size}",
//                        Icons.Default.Add
//                    )
//                )
//            }) {
//                Text(text = "Add random Item")
//            }
//            Button(onClick = { onRemoveItem(items[0]) }) {
//                Text(text = "Remove Item")
//            }
//        }

    }

}

@Composable
fun TodoRow(todo: TodoItem, onItemClicked: (TodoItem) -> Unit, modifier: Modifier = Modifier) {
    Log.i("Simon", "进来了吗？")
    Row(
        modifier = modifier
            .clickable { onItemClicked(todo) }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(modifier = modifier.weight(1f), text = todo.name)
        val iconAlpha = remember(todo.id) { randomTint() }
//        val iconAlpha = randomTint()
        Log.i("Simon", "进来了吗？$iconAlpha")
        Icon(
            imageVector = todo.icon,
            tint = LocalContentColor.current.copy(alpha = iconAlpha),
            contentDescription = "Description"
        )
    }
}

private fun randomTint(): Float {
    val randoms = (3..9).random()
    return randoms / 10f
}

@Composable
fun TodoScreenState() {
    val items = remember {
        mutableStateListOf<TodoItem>()
    }
    val scrollState = rememberLazyListState()
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn(state = scrollState) {
            items(items.size) {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(1.dp)
//                        .background(Color.LightGray)
//                ) {
//                    Text(modifier = Modifier.weight(1f), text = "${items[it].name}")
//                    Icon(imageVector = items[it].icon, contentDescription = null)
//                }
                TodoRow(items[it], {})

            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {
                items.add(
                    TodoItem(
                        items.size - 1,
                        "Test${items.size}",
                        Icons.Default.Add
                    )
                )
            }) {
                Text(text = "Add random Item")
            }
            Button(onClick = { items.remove(items[items.size - 1]) }) {
                Text(text = "Remove Item")
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
            add(TodoItem(1, "笑语盈盈暗香去", Icons.Default.AddCircle))
            add(TodoItem(2, "看啦来快点快点", Icons.Default.Search))
            add(TodoItem(3, "随俗速度点发", Icons.Default.ThumbUp))
        }
        TodoScreen(items, {}, {})
//        TodoItemInput { }
    }
}
//
//@Preview
//@Composable
//fun PreviewTodoItemInput() = TodoItemEntryInput(onItemComplete = { })

//@Preview
//@Composable
//fun PreviewInputRow() = InputRow(Modifier.fillMaxWidth())
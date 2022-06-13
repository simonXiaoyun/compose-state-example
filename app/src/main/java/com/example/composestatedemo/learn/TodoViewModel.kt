package com.example.composestatedemo.learn

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {
    //    private var _todoItems = MutableLiveData(listOf<TodoItem>())
//    val todoItems: LiveData<List<TodoItem>> = _todoItems
    private var currentEditPosition by mutableStateOf(-1)
    var todoItems = mutableStateListOf<TodoItem>()
        private set

    val currentEditItem: TodoItem?
        get() = todoItems.getOrNull(currentEditPosition)

    fun addItem(item: TodoItem) {
//      _todoItems.value?.let {
//          val list =it.toMutableList()
//          list.add(item)
//          _todoItems.value = list
//      }
        todoItems.add(item)
    }

    fun removeItem(item: TodoItem) {
//        _todoItems.value?.let {
//            val list =it.toMutableList()
//            list.remove(item)
//            _todoItems.value = list
//        }
        todoItems.remove(item)
        onEditDone()
    }

    fun onEditItemSelected(item: TodoItem) {
        currentEditPosition = todoItems.indexOf(item)
    }

    fun onEditDone() {
        currentEditPosition = -1
    }

    fun onEditItemChange(item: TodoItem) {
        val currentItem = requireNotNull(currentEditItem)
        require(currentItem.id == item.id){
            "You can only change an item with the same id as currentEditItem"
        }
        todoItems[currentEditPosition] = item
    }
}
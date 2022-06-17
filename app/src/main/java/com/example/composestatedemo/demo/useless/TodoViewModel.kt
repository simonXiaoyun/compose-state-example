package com.example.composestatedemo.demo.useless

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TodoViewModelNew : ViewModel() {
    var todoItems = mutableStateListOf<TodoItemNew>()
        private set

    private var currentEditPosition by mutableStateOf(-1)

    private val currentItemNew: TodoItemNew?
        get() = todoItems.getOrNull(currentEditPosition)?.copy()

    fun addItem(itemNew: TodoItemNew) {
        todoItems.add(itemNew)
    }

    fun onEditItemSelected(itemNew: TodoItemNew) {
        currentEditPosition = todoItems.indexOf(itemNew)
    }

    fun onEditDone() {
        currentEditPosition = -1
    }

    fun onEditItemChange() {
        currentItemNew?.let {
            todoItems[currentEditPosition] = it
        }
    }

    fun removeItem(itemId: String) {
        todoItems.find { it.id == itemId }?.apply {
            Log.i("Simon", "要删除的名字：${this.name}")
            todoItems.remove(this)
        }

    }

}
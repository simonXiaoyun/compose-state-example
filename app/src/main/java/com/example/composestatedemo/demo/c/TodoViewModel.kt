package com.example.composestatedemo.demo.c

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {
    var todoItems = mutableStateListOf<TodoItem>()
        private set


    fun addItem(item: TodoItem) {
        todoItems.add(item)
    }

    fun changItem(item: TodoItem){
        todoItems.find { it.id == item.id }?.apply {
            name = item.name
            icon = item.icon
        }
    }

    fun removeItem(itemId: String) {
        todoItems.find { it.id == itemId }?.apply {
            Log.i("Simon","要删除的名字：${this.name}")
            todoItems.remove(this)
        }

    }

}
package com.example.composestatedemo.demo.b

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataFlowViewModel : ViewModel() {
    private var _num = MutableLiveData<Int>()
    val num: LiveData<Int> = _num

    fun changNumText(num:Int){
        _num.value = num
    }
}
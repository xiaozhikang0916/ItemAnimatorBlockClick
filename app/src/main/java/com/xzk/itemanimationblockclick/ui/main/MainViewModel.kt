package com.xzk.itemanimationblockclick.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.IllegalArgumentException
import java.util.*

const val TAG = "ItemAnimationClick"
class MainViewModel : ViewModel() {
    val dataList: MutableLiveData<List<Item>> = MutableLiveData()
    private var internalData: List<Item> = arrayListOf()

    fun getInitDataList(initSize: Int = 10) {
        val result = arrayListOf<Item>()
        repeat(initSize) {
            UUID.randomUUID().apply {
                result.add(Item(this.hashCode().toLong(), this.toString()))
            }
        }
        internalData = result
        dataList.postValue(internalData)
    }

    fun checkItem(position: Int, currentState: Boolean) {
        internalData = internalData.map { it.clone() }.toList()
        val item = internalData[position]
        Log.d(TAG, "ViewModel Click on $item, currentState $currentState")
        if (item.checked != currentState) {
            Log.e(TAG,"Fail to switch state", IllegalArgumentException("Your passing state conflicts with data"))
        }
        item.checked = item.checked.not()
        dataList.postValue(internalData)
    }
}

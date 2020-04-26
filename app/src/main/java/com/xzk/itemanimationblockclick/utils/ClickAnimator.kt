package com.xzk.itemanimationblockclick.utils

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView

class ClickItemAnimator : DefaultItemAnimator() {

    override fun onChangeStarting(item: RecyclerView.ViewHolder?, oldItem: Boolean) {
        (item as? OnItemAnimationListener)?.onItemAnimationStatus(false)
        super.onChangeStarting(item, oldItem)
    }

    override fun onChangeFinished(item: RecyclerView.ViewHolder?, oldItem: Boolean) {
        (item as? OnItemAnimationListener)?.onItemAnimationStatus(true)
        super.onChangeFinished(item, oldItem)
    }

    override fun onMoveStarting(item: RecyclerView.ViewHolder?) {
        (item as? OnItemAnimationListener)?.onItemAnimationStatus(false)
        super.onMoveStarting(item)
    }

    override fun onMoveFinished(item: RecyclerView.ViewHolder?) {
        (item as? OnItemAnimationListener)?.onItemAnimationStatus(true)
        super.onMoveFinished(item)
    }
}

interface OnItemAnimationListener {
    fun onItemAnimationStatus(shouldClickEnabled: Boolean)
}
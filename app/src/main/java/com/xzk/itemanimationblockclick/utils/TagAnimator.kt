package com.xzk.itemanimationblockclick.utils

import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.xzk.itemanimationblockclick.R

class TagAnimator : DefaultItemAnimator() {

    override fun onChangeStarting(item: RecyclerView.ViewHolder?, oldItem: Boolean) {
        item?.itemView?.let(::setTag)
        super.onChangeStarting(item, oldItem)
    }

    override fun onChangeFinished(item: RecyclerView.ViewHolder?, oldItem: Boolean) {
        item?.itemView?.let(::restoreTag)
        super.onChangeFinished(item, oldItem)
    }

    private fun setTag(view: View) {
        val clickable = view.isClickable
        view.setTag(R.id.tag_id, clickable)
        view.isClickable = false
        iteratorView(view, ::setTag)
    }

    private fun restoreTag(view: View) {
        val clickable = view.getTag(R.id.tag_id)
        view.isClickable = clickable as? Boolean ?: false
        iteratorView(view, ::restoreTag)
    }

    private fun iteratorView(view: View, action: ((View) -> Unit)) {
        if (view is ViewGroup) {
            view.children.forEach(action)
        }
    }
}
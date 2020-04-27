package com.xzk.itemanimationblockclick.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xzk.itemanimationblockclick.R

class DefaultAnimatorFragment : BaseFragment<ItemViewHolder>() {
    override fun buildRecyclerView(recyclerView: RecyclerView) {
        super.buildRecyclerView(recyclerView)
        recyclerView.itemAnimator?.let {
            it.changeDuration = 1500L
        }
    }

    override fun buildAdapter(): BaseAdapter<ItemViewHolder> {
        return DefaultAdapter(this)
    }
}

class DefaultAdapter(fragment: BaseFragment<ItemViewHolder>) : BaseAdapter<ItemViewHolder>(fragment) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_item,
                parent,
                false
            ), fragment::checkItem
        )
    }
}
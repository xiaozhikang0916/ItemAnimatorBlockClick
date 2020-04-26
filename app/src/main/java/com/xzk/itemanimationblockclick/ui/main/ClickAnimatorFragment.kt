package com.xzk.itemanimationblockclick.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xzk.itemanimationblockclick.R
import com.xzk.itemanimationblockclick.utils.ClickItemAnimator
import com.xzk.itemanimationblockclick.utils.OnItemAnimationListener

class ClickAnimatorFragment : BaseFragment<ItemViewHolder>() {
    override fun buildRecyclerView(recyclerView: RecyclerView) {
        super.buildRecyclerView(recyclerView)
        recyclerView.itemAnimator = ClickItemAnimator().apply {
            this.changeDuration = 1500L
        }
    }

    override fun buildAdapter(): BaseAdapter<ItemViewHolder> {
        return ClickAdapter(this)
    }
}

class ClickAdapter(fragment: ClickAnimatorFragment) : BaseAdapter<ItemViewHolder>(fragment) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ClickItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_item,
                parent,
                false
            ), fragment::checkItem
        )
    }
}

class ClickItemViewHolder(itemView: View, onCheck: ((Int, Boolean) -> Unit)) :
    ItemViewHolder(itemView, onCheck), OnItemAnimationListener {
    private var handleClick = true

    override fun onClick(view: View) {
        onCheck?.takeIf { handleClick }?.invoke(adapterPosition, data?.checked ?: false)
    }

    override fun onItemAnimationStatus(shouldClickEnabled: Boolean) {
        handleClick = shouldClickEnabled
    }

    override fun bind(bindData: Item) {
        handleClick = true
        super.bind(bindData)
    }
}
package com.xzk.itemanimationblockclick.ui.main

import androidx.recyclerview.widget.RecyclerView
import com.xzk.itemanimationblockclick.utils.TagAnimator

class TagAnimatorFragment : BaseFragment<ItemViewHolder>() {
    override fun buildRecyclerView(recyclerView: RecyclerView) {
        super.buildRecyclerView(recyclerView)
        recyclerView.itemAnimator = TagAnimator().apply {
            this.changeDuration = 1500L
        }
    }

    override fun buildAdapter(): BaseAdapter<ItemViewHolder> {
        return DefaultAdapter(this)
    }
}

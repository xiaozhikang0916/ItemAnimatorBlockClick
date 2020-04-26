package com.xzk.itemanimationblockclick.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xzk.itemanimationblockclick.R

abstract class BaseFragment<T : ItemViewHolder> : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BaseAdapter<T>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler)
        buildRecyclerView(recyclerView)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.dataList.observe(this, Observer {
            adapter.setData(it)
        })

        if (viewModel.dataList.value.isNullOrEmpty()) {
            viewModel.getInitDataList()
        }
    }

    protected open fun buildRecyclerView(recyclerView: RecyclerView) {
        adapter = buildAdapter()
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter
    }

    protected abstract fun buildAdapter(): BaseAdapter<T>

    fun checkItem(index: Int, currentState: Boolean) {
        viewModel.checkItem(index, currentState)
    }
}

data class Item(
    val id: Long,
    val title: String,
    var checked: Boolean = false
) {
    fun clone(): Item {
        return Item(id, title, checked)
    }
}

abstract class BaseAdapter<T : ItemViewHolder>(protected val fragment: BaseFragment<T>) : RecyclerView.Adapter<T>() {

    protected var dataList: List<Item> = emptyList()

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.bind(dataList[position])
    }

    fun setData(list: List<Item>) {
        val old = dataList
        dataList = list
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return old[oldItemPosition].id == dataList[newItemPosition].id
            }

            override fun getOldListSize(): Int {
                return old.size
            }

            override fun getNewListSize(): Int {
                return dataList.size
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return old[oldItemPosition] == dataList[newItemPosition]
            }
        }).dispatchUpdatesTo(this)
    }
}

open class ItemViewHolder(itemView: View, protected val onCheck: ((Int, Boolean) -> Unit)? = null) :
    RecyclerView.ViewHolder(itemView) {
    protected var data: Item? = null
    private val titleView = itemView.findViewById<TextView>(R.id.title)
    private val checkButton = itemView.findViewById<TextView>(R.id.button)

    init {
        checkButton.setOnClickListener(::onClick)
    }

    open fun onClick(view: View) {
        onCheck?.invoke(adapterPosition, data?.checked ?: false)
    }

    open fun bind(bindData: Item) {
        data = bindData
        titleView.text = bindData.title
        checkButton.isSelected = bindData.checked
    }
}
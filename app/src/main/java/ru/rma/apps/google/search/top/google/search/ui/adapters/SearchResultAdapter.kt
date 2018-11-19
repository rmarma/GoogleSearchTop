package ru.rma.apps.google.search.top.google.search.ui.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.search_result_item.view.*
import ru.rma.apps.google.search.top.R
import ru.rma.apps.google.search.top.core.di.scopes.ActivityScope
import ru.rma.apps.google.search.top.core.utils.extensions.inflate
import ru.rma.apps.google.search.top.google.search.ui.models.SearchResultModel
import javax.inject.Inject

@ActivityScope
class SearchResultAdapter @Inject constructor() : RecyclerView.Adapter<SearchResultAdapter.ItemViewHolder>() {


    class ItemViewHolder constructor(
            itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView.textTitle
        private val snippet: TextView = itemView.textSnippet

        fun item(item: SearchResultModel) {
            title.text = item.title
            snippet.text = item.snippet
        }
    }


    private var items = emptyList<SearchResultModel>()

    fun itmes(items: List<SearchResultModel>) {
        val diff = DiffUtil.calculateDiff(DiffUtilCallback(this.items, items))
        this.items = items
        diff.dispatchUpdatesTo(this)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(parent.inflate(R.layout.search_result_item))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.item(items[position])
    }


    private class DiffUtilCallback(
        val oldItems: List<SearchResultModel>,
        val newItems: List<SearchResultModel>
    ) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldItems[oldItemPosition]
            val newItem = newItems[newItemPosition]
            // один и тот же элемент
            return oldItem.link == newItem.link
        }

        override fun getOldListSize() = oldItems.size

        override fun getNewListSize() = newItems.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldItems[oldItemPosition]
            val newItem = newItems[newItemPosition]
            // одинаковое содержимое
            return oldItem.title == newItem.title
                    && oldItem.snippet == newItem.snippet
        }
    }
}
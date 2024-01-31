package ru.geekbrains.map.ui.markers.adaper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.map.R
import ru.geekbrains.map.domain.models.MarkerDomain


class MarkerAdapter(private val delegate: Delegate?) :
    RecyclerView.Adapter<MarkerViewHolder?>() {

    interface Delegate {
        /**
         * Событие наступает при выборе
         * маркера из списка.
         * @param marker Маркер
         */
        fun onItemClick(marker: MarkerDomain)

        /**
         * Событие наступает при длительном
         * клике по строке.
         * @param marker Маркер
         */
        fun onItemLongClick(marker: MarkerDomain)
    }

    private val data = ArrayList<MarkerDomain>()

    fun setItems(newList: List<MarkerDomain>) {
        val result = DiffUtil.calculateDiff(
            DiffUtilCallback(
                data,
                newList as ArrayList<MarkerDomain>
            )
        )
        result.dispatchUpdatesTo(this)
        data.clear()
        data.addAll(newList)
    }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarkerViewHolder =
        MarkerViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.marker_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MarkerViewHolder, position: Int) =
        holder.bind(data[position], delegate)

    inner class DiffUtilCallback(
        private var oldItems: ArrayList<MarkerDomain>,
        private var newItems: ArrayList<MarkerDomain>,
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldItems.size

        override fun getNewListSize(): Int = newItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition].markerId == newItems[newItemPosition].markerId

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition] == newItems[newItemPosition]
    }
}
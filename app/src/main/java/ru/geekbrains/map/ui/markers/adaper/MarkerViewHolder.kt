package ru.geekbrains.map.ui.markers.adaper

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.geekbrains.map.databinding.MarkerItemBinding
import ru.geekbrains.map.domain.models.MarkerDomain
import ru.geekbrains.map.utils.click
import ru.geekbrains.map.utils.longClick


class MarkerViewHolder(
    view: View,
) : RecyclerView.ViewHolder(view) {

    private val viewBinding: MarkerItemBinding by viewBinding()

    fun bind(marker: MarkerDomain, delegate: MarkerAdapter.Delegate?) {
        with(viewBinding) {
            coordinate.text = marker.coordinateToString()
            title.text = marker.title
            description.text = marker.description
            root.click { delegate?.onItemClick(marker) }
            root.longClick {
                delegate?.onItemLongClick(marker)
                true
            }
        }
    }
}
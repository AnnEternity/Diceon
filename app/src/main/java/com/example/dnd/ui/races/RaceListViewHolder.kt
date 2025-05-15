package com.example.dnd.ui.races

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.dnd.database.RaceListEntity
import com.example.dnd.databinding.RaceListItemBinding

class RaceListViewHolder(
    private val listItemBind: RaceListItemBinding,
    private val onClick: (RaceListEntity) -> Unit,
) : ViewHolder(listItemBind.root) {

    fun bind(race: RaceListEntity) {
        listItemBind.listItemVar = race
        listItemBind.root.setOnClickListener {
            onClick(race)
        }
    }
}
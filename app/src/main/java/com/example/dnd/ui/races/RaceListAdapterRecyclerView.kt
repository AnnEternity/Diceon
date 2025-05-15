package com.example.dnd.ui.races

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.dnd.database.RaceListEntity
import com.example.dnd.databinding.RaceListItemBinding

class RaceListAdapterRecyclerView(
    private val onClick: (RaceListEntity) -> Unit,
) : Adapter<RaceListViewHolder>() {

    private var listRacesCurrent = emptyList<RaceListEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RaceListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RaceListItemBinding.inflate(inflater, parent, false)
        val viewHolder = RaceListViewHolder(binding, onClick)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return listRacesCurrent.size
    }

    override fun onBindViewHolder(holder: RaceListViewHolder, position: Int) {
        val item = listRacesCurrent[position]
        holder.bind(item)
    }

    fun updateListRacesCurrent(newList: List<RaceListEntity>) {
        listRacesCurrent = newList
        notifyDataSetChanged()
    }
}

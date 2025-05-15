package com.example.dnd.ui.classes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.dnd.database.ClassListEntity
import com.example.dnd.databinding.ClassListItemBinding

class ClassListAdapter(
    private val onClick: (ClassListEntity) -> Unit,
) : Adapter<ClassListViewHolder>() {

    private var listClassesCurrent = emptyList<ClassListEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ClassListItemBinding.inflate(inflater, parent, false)
        val viewHolder = ClassListViewHolder(binding, onClick)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return listClassesCurrent.size
    }

    override fun onBindViewHolder(holder: ClassListViewHolder, position: Int) {
        val item = listClassesCurrent[position]
        holder.bind(item)
    }

    fun updateListClassesCurrent(newList: List<ClassListEntity>) {
        listClassesCurrent = newList
        notifyDataSetChanged()
    }
}
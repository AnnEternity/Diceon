package com.example.dnd.ui.classes

import androidx.recyclerview.widget.RecyclerView
import com.example.dnd.database.ClassListEntity
import com.example.dnd.databinding.ClassListItemBinding

class ClassListViewHolder(
    private val itemBind: ClassListItemBinding,
    private val onClick: (ClassListEntity) -> Unit,
) : RecyclerView.ViewHolder(itemBind.root) {

    fun bind(classItem: ClassListEntity) {
        itemBind.listItemVar = classItem
        itemBind.root.setOnClickListener {
            onClick(classItem)
        }
    }
}
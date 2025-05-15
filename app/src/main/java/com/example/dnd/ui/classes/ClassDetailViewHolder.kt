package com.example.dnd.ui.classes

import androidx.recyclerview.widget.RecyclerView
import com.example.dnd.database.ProficiencyEntity
import com.example.dnd.databinding.ClassProficiencyItemBinding

class ClassDetailViewHolder(
    private val itemBind: ClassProficiencyItemBinding
) : RecyclerView.ViewHolder(itemBind.root) {

    fun bind(classItem: ProficiencyEntity) {
        itemBind.listItemVar = classItem
    }
}
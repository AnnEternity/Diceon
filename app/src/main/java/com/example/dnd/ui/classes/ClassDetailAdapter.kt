package com.example.dnd.ui.classes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.dnd.database.ClassWithProficiencies
import com.example.dnd.database.ProficiencyEntity
import com.example.dnd.databinding.ClassProficiencyItemBinding

class ClassDetailAdapter(
) : Adapter<ClassDetailViewHolder>() {

    private lateinit var binding: ClassProficiencyItemBinding
    private var listClassWithPro = emptyList<ClassWithProficiencies>()

    private var listProficienciesCurrent = mutableListOf<ProficiencyEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassDetailViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ClassProficiencyItemBinding.inflate(inflater, parent, false)

        val viewHolder = ClassDetailViewHolder(binding)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return listProficienciesCurrent.size
    }

    override fun onBindViewHolder(holder: ClassDetailViewHolder, position: Int) {
        val item = listProficienciesCurrent[position]
        holder.bind(item)
    }

    fun updateListProficienciesCurrent(newList: List<ClassWithProficiencies>) {
        listProficienciesCurrent.clear()
        listClassWithPro = newList
        for (itemList in listClassWithPro) {
            listProficienciesCurrent.addAll(itemList.proficiencies)
        }
        notifyDataSetChanged()
    }
}
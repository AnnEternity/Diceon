package com.example.dnd.ui.classes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dnd.databinding.ClassDetailFragmentBinding

class ClassDetailFragment : Fragment() {

    lateinit var binding: ClassDetailFragmentBinding
    lateinit var viewModel: ClassDetailViewModel
    lateinit var adapter: ClassDetailAdapter
    private val args by navArgs<ClassDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ClassDetailFragmentBinding.inflate(inflater, container, false)
        viewModel =
            ViewModelProvider.create(this, ViewModelProvider.AndroidViewModelFactory()).get()
        binding.proficiencyViewList.layoutManager = LinearLayoutManager(requireContext())
        adapter = ClassDetailAdapter()
        binding.proficiencyViewList.adapter = adapter
        viewModel.hasError.observe(viewLifecycleOwner) { noData ->
            showNoData(noData)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getClassLiveData(args.index).observe(viewLifecycleOwner) {
            binding.detailClassVar = it
            showNoData(it == null || it.classEntity.name?.isEmpty() ?: true)
        }
        viewModel.getClassLiveData(args.index)
            .observe(viewLifecycleOwner) { classWithProficiencies ->
                classWithProficiencies?.let {
                    binding.detailClassVar = it
                    adapter.updateListProficienciesCurrent(listOf(it))
                }
            }
    }

    private fun showNoData(noData: Boolean) {
        if (noData) {
            binding.noData.visibility = View.VISIBLE
            binding.hitDieText.visibility = View.GONE
            binding.proficiencyText.visibility = View.GONE

        } else {
            binding.noData.visibility = View.GONE
            binding.hitDieText.visibility = View.VISIBLE
            binding.proficiencyText.visibility = View.VISIBLE
        }
    }
}
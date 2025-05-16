package com.example.dnd.ui.classes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil3.load
import coil3.request.error
import coil3.request.placeholder
import com.example.dnd.R
import com.example.dnd.database.ClassListEntity
import com.example.dnd.databinding.ClassFragmentBinding

private const val CLASS_STATE_KEY = "ClassStateKey"

class ClassFragment : Fragment() {

    private lateinit var binding: ClassFragmentBinding
    private lateinit var viewModel: ClassViewModel

    private val adapter = ClassListAdapter(
        onClick = { classListItem: ClassListEntity ->
            findNavController().navigate(
                ClassFragmentDirections.actionClassFragmentToClassDetailFragment(
                    classListItem.index
                )
            )
        })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.class_fragment, container, false
        )
        viewModel = ViewModelProvider(this).get(ClassViewModel::class.java)
        binding.classes = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.recyclerViewList.adapter = adapter
        binding.recyclerViewList.layoutManager = LinearLayoutManager(requireContext())
        if (savedInstanceState != null) {
            binding.classMotion.transitionToState(savedInstanceState.getInt(CLASS_STATE_KEY))
        }
        binding.classesImage.load(
            "https://f000.backblazeb2.com/file/dnd-udacity/races_image.png",
            builder = {
                placeholder(R.drawable.image_placeholder)
                error(R.drawable.image_placeholder)
            })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.list.observe(viewLifecycleOwner) {
            adapter.updateListClassesCurrent(it)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val currentState = binding.classMotion.currentState
        outState.putInt(CLASS_STATE_KEY, currentState)
    }
}
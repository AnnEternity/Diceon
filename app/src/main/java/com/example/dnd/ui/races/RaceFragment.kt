package com.example.dnd.ui.races

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
import com.example.dnd.database.RaceListEntity
import com.example.dnd.databinding.RaceFragmentBinding

private const val RACE_STATE_KEY = "RaceStateKey"

class RaceFragment : Fragment() {

    private lateinit var binding: RaceFragmentBinding
    private lateinit var viewModel: RaceViewModel

    private val adapter = RaceListAdapterRecyclerView(
        onClick = { raceListEntity: RaceListEntity ->
            findNavController().navigate(
                RaceFragmentDirections.actionRaceFragmentToDetailFragment(
                    raceListEntity.index
                )
            )
        })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.race_fragment, container, false
        )
        binding.recyclerViewList.adapter = adapter
        binding.recyclerViewList.layoutManager = LinearLayoutManager(requireContext())
        viewModel = ViewModelProvider(this).get(RaceViewModel::class.java)
        binding.race = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
        if (savedInstanceState != null) {
            binding.raceMotion.transitionToState(savedInstanceState.getInt(RACE_STATE_KEY))
        }
        binding.racesImage.load(
            "https://f000.backblazeb2.com/file/dnd-udacity/races_image.png",
            builder = {
                placeholder(R.drawable.image_placeholder)
                error(R.drawable.image_placeholder)
            })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.list.observe(viewLifecycleOwner) {
            adapter.updateListRacesCurrent(it)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val currentState = binding.raceMotion.currentState
        outState.putInt(RACE_STATE_KEY, currentState)
    }
}
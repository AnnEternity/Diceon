package com.example.dnd.ui.races

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.navArgs
import com.example.dnd.databinding.RaceDetailFragmentBinding

class DetailFragment : Fragment() {

    lateinit var binding: RaceDetailFragmentBinding
    lateinit var viewModel: DetailViewModel
    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RaceDetailFragmentBinding.inflate(
            inflater, container, false
        )
        viewModel =
            ViewModelProvider.create(this, ViewModelProvider.AndroidViewModelFactory()).get()
        binding.lifecycleOwner = this.viewLifecycleOwner

        viewModel.hasError.observe(viewLifecycleOwner) { noData ->
            showNoData(noData)
            }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getRaceLiveData(args.index).observe(viewLifecycleOwner) {
            binding.detailVar = it
            showNoData(it == null || it.name?.isEmpty() ?: true)
        }
    }

    private fun showNoData(show: Boolean) {
        binding.noData.visibility = if (show) View.VISIBLE else View.GONE
    }
}
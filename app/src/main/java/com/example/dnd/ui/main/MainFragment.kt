package com.example.dnd.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil3.load
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.error
import coil3.request.fallback
import coil3.request.placeholder
import com.example.dnd.R
import com.example.dnd.databinding.MainFragmentBinding

class MainFragment : Fragment() {

//    val request = ImageRequest.Builder(requireContext())
//        .data("https://f000.backblazeb2.com/file/dnd-udacity/dragon_image.png")
//        .crossfade(true)
//        .target(binding.mainScreenImage)
//        .build()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = MainFragmentBinding.inflate(inflater)

        binding.racesButton.setOnClickListener { navToRaces() }
        binding.classesButton.setOnClickListener { navToClasses() }
        binding.addReminderButton.setOnClickListener { navToReminder() }



        binding.mainScreenImage.load(
            "https://f000.backblazeb2.com/file/dnd-udacity/dragon_image.png",
            builder = {
                placeholder(R.drawable.image_placeholder)
                error(R.drawable.image_placeholder)
            })

        return binding.root
    }

    private fun navToRaces() {
        this.findNavController().navigate(MainFragmentDirections.actionMainFragmentToRaceFragment())
    }

    private fun navToClasses() {
        this.findNavController()
            .navigate(MainFragmentDirections.actionMainFragmentToClassFragment())
    }

    private fun navToReminder() {
        this.findNavController()
            .navigate(MainFragmentDirections.actionMainFragmentToReminderFragment())
    }
}